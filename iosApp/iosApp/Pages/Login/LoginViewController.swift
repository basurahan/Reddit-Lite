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
    
    deinit {
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
                guard let strongSelf = self else { return }
                strongSelf.customView.loadingIndicator.isHidden = !isLoadng
                strongSelf.customView.btLogin.isEnabled = !isLoadng
            }
            .store(in: &cancellables)
        
        viewModel.$message
            .receive(on: DispatchSerialQueue.main)
            .sink { [weak self] message in
                guard let unWrapMessage = message else { return }
                self?.showSnackbar(message: unWrapMessage)
            }
            .store(in: &cancellables)
        
        viewModel.$usernameError
            .receive(on: DispatchSerialQueue.main)
            .sink { [weak self] error in
                guard let strongSelf = self, let unWrapError = error else { return }
                strongSelf.customView.tfUsername.showError(message: unWrapError)
            }
            .store(in: &cancellables)
        
        viewModel.$passwordError
            .receive(on: DispatchSerialQueue.main)
            .sink { [weak self] error in
                guard let strongSelf = self, let unWrapError = error else { return }
                strongSelf.customView.tfPassword.showError(message: unWrapError)
            }
            .store(in: &cancellables)
        
        viewModel.onSuccess
            .receive(on: DispatchQueue.main)
            .sink { [weak self] in
                if let navController = self?.navigationController {
                    navController.popToRootViewController(animated: true)
                }
                
                DispatchQueue.main.asyncAfter(deadline: .now() + 0.3) {
                    guard let windowScene = UIApplication.shared.connectedScenes.first as? UIWindowScene else { return }
                    guard let rootViewController = windowScene.windows.first?.rootViewController as? UINavigationController else { return }
                    guard let homeViewController = rootViewController.viewControllers.first as? UITabBarController else { return }

                    homeViewController.selectedIndex = 0
                }
            }
            .store(in: &cancellables)
    }
}
