//
//  ProfileViewModel.swift
//  iosApp
//
//  Created by Apple on 10/16/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine

class ProfileViewModel {
    
    // MARK: - properties
    let sessionViewModel = SessionViewModel.shared
    
    // MARK: - ui events
    let onLogoutSuccess = PassthroughSubject<Void, Never>()
    let onLoading = PassthroughSubject<Bool, Never>()
    
    // MARK: - class helper
    func logout() {
        onLoading.send(true)
        sessionViewModel.logout { [weak self] in
            guard let strongSelf = self else { return }
            strongSelf.onLoading.send(false)
            strongSelf.onLogoutSuccess.send()
        }
    }
}
