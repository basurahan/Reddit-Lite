//
//  SessionViewModel.swift
//  iosApp
//
//  Created by Apple on 10/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import shared

protocol SessionUIState {
    var isLoading: Bool { get }
    var isReady: Bool { get }
}

enum SealedSessionUIState : Equatable, SessionUIState {
    case initial(isReady: Bool, isLoading: Bool)
    case logged_in(isReady: Bool, isLoading: Bool, info: UiUserInfo)
    case logged_out(isReady: Bool, isLoading: Bool)
    
    var isLoading: Bool {
        switch self {
        case .initial(_, let isLoading):
            return isLoading
        case .logged_in(_, let isLoading, _):
            return isLoading
        case .logged_out(_, let isLoading):
            return isLoading
        }
    }
    
    var isReady: Bool {
        switch self {
        case .initial(let isReady, _):
            return isReady
        case .logged_in(let isReady, _, _):
            return isReady
        case .logged_out(let isReady, _):
            return isReady
        }
    }
    
    static func == (prev: SealedSessionUIState, new: SealedSessionUIState) -> Bool {
        switch (prev, new) {
        case (.initial(let prevIsReady, let prevIsLoading), .initial(let newIsReady, let newIsLoading)):
            return prevIsReady == newIsReady && prevIsLoading == newIsLoading
        case (.logged_in(let prevIsReady, let prevIsLoading, let prevInfo), .logged_in(let newIsReady, let newIsLoading, let newInfo)):
            return prevIsReady == newIsReady && prevIsLoading == newIsLoading && prevInfo == newInfo
        case (.logged_out(let prevIsReady, let prevIsLoading), .logged_out(let newIsReady, let newIsLoading)):
            return prevIsReady == newIsReady && prevIsLoading == newIsLoading
        default:
            return false
        }
    }
}

enum SealedLaunchScreenUIState : Equatable {
    case initial
    case ready
    
    static func == (prev: SealedLaunchScreenUIState, new: SealedLaunchScreenUIState) -> Bool {
        switch (prev, new) {
        case (.initial, .initial):
            return true
        case (.ready, .ready):
            return true
        default:
            return false
        }
    }
}

struct SessionViewModelState: Equatable {
    var isReady: Bool = false
    var isLoading: Bool = false
    var info: UiUserInfo? = nil
    
    func toUIState() -> SealedSessionUIState {
        if isReady {
            if let unwrapedInfo = info {
                return SealedSessionUIState.logged_in(isReady: isReady,isLoading: isLoading, info: unwrapedInfo)
            } else {
                return SealedSessionUIState.logged_out(isReady: isReady, isLoading: isLoading)
            }
        } else {
            return SealedSessionUIState.initial(isReady: isReady, isLoading: isReady)
        }
    }
    
    func toLaunchScreenUIState() -> SealedLaunchScreenUIState {
        if isReady {
            return SealedLaunchScreenUIState.ready
        } else {
            return SealedLaunchScreenUIState.initial
        }
    }
}

class SessionViewModel {
    static let shared = SessionViewModel()
    
    // MARK: - properties
    private let helper = IOSSessionViewModelHelperWrapper().getHelper()
    
    // MARK: - ui state
    private let _viewModelState = CurrentValueSubject<SessionViewModelState, Never>(SessionViewModelState())
    var currentSessionUIState: SealedSessionUIState {
        return _viewModelState.value.toUIState()
    }
    var sessionUIState: AnyPublisher<SealedSessionUIState, Never> {
        return _viewModelState.map { state in state.toUIState() }.removeDuplicates().eraseToAnyPublisher()
    }
    var launchScreenUIState: AnyPublisher<SealedLaunchScreenUIState, Never> {
        return _viewModelState.map { state in state.toLaunchScreenUIState() }.removeDuplicates().eraseToAnyPublisher()
    }
    
    // MARK: - lifecycle
    init() {
        helper.collectSession { [weak self] session in
            guard let strongSelf = self else { return }
            strongSelf._viewModelState.send(SessionViewModelState(isReady: true, isLoading: strongSelf._viewModelState.value.isLoading, info: session))
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
