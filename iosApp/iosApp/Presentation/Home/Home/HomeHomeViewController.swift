//
//  HomeHomeViewController.swift
//  iosApp
//
//  Created by Apple on 9/19/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit
import Combine

class HomeHomeViewController: UIViewController {
    
    // MARK: - properties
    private let pageId = "b1c414ef-d03c-4ab8-82b6-86c59fefcb9a"
    private let sessionViewModel = SessionViewModel.shared
    private var cancellables = Set<AnyCancellable>()
    
    // MARK: - lifecycle
    override func loadView() {
        self.view = HomeHomeView()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        sessionViewModel.session
            .registerObserver(id: pageId, cancellables: &cancellables) { [weak self] value in
                self?.showSnackbar(message: "Welcome \(value.username)")
            }
    }
}
