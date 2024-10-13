//
//  SessionViewModel.swift
//  iosApp
//
//  Created by Apple on 10/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import shared

class SessionViewModel {
    static let shared = SessionViewModel()
    
    let helper = IOSSessionViewModelHelperWrapper().getHelper()
    let onReady = CurrentValueSubject<Bool, Never>(false)
    let userInfo = CurrentValueSubject<UiUserInfo?, Never>(nil)
    
    init() {
        helper.collectSession { [weak self] info in
            self?.userInfo.send(info)
            self?.onReady.send(true)
        }
    }
    
    deinit {
        // TODO check when is the best way to cancel coroutines
        helper.leave()
    }
}
