//
//  HomeViewController.swift
//  iosApp
//
//  Created by Apple on 9/19/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit
import Combine

class HomeViewController: UITabBarController, UITabBarControllerDelegate {
    
    // MARK: - properties
    private let pageId = "402d0c4d-a3e9-4766-8d03-467ffffe12f3"
    private let appEventsViewModel = AppEventsViewModel.shared
    private let sessionViewModel = SessionViewModel.shared
    private var cancellables = Set<AnyCancellable>()
    
    // MARK: - lifecycle
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .white
        self.delegate = self
        
        if #available(iOS 13.0, *) {
            let appearance = UITabBarAppearance()
            appearance.backgroundColor = .white
            tabBar.standardAppearance = appearance
            if #available(iOS 15.0, *) {
                tabBar.scrollEdgeAppearance = appearance
            }
        } else {
            tabBar.barTintColor = .white
        }
        
        appEventsViewModel.onSessionStarted
            .registerObserver(id: pageId, cancellables: &cancellables) { [weak self] username in
                guard let strongSelf = self else { return }
                strongSelf.showSnackbar(message: "Welcome \(username)")
            }
        
        setupViews()
    }
    
    private func setupViews() {
        let homeViewController = HomeHomeViewController()
        homeViewController.tabBarItem = UITabBarItem(title: "Home", image: UIImage(systemName: "house"), tag: 0)
        
        let chatViewController = ChatViewController()
        chatViewController.tabBarItem = UITabBarItem(title: "Chat", image: UIImage(systemName: "ellipsis.message"), tag: 1)
        
        let createPostViewController = CreateViewController()
        createPostViewController.tabBarItem = UITabBarItem(title: "Create", image: UIImage(systemName: "plus.circle"), tag: 2)
        
        let notificationViewController = NotificationViewController()
        notificationViewController.tabBarItem = UITabBarItem(title: "Notif", image: UIImage(systemName: "bell"), tag: 3)
        
        let profileViewController = ProfileViewController()
        let profileTabNavigationController = UINavigationController(rootViewController: profileViewController)
        profileTabNavigationController.tabBarItem = UITabBarItem(title: "Person", image: UIImage(systemName: "person"), tag: 4)
        
        self.viewControllers = [homeViewController, chatViewController, createPostViewController, notificationViewController, profileTabNavigationController]
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: false)
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: false)
    }
    
    // MARK: - ui events
    func tabBarController(_ tabBarController: UITabBarController, shouldSelect viewController: UIViewController) -> Bool {
        if let navigationController = viewController as? UINavigationController {
            if navigationController.viewControllers.first is ProfileViewController, case .logged_out(_, _) = self.sessionViewModel.currentSessionUIState {
                if let navigationController = self.navigationController {
                    let loginViewController = LoginViewController()
                    navigationController.pushViewController(loginViewController, animated: true)
                    return false
                }
            }
        }
        return true
    }
}
