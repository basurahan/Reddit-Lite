//
//  LoginView.swift
//  iosApp
//
//  Created by Apple on 9/22/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit

class LoginView: UIView {
    
    var activeTextField: UITextField? = nil
    
    // MARK: - ui components
    lazy var tfUsername: TextFieldWithValidation = {
        let textField = TextFieldWithValidation(hint: "Username", isPrivate: false, rightView: nil)
        textField.translatesAutoresizingMaskIntoConstraints = false
        return textField
    }()
    
    lazy var tfPassword: TextFieldWithValidation = {
        let textField = TextFieldWithValidation(hint: "Password", isPrivate: true, rightView: btPasswordVisibility)
        textField.translatesAutoresizingMaskIntoConstraints = false
        return textField
    }()
    
    lazy var btPasswordVisibility: PasswordToggle = {
        let button = PasswordToggle() {
            self.tfPassword.toggleSecurityText()
        }
        button.translatesAutoresizingMaskIntoConstraints = false
        return button
    }()
    
    lazy var btLogin: UIButton = {
        var config = UIButton.Configuration.filled()
            config.title = "Login"
            config.baseBackgroundColor = .systemBlue
            config.baseForegroundColor = .white
            
        let button = UIButton(configuration: config)
        button.translatesAutoresizingMaskIntoConstraints = false
        return button
    }()
    
    lazy var btRegister: UIButton = {
        let button = UIButton(type: .system)
        button.setTitle("Register", for: .normal)
        button.translatesAutoresizingMaskIntoConstraints = false
        return button
    }()
    
    lazy var loadingIndicator: UIActivityIndicatorView = {
        let ai = UIActivityIndicatorView()
        ai.startAnimating()
        ai.isHidden = true
        ai.translatesAutoresizingMaskIntoConstraints = false
        return ai
    }()
    
    lazy var scrollView: UIScrollView = {
        let scrollView = UIScrollView()
        scrollView.addSubview(container)
        
        NSLayoutConstraint.activate([
            container.leadingAnchor.constraint(equalTo: scrollView.leadingAnchor),
            container.trailingAnchor.constraint(equalTo: scrollView.trailingAnchor),
            container.topAnchor.constraint(equalTo: scrollView.topAnchor),
            container.bottomAnchor.constraint(equalTo: scrollView.bottomAnchor),
            container.widthAnchor.constraint(equalTo: scrollView.widthAnchor)
        ])
        
        scrollView.translatesAutoresizingMaskIntoConstraints = false
        return scrollView
    }()
    
    lazy var container: UIView = {
        let view = UIView()
        view.addSubview(tfUsername)
        view.addSubview(tfPassword)
        view.addSubview(btLogin)
        view.addSubview(btRegister)
        view.addSubview(loadingIndicator)
        
        NSLayoutConstraint.activate([
            tfUsername.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 16),
            tfUsername.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -16),
            tfUsername.topAnchor.constraint(equalTo: view.topAnchor, constant: 600),
            
            tfPassword.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 16),
            tfPassword.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -16),
            tfPassword.topAnchor.constraint(equalTo: tfUsername.bottomAnchor, constant: 8),
            
            btLogin.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 16),
            btLogin.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -16),
            btLogin.topAnchor.constraint(equalTo: tfPassword.bottomAnchor, constant: 8),
            
            btRegister.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -16),
            btRegister.topAnchor.constraint(equalTo: btLogin.bottomAnchor, constant: 16),
            btRegister.bottomAnchor.constraint(equalTo: view.bottomAnchor),
            
            loadingIndicator.centerXAnchor.constraint(equalTo: btLogin.centerXAnchor),
            loadingIndicator.centerYAnchor.constraint(equalTo: btLogin.centerYAnchor)
        ])
        
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    // MARK: - lifecycle
    override init(frame: CGRect) {
        super.init(frame: frame)
        self.backgroundColor = .white
        setupView()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func setupView() {
        self.addSubview(scrollView)
        
        NSLayoutConstraint.activate([
            scrollView.leadingAnchor.constraint(equalTo: self.leadingAnchor),
            scrollView.trailingAnchor.constraint(equalTo: self.trailingAnchor),
            scrollView.topAnchor.constraint(equalTo: self.topAnchor),
            scrollView.bottomAnchor.constraint(equalTo: self.bottomAnchor)
        ])
    }
}

#if DEBUG
import SwiftUI

@available(iOS 13, *)
struct LoginViewPreview: PreviewProvider {
    static var previews: some View {
        LoginView().showPreview()
    }
}
#endif
