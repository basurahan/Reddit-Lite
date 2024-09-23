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
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        viewModel.leave()
    }
    
    private func setupViews() {
        customView.btLogin.addTarget(self, action: #selector(onLoginClick), for: .touchUpInside)
        customView.tfUsername.textField.addTarget(self, action: #selector(onUsernameChange(_:)), for: .editingChanged)
        customView.tfPassword.textField.addTarget(self, action: #selector(onPasswordChange(_:)), for: .editingChanged)
    }
    
    @objc private func onLoginClick() {
        let username = customView.tfUsername.getText() ?? ""
        let password = customView.tfPassword.getText() ?? ""
        viewModel.login(username: username, password: password)
    }
    
    @objc private func onUsernameChange(_ textfield: UITextField) {
        customView.tfUsername.clearError()
        customView.tfPassword.clearError()
    }
    
    @objc private func onPasswordChange(_ textfield: UITextField) {
        customView.tfUsername.clearError()
        customView.tfPassword.clearError()
    }
    
    private func setupDataObservers() {
        viewModel.$isLoading
            .receive(on: DispatchQueue.main)
            .sink{ [weak self] isLoadng in
                self?.customView.loadingIndicator.isHidden = !isLoadng
                self?.customView.btLogin.isEnabled = !isLoadng
            }
            .store(in: &cancellables)
        
        viewModel.$message
            .receive(on: RunLoop.main)
            .sink { [weak self] message in
                if (message == nil) {
                    return
                }
                self?.showSnackbar(message: message!)
            }
            .store(in: &cancellables)
        
        viewModel.$usernameError
            .receive(on: DispatchSerialQueue.main)
            .sink { [weak self] error in
                if (error == nil) {
                    return
                }
                self?.customView.tfUsername.showError(message: error!)
            }
            .store(in: &cancellables)
        
        viewModel.$passwordError
            .receive(on: DispatchSerialQueue.main)
            .sink { [weak self] error in
                if (error == nil) {
                    return
                }
                self?.customView.tfPassword.showError(message: error!)
            }
            .store(in: &cancellables)
    }
}
