//
//  ProfileViewModel.swift
//  iosApp
//
//  Created by Apple on 10/16/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine

class ProfileViewModel : ObservableObject {
    
    // MARK: - properties
    let sessionViewModel = SessionViewModel.shared
    
    // MARK: - ui events
    let onLogoutSuccess = PassthroughSubject<Void, Never>()
    
    // MARK: - ui state
    @Published var isLoading: Bool = false
    
    // MARK: - class helper
    func logout() {
        isLoading = true
        sessionViewModel.logout { [weak self] in
            guard let strongSelf = self else { return }
            strongSelf.isLoading = false
            strongSelf.onLogoutSuccess.send()
        }
    }
}
