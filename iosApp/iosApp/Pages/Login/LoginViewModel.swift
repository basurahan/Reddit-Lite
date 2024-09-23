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
    
    private let helper = LoginViewModelHelperWrapper().getHelper()
    
    var onSuccess = PassthroughSubject<Void, Never>()
    
    @Published var isLoading: Bool = false
    @Published var message: String? = nil
    
    @Published var usernameError: String? = nil
    @Published var passwordError: String? = nil
    
    func login(username: String, password: String) {
        isLoading = true
        
        let param = LoginUseCase.Param(username: username, password: password)
        helper.login(param: param) { res, error in
            self.isLoading = false
            
            if let nsError = error as NSError? {
                self.message = nsError.localizedDescription
                return
            }
            
            if let body = res as? ApiResLogin.Fail {
                self.usernameError = body.validation.username
                self.passwordError = body.validation.password
                return
            }
            
            if let body = res as? ApiResLogin.Success {
                let success = KeychainHelper.save(token: body.token, forKey: KeyDirectory.token.key)
                guard success else {
                    self.message = "Something went wrong"
                    return
                }
                
                self.onSuccess.send()
                return
            }
        }
    }
    
    func leave() {
        helper.cancelCoroutines()
    }
}
