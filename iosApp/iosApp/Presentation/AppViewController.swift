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
    
    let sessionViewModel = SessionViewModel.shared
    var cancellables = Set<AnyCancellable>()
    
    override init(rootViewController: UIViewController) {
        super.init(rootViewController: rootViewController)
        
        sessionViewModel.onReady.removeDuplicates()
            .receive(on: DispatchQueue.main)
            .sink { _ in
                self.setViewControllers([HomeViewController()], animated: true)
            }
            .store(in: &cancellables)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
