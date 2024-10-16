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
    
    var loginClick: (() -> Void)
    
    // MARK: - properties
    private let customView = ProfileView()
    private let viewModel = ProfileViewModel()
    private var cancellables = Set<AnyCancellable>()
    
    // MARK: - lifecycle
    init(nibName: String?, bundle: Bundle?, loginClick: @escaping () -> Void) {
        self.loginClick = loginClick
        super.init(nibName: nibName, bundle: bundle)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func loadView() {
        self.view = customView
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        setupStateObservers()
        setupEventObservers()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.tabBarController?.navigationItem.rightBarButtonItem = UIBarButtonItem(title: "Logout", style: .plain, target: self, action: #selector(onLogoutClick))
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        self.tabBarController?.navigationItem.rightBarButtonItem = nil
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
