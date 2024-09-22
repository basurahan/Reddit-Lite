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

class ProfileViewController: UIViewController {
    
    var loginClick: (() -> Void)
    
    private let customView = ProfileView()
    
    init(nibName: String?, bundle: Bundle?, loginClick: @escaping () -> Void) {
        self.loginClick = loginClick
        super.init(nibName: nibName, bundle: bundle)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func loadView() {
        self.view = customView
        customView.button.addTarget(self, action: #selector(onLoginClick), for: .touchUpInside)
    }
    
    @objc func onLoginClick() {
        loginClick()
    }
}
