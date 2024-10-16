//
//  SessionViewModel.swift
//  iosApp
//
//  Created by Apple on 10/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import shared

protocol SessionUIState: Equatable {}
struct Initial: SessionUIState  {}
struct LoggedIn: SessionUIState {
    let userInfo: UiUserInfo
}
struct NotLoggedIn: SessionUIState {}

struct SessionViewModelState: Equatable {
    
    private(set) var isReady: Bool = false
    private(set) var userInfo: UiUserInfo? = nil
    
    func toUiState() -> any SessionUIState {
        if isReady {
            if userInfo != nil {
                return LoggedIn(userInfo: self.userInfo!)
            } else {
                return NotLoggedIn()
            }
        } else {
            return Initial()
        }
    }
}

class SessionViewModel {
    static let shared = SessionViewModel()
    
    // MARK: - properties
    private let helper = IOSSessionViewModelHelperWrapper().getHelper()
    
    // MARK: - ui state
    private let viewModelState = CurrentValueSubject<SessionViewModelState, Never>(SessionViewModelState())
    var currentSessionState: any SessionUIState {
        return viewModelState.value.toUiState()
    }
    var launchScreenState: AnyPublisher<Void, Never> {
        return viewModelState.map { state in
            state.toUiState()
        }.removeDuplicates { prev, current in
            if prev is Initial {
                return false
            }
            
            return true
        }
        .map { _ in () }
        .eraseToAnyPublisher()
    }
    var uiState: AnyPublisher<any SessionUIState, Never> {
        return viewModelState.map { state in
            state.toUiState()
        }
        .eraseToAnyPublisher()
    }
    
    // MARK: - lifecycle
    init() {
        helper.collectSession { [weak self] session in
            guard let strongSelf = self else { return }
            strongSelf.viewModelState.send(SessionViewModelState(isReady: true, userInfo: session))
        }
    }
    
    // MARK: - class helper
    func logout(callBack: @escaping () -> Void) {
        helper.logout {
            callBack()
        }
    }
    
    func cancelCoroutines() {
        helper.cancelCoroutines()
    }
}
