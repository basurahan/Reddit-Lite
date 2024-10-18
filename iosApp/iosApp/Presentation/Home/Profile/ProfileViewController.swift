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
    
    // MARK: - dialogs
    lazy var confirmLogoutDialog: UIAlertController = {
        let dialog = UIAlertController()
        dialog.title = "Please Confirm"
        dialog.message = "Do you really want to logout?"
        dialog.addAction(UIAlertAction(title: "Cancel", style: .cancel, handler: nil))
        dialog.addAction(UIAlertAction(title: "Logout", style: .destructive, handler: { _ in self.viewModel.logout() }))
        return dialog
    }()
    
    // MARK: - lifecycle
    override func loadView() {
        self.view = customView
        self.navigationItem.rightBarButtonItem = UIBarButtonItem(title: "Logout", style: .plain, target: self, action: #selector(onLogoutClick))
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupDataObservers()
    }
    
    private func setupDataObservers() {
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
                    strongSelf.customView.avatar.image = generateAvatar(initials: info.userInfo.initial, size: 200)
                }
            }
            .store(in: &cancellables)
        
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
        present(confirmLogoutDialog, animated: true, completion: nil)
    }
}
