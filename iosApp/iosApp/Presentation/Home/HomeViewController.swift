//
//  HomeViewController.swift
//  iosApp
//
//  Created by Apple on 9/19/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit

class HomeViewController: UITabBarController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .white
        
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
        
        let profileViewController = ProfileViewController(nibName: nil, bundle: nil) {
            if let navigationController = self.navigationController {
                let loginViewController = LoginViewController()
                navigationController.pushViewController(loginViewController, animated: true)
            }
        }
        let profileTabNavigationController = UINavigationController(rootViewController: profileViewController)
        profileTabNavigationController.tabBarItem = UITabBarItem(title: "Person", image: UIImage(systemName: "person"), tag: 4)
        
        self.viewControllers = [homeViewController, chatViewController, createPostViewController, notificationViewController, profileTabNavigationController]
    }
}
