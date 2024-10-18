//
//  LoginViewModel.swift
//  iosApp
//
//  Created by Apple on 9/22/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import shared

protocol LoginUIState {
    var isLoading: Bool { get }
}

enum SealedLoginUIState: Equatable, LoginUIState {
    case initial(isLoading: Bool)
    case validation(isLoading: Bool, username: String?, password: String?)
    
    var isLoading: Bool {
        switch self {
        case .initial(let isLoading):
            return isLoading
        case .validation(let isLoading, _, _):
            return isLoading
        }
    }
    
    static func == (prev: SealedLoginUIState, new: SealedLoginUIState) -> Bool {
        switch (prev, new) {
        case (.initial(let prevIsLoading), .initial(let newIsLoading)):
            return prevIsLoading == newIsLoading
        case (.validation(let prevIsLoading, let prevUsername, let prevPassword), .validation(let newIsLoading, let newUsername, let newPassword)):
            return prevIsLoading == newIsLoading && prevUsername == newUsername && prevPassword == newPassword
        default:
            return false
        }
    }
}
struct LoginViewModelState: Equatable {
    var isLoading: Bool = false
    var usernameError: String? = nil
    var passwordError: String? = nil
    
    func toUIState() -> SealedLoginUIState {
        if usernameError == nil && passwordError == nil {
            return SealedLoginUIState.initial(isLoading: isLoading)
        } else {
            return SealedLoginUIState.validation(isLoading: isLoading, username: usernameError, password: passwordError)
        }
    }
}

class LoginViewModel: ObservableObject {
    
    // MARK: - properties
    private let helper = IOSLoginViewModelHelperWrapper().getHelper()
    
    // MARK: - ui events
    let onSuccess = PassthroughSubject<String, Never>()
    let onMessageReceived = PassthroughSubject<String, Never>()
    
    // MARK: - ui state
    private let _viewModelState = CurrentValueSubject<LoginViewModelState, Never>(LoginViewModelState())
    var uiState: AnyPublisher<SealedLoginUIState, Never> {
        return _viewModelState.map { state in state.toUIState() }.removeDuplicates().eraseToAnyPublisher()
    }
    
    // MARK: - class helper
    func login(username: String, password: String) {
        _viewModelState.send(LoginViewModelState(isLoading: true))
        
        let param = CommonLoginUseCase.Param(username: username, password: password)
        helper.login(param: param) { [weak self] res, error in
            guard let strongSelf = self else { return }
            
            if let nsError = error as NSError? {
                strongSelf._viewModelState.send(LoginViewModelState(isLoading: false))
                strongSelf.onMessageReceived.send(nsError.localizedDescription)
                return
            }
            
            if let body = res as? UiResLogin.Fail {
                strongSelf._viewModelState.send(LoginViewModelState(isLoading: false, usernameError: body.validation.username, passwordError: body.validation.password))
                return
            }
            
            if let body = res as? UiResLogin.Success {
                strongSelf.startSessionBy(user: body.user, token: body.token)
                return
            }
        }
    }
    
    private func startSessionBy(user: UiUser, token: String) {
        helper.startSessionBy(user: user, token: token) { [weak self] in
            guard let strongSelf = self else { return }
            strongSelf._viewModelState.send(LoginViewModelState(isLoading: false))
            strongSelf.onSuccess.send(user.username)
        }
    }
    
    func cancelCoroutines() {
        helper.cancelCoroutines()
    }
}
