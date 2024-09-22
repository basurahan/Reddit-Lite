//
//  LoginViewController.swift
//  iosApp
//
//  Created by Apple on 9/19/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit
import Combine

class LoginViewController: UIViewController {
    
    private let customView = LoginView()
    private let viewModel = LoginViewModel()
    private var cancellables = Set<AnyCancellable>()
    
    override func loadView() {
        self.view = customView
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupViews()
        setupDataObservers()
    }
    
    private func setupViews() {
        customView.btLogin.addTarget(self, action: #selector(onLoginClick), for: .touchUpInside)
    }
    
    @objc private func onLoginClick() {
        let username = customView.tfUsername.text ?? ""
        let password = customView.tfPassword.text ?? ""
        viewModel.login(username: username, password: password)
    }
    
    private func setupDataObservers() {
        viewModel.$isLoading
            .receive(on: RunLoop.main)
            .sink{ [weak self] isLoadng in
                self?.customView.loadingIndicator.isHidden = !isLoadng
                self?.customView.btLogin.isEnabled = !isLoadng
            }
            .store(in: &cancellables)
    }
}
