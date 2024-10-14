//
//  LaunchViewController.swift
//  iosApp
//
//  Created by Apple on 10/13/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit

class LaunchViewController: UIViewController {
    
    let customView = LaunchView()
    
    override func loadView() {
        self.view = customView
    }
}
