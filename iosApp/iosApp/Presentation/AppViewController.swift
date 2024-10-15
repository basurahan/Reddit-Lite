//
//  RootViewController.swift
//  iosApp
//
//  Created by Apple on 10/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit
import Combine

class AppViewController: UINavigationController {
    
    // MARK: - properties
    let sessionViewModel = SessionViewModel.shared
    var cancellables = Set<AnyCancellable>()
    
    // MARK: - lifecycle
    override init(rootViewController: UIViewController) {
        super.init(rootViewController: rootViewController)
        
        sessionViewModel.uiState
            .receive(on: DispatchQueue.main)
            .sink { [weak self] state in
                guard let strongSelf = self else { return }
                if !(state is Initial) {
                    strongSelf.setViewControllers([HomeViewController()], animated: true)
                }
            }
            .store(in: &cancellables)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    deinit {
        sessionViewModel.cancelCoroutines()
    }
}
