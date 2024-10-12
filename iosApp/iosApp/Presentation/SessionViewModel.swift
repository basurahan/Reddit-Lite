//
//  SessionViewModel.swift
//  iosApp
//
//  Created by Apple on 10/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import shared

class SessionViewModel {
    static let shared = SessionViewModel()
    
    let session = EventBus<UiUser>()
    
    func startSessionBy(_ user: UiUser) {
        session.emit(user)
    }
    
    func endSession() {
        session.emit(nil)
    }
}
