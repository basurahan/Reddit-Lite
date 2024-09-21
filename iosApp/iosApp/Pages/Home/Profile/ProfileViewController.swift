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
    private let viewModel = LoginViewModelHelper().getViewModel()
    //private let collector = Tester()
    
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
        //loginClick()
        viewModel.login(username: "dev.renz", password: "S@langa1998")
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        //viewModel.cancelData()
    }
}
