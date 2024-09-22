//
//  LoginView.swift
//  iosApp
//
//  Created by Apple on 9/22/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit

class LoginView: UIView {
    
    lazy var tfUsername: UITextField = {
        let textField = UITextField()
        textField.placeholder = "Username"
        textField.borderStyle = .roundedRect
        textField.translatesAutoresizingMaskIntoConstraints = false
        return textField
    }()
    
    lazy var tfPassword: UITextField = {
        let textField = UITextField()
        textField.placeholder = "Password"
        textField.borderStyle = .roundedRect
        textField.translatesAutoresizingMaskIntoConstraints = false
        return textField
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
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        self.backgroundColor = .white
        setupView()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func setupView() {
        self.addSubview(tfUsername)
        self.addSubview(tfPassword)
        self.addSubview(btLogin)
        self.addSubview(btRegister)
        self.addSubview(loadingIndicator)
        
        NSLayoutConstraint.activate([
            tfUsername.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 16),
            tfUsername.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -16),
            tfUsername.centerYAnchor.constraint(equalTo: self.centerYAnchor, constant: -100),
            
            tfPassword.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 16),
            tfPassword.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -16),
            tfPassword.topAnchor.constraint(equalTo: tfUsername.bottomAnchor, constant: 16),
            
            btLogin.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 16),
            btLogin.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -16),
            btLogin.topAnchor.constraint(equalTo: tfPassword.bottomAnchor, constant: 16),
            
            btRegister.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -16),
            btRegister.topAnchor.constraint(equalTo: btLogin.bottomAnchor, constant: 16),
            
            loadingIndicator.centerXAnchor.constraint(equalTo: btLogin.centerXAnchor),
            loadingIndicator.centerYAnchor.constraint(equalTo: btLogin.centerYAnchor)
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
