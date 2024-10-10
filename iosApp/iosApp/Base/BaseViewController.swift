//
//  BaseViewController.swift
//  iosApp
//
//  Created by Apple on 10/10/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit

class BaseViewController : UIViewController {
    
    private var loadingDialog: LoadingDialog? = nil
    
    func showLoadingDialog() {
        if loadingDialog == nil {
            loadingDialog = LoadingDialog()
        }
        
        guard let unwrapLoadingDialog = loadingDialog else { return }
        
        unwrapLoadingDialog.translatesAutoresizingMaskIntoConstraints = false
        
        self.view.addSubview(unwrapLoadingDialog)
        NSLayoutConstraint.activate([
            unwrapLoadingDialog.leadingAnchor.constraint(equalTo: self.view.leadingAnchor),
            unwrapLoadingDialog.trailingAnchor.constraint(equalTo: self.view.trailingAnchor),
            unwrapLoadingDialog.topAnchor.constraint(equalTo: self.view.topAnchor),
            unwrapLoadingDialog.bottomAnchor.constraint(equalTo: self.view.bottomAnchor)
        ])
    }
    
    func hideLoadingDialog() {
        loadingDialog?.removeFromSuperview()
    }
}
