//
//  Snackbar.swift
//  iosApp
//
//  Created by Apple on 9/22/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit

class Snackbar: UIView {
    
    // MARK: - ui components
    lazy var label: UILabel = {
        let label = UILabel()
        label.textColor = .white
        label.textAlignment = .left
        label.numberOfLines = 0
        label.lineBreakMode = .byWordWrapping
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    // MARK: - lifecycle
    init(message: String) {
        super.init(frame: .zero)
        
        // set the background color of the snackbar
        self.backgroundColor = UIColor.black.withAlphaComponent(0.5)
        self.layer.cornerRadius = 8
        self.layer.masksToBounds = true
        
        // set the message
        label.text = message
        self.addSubview(label)
        
        NSLayoutConstraint.activate([
            label.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 16),
            label.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -16),
            label.topAnchor.constraint(equalTo: self.topAnchor, constant: 8),
            label.bottomAnchor.constraint(equalTo: self.bottomAnchor, constant: -8)
        ])
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}

extension UIViewController {
    
    func showSnackbar(message: String, duration: TimeInterval = 3.0) {
        // add the snackbar to the view controller
        let snackbar = Snackbar(message: message)
        snackbar.translatesAutoresizingMaskIntoConstraints = false
        
        // hide it so we can calculate the hide using autolayout
        snackbar.isHidden = true
        self.view.addSubview(snackbar)
        
        let bottomConstraint = snackbar.bottomAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.bottomAnchor)
    
        // hide it below the view controller screen
        NSLayoutConstraint.activate([
            snackbar.leadingAnchor.constraint(equalTo: self.view.leadingAnchor, constant: 16),
            snackbar.trailingAnchor.constraint(equalTo: self.view.trailingAnchor, constant: -16),
            bottomConstraint
        ])
        
        // make sure to get the updated constraint
        self.view.layoutIfNeeded()
        
        // hide the snackbar using translation instead of visibility
        let hiddenTranslation = snackbar.frame.height + self.view.safeAreaInsets.bottom
        bottomConstraint.constant = hiddenTranslation
        snackbar.isHidden = false
        
        // make sure to get the updated constraint
        self.view.layoutIfNeeded()
        
        var marginBottom = 20.0
        if let tabBarController = self as? UITabBarController {
            let tabBarHeight = tabBarController.tabBar.frame.height
            let bottomInset = tabBarController.tabBar.safeAreaInsets.bottom
            marginBottom -= bottomInset
            marginBottom += tabBarHeight
        }
        
        // animate showing of snackbar
        let showTranslation = -(hiddenTranslation + marginBottom)
        UIView.animate(
            withDuration: 0.5,
            animations: {
                snackbar.transform = CGAffineTransform(translationX: 0, y: showTranslation)
            }
        ) { _ in
            DispatchQueue.main.asyncAfter(
                deadline: .now() + duration
            ) {
                UIView.animate(
                    withDuration: 0.5,
                    animations: {
                        snackbar.transform =  .identity
                    }
                ) { _ in
                    snackbar.removeFromSuperview()
                }
            }
        }
    }
}

#if DEBUG
import SwiftUI

@available(iOS 13, *)
struct SnackBarPreview: PreviewProvider {
    static var previews: some View {
        Snackbar(message: "Someting went wrong").showPreview()
    }
}
#endif
