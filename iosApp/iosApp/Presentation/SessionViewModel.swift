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
    
    // MARK: - properties
    private let helper = IOSSessionViewModelHelperWrapper().getHelper()
    
    // MARK: - ui state
    let onReady = CurrentValueSubject<Bool, Never>(false)
    let userInfo = CurrentValueSubject<UiUserInfo?, Never>(nil)
    
    
    // MARK: - class helper
    func leave() {
        helper.leave()
    }
}
