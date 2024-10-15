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

extension SessionViewModelState {
    func copy(isReady: Bool? = nil, userInfo: UiUserInfo? = nil) -> SessionViewModelState {
        return SessionViewModelState(isReady: isReady ?? self.isReady, userInfo: userInfo ?? self.userInfo)
    }
}

class SessionViewModel {
    static let shared = SessionViewModel()
    
    // MARK: - properties
    private let helper = IOSSessionViewModelHelperWrapper().getHelper()
    
    // MARK: - ui state
    private let viewModelState = CurrentValueSubject<SessionViewModelState, Never>(SessionViewModelState())
    var uiState: AnyPublisher<any SessionUIState, Never> {
        return viewModelState.removeDuplicates().map { state in
            state.toUiState()
        }.eraseToAnyPublisher()
    }
    
    // MARK: - lifecycle
    init() {
        helper.collectSession { [weak self] session in
            guard let strongSelf = self else { return }
            strongSelf.viewModelState.send(strongSelf.viewModelState.value.copy(isReady: true, userInfo: session))
        }
    }
    
    // MARK: - class helper
    func cancelCoroutines() {
        helper.cancelCoroutines()
    }
}
