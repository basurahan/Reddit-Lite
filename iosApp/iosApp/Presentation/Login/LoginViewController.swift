//
//  LoginViewController.swift
//  iosApp
//
//  Created by Apple on 9/19/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit
import Combine

class LoginViewController: UIViewController, UITextFieldDelegate {
    
    // MARK: - properties
    private let customView = LoginView()
    private let viewModel = LoginViewModel()
    private var cancellables = Set<AnyCancellable>()
    
    private var activeTextField: UITextField? = nil
    
    // MARK: - lifecycle
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
        NotificationCenter.default.removeObserver(self, name: UIResponder.keyboardWillShowNotification, object: nil)
        NotificationCenter.default.removeObserver(self, name: UIResponder.keyboardWillHideNotification, object: nil)
    }
    
    // MARK: - ui events
    private func setupViews() {
        customView.btLogin.addTarget(self, action: #selector(onLoginClick), for: .touchUpInside)
        customView.tfUsername.textField.addTarget(self, action: #selector(onUsernameChange(_:)), for: .editingChanged)
        customView.tfPassword.textField.addTarget(self, action: #selector(onPasswordChange(_:)), for: .editingChanged)
        
        customView.tfUsername.setDelegate(self)
        customView.tfPassword.setDelegate(self)
        NotificationCenter.default.addObserver(self, selector: #selector(onKeyboardShown), name: UIResponder.keyboardWillShowNotification, object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(onKeyboardHidden), name: UIResponder.keyboardWillHideNotification, object: nil)
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
    
    @objc func onKeyboardShown(notification: NSNotification) {
        if let keyboardFrame = notification.userInfo?[UIResponder.keyboardFrameEndUserInfoKey] as? NSValue {
            let keyboardSize = keyboardFrame.cgRectValue.size
            let bottomInset = keyboardSize.height
            
            // add the keyboard height as inset to the scrollview so the user can scroll through all the items
            customView.scrollView.contentInset = UIEdgeInsets(top: 0, left: 0, bottom: bottomInset, right: 0)
            customView.scrollView.scrollIndicatorInsets = customView.scrollView.contentInset
            
            // If there's a currently focused text field, scroll it into view
            if let activeField = activeTextField {
                let visibleRect = customView.scrollView.frame.inset(by: customView.scrollView.contentInset)
                let fieldFrame = activeField.convert(activeField.bounds, to: customView.scrollView)

                if !visibleRect.contains(fieldFrame.origin) {
                    customView.scrollView.scrollRectToVisible(fieldFrame, animated: true)
                }
            }
        }
    }
    
    @objc func onKeyboardHidden() {
        customView.scrollView.contentInset = .zero
        customView.scrollView.scrollIndicatorInsets = .zero
    }
    
    // MARK: - data observers
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
            .receive(on: DispatchQueue.main)
            .sink { [weak self] message in
                guard let unWrapMessage = message else { return }
                self?.showSnackbar(message: unWrapMessage)
            }
            .store(in: &cancellables)
        
        viewModel.$usernameError
            .receive(on: DispatchQueue.main)
            .sink { [weak self] error in
                guard let strongSelf = self, let unWrapError = error else { return }
                strongSelf.customView.tfUsername.showError(message: unWrapError)
            }
            .store(in: &cancellables)
        
        viewModel.$passwordError
            .receive(on: DispatchQueue.main)
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
                
                guard let windowScene = UIApplication.shared.connectedScenes.first as? UIWindowScene else { return }
                guard let rootViewController = windowScene.windows.first?.rootViewController as? UINavigationController else { return }
                guard let homeViewController = rootViewController.viewControllers.first as? UITabBarController else { return }

                homeViewController.selectedIndex = 0
            }
            .store(in: &cancellables)
    }
    
    // MARK: - delegates
    func textFieldDidBeginEditing(_ textField: UITextField) {
        activeTextField = textField
    }

    func textFieldDidEndEditing(_ textField: UITextField) {
        activeTextField = nil
    }
    
    // MARK: - custom behaviour
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        // Dismiss the keyboard when the user taps anywhere outside the text fields
        self.view.endEditing(true)
        
        // Call the superclass implementation if necessary
        //super.touchesBegan(touches, with: event)
    }
}
