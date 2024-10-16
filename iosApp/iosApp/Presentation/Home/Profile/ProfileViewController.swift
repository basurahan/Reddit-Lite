//
//  ProfileViewController.swift
//  iosApp
//
//  Created by Apple on 9/19/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit
import Combine
import shared

class ProfileViewController: BaseViewController {
    
    // MARK: - properties
    private let customView = ProfileView()
    private let viewModel = ProfileViewModel()
    private let sessionViewModel = SessionViewModel.shared
    private var cancellables = Set<AnyCancellable>()
    
    // MARK: - lifecycle
    override func loadView() {
        self.view = customView
        self.navigationItem.rightBarButtonItem = UIBarButtonItem(title: "Logout", style: .plain, target: self, action: #selector(onLogoutClick))
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupStateObservers()
        setupEventObservers()
    }
    
    private func setupStateObservers() {
        viewModel.$isLoading
            .receive(on: DispatchQueue.main)
            .sink { [weak self] isLoading in
                guard let strongSelf = self else { return }
                if isLoading {
                    strongSelf.showLoadingDialog()
                } else {
                    strongSelf.hideLoadingDialog()
                }
            }
            .store(in: &cancellables)
        
        sessionViewModel.uiState
            .receive(on: DispatchQueue.main)
            .sink { [weak self] state in
                guard let strongSelf = self else { return }
                if let info = state as? LoggedIn {
                    strongSelf.customView.username.text = info.userInfo.username
                }
            }
            .store(in: &cancellables)
    }
    
    private func setupEventObservers() {
        viewModel.onLogoutSuccess
            .receive(on: DispatchQueue.main)
            .sink { [weak self] in
                guard let strongSelf = self else { return }
                strongSelf.tabBarController?.selectedIndex = 0
            }
            .store(in: &cancellables)
    }
    
    // MARK: - ui events
    @objc func onLogoutClick() {
        viewModel.logout()
    }
}
