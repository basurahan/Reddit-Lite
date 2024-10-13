//
//  LoginViewModel.swift
//  iosApp
//
//  Created by Apple on 9/22/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import shared

class LoginViewModel: ObservableObject {
    
    // MARK: - properties
    private let helper = IOSLoginViewModelHelperWrapper().getHelper()
    
    // MARK: - ui events
    var onSuccess = PassthroughSubject<String, Never>()
    
    // MARK: - ui state
    @Published var isLoading: Bool = false
    @Published var message: String? = nil
    
    @Published var usernameError: String? = nil
    @Published var passwordError: String? = nil
    
    // MARK: - class helper
    func login(username: String, password: String) {
        isLoading = true
        
        let param = CommonLoginUseCase.Param(username: username, password: password)
        helper.login(param: param) { res, error in
            self.isLoading = false
            
            if let nsError = error as NSError? {
                self.message = nsError.localizedDescription
                return
            }
            
            if let body = res as? UiResLogin.Fail {
                self.usernameError = body.validation.username
                self.passwordError = body.validation.password
                return
            }
            
            if let body = res as? UiResLogin.Success {
                self.startSessionBy(user: body.user, token: body.token)
                return
            }
        }
    }
    
    private func startSessionBy(user: UiUser, token: String) {
        helper.startSessionBy(user: user, token: token) {
            self.onSuccess.send(user.username)
        }
    }
    
    func leave() {
        helper.cancelCoroutines()
    }
}
