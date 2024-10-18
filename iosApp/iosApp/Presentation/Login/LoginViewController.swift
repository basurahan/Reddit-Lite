//
//  LoginViewController.swift
//  iosApp
//
//  Created by Apple on 9/19/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit
import Combine

class LoginViewController: BaseViewController, UITextFieldDelegate {
    
    // MARK: - properties
    private let customView = LoginView()
    private let viewModel = LoginViewModel()
    private let appEventsViewModel = AppEventsViewModel.shared
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
        viewModel.cancelCoroutines()
        NotificationCenter.default.removeObserver(self, name: UIResponder.keyboardWillShowNotification, object: nil)
        NotificationCenter.default.removeObserver(self, name: UIResponder.keyboardWillHideNotification, object: nil)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        NotificationCenter.default.addObserver(self, selector: #selector(onKeyboardShown), name: UIResponder.keyboardWillShowNotification, object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(onKeyboardHidden), name: UIResponder.keyboardWillHideNotification, object: nil)
    }
    
    // MARK: - ui events
    private func setupViews() {
        customView.btLogin.addTarget(self, action: #selector(onLoginClick), for: .touchUpInside)
        customView.tfUsername.textField.addTarget(self, action: #selector(onUsernameChange(_:)), for: .editingChanged)
        customView.tfPassword.textField.addTarget(self, action: #selector(onPasswordChange(_:)), for: .editingChanged)
        
        customView.tfUsername.setDelegate(self)
        customView.tfPassword.setDelegate(self)
    }
    
    @objc private func onLoginClick() {
        let username = customView.tfUsername.getText() ?? ""
        let password = customView.tfPassword.getText() ?? ""
        viewModel.login(username: username, password: password)
    }
    
    @objc private func onUsernameChange(_ textfield: UITextField) {
        customView.tfUsername.setError(message: nil)
        customView.tfPassword.setError(message: nil)
    }
    
    @objc private func onPasswordChange(_ textfield: UITextField) {
        customView.tfUsername.setError(message: nil)
        customView.tfPassword.setError(message: nil)
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
        viewModel.uiState
            .receive(on: DispatchQueue.main)
            .sink{ [weak self] state in
                guard let strongSelf = self else { return }
                strongSelf.bindLoadingDialogState(isLoading: state.isLoading)
                switch state {
                case .validation(_, let username, let password):
                    strongSelf.customView.tfUsername.setError(message: username)
                    strongSelf.customView.tfPassword.setError(message: password)
                default:
                    break
                }
            }
            .store(in: &cancellables)
        
        viewModel.onMessageReceived
            .receive(on: DispatchQueue.main)
            .sink { [weak self] message in
                guard let strongSelf = self else { return }
                strongSelf.showSnackbar(message: message)
            }
            .store(in: &cancellables)
        
        viewModel.onSuccess
            .receive(on: DispatchQueue.main)
            .sink { [weak self] username in
                guard let strongSelf = self else { return }
                
                if let navController = strongSelf.navigationController {
                    navController.popToRootViewController(animated: true)
                }
                
                guard let windowScene = UIApplication.shared.connectedScenes.first as? UIWindowScene else { return }
                guard let rootViewController = windowScene.windows.first?.rootViewController as? UINavigationController else { return }
                guard let homeViewController = rootViewController.viewControllers.first as? UITabBarController else { return }

                homeViewController.selectedIndex = 0
                
                strongSelf.appEventsViewModel.onSessionStarted.emit(username)
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
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()  // Hides the keyboard
        return true
    }
}
