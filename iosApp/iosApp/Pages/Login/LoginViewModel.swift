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
    
    private let useCases = LoginViewModelHelper().getViewModel()
    
    @Published var isLoading: Bool = false
    
    func login(username: String, password: String) {
        isLoading = true
        
        let param = LoginUseCase.Param(username: username, password: password)
        useCases.login(param: param) { res, error in
            self.isLoading = false
            
            if let nsError = error as NSError? {
                if nsError.domain == "MyDomain" {
                    
                }
                print("may error pre")
                return
            }
            
            if res is ApiResLogin.Fail {
                print("may fail guys")
                return
            }
            
            if res is ApiResLogin.Success {
                print("may success guys")
                return
            }
        }
    }
}
