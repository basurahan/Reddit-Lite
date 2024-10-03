//
//  Snackbar.swift
//  iosApp
//
//  Created by Apple on 9/22/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit

class Snackbar: UIView {
    
    lazy var label: UILabel = {
        let label = UILabel()
        label.textColor = .white
        label.textAlignment = .left
        label.numberOfLines = 1
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    init(_ message: String) {
        super.init(frame: .zero)
        
        self.backgroundColor = UIColor.black.withAlphaComponent(0.5)
        self.layer.cornerRadius = 8
        self.layer.masksToBounds = true
        
        label.text = message
        
        addSubview(label)
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
        let snackbar = Snackbar(message)
        
        snackbar.translatesAutoresizingMaskIntoConstraints = false
        view.addSubview(snackbar)
        
        NSLayoutConstraint.activate([
            snackbar.leadingAnchor.constraint(equalTo: self.view.leadingAnchor, constant: 16),
            snackbar.trailingAnchor.constraint(equalTo: self.view.trailingAnchor, constant: -16),
            snackbar.bottomAnchor.constraint(equalTo: self.view.bottomAnchor, constant: 100)
        ])
        
        view.layoutIfNeeded()
        
        UIView.animate(withDuration: 0.5, animations: {
            snackbar.transform = CGAffineTransform(translationX: 0, y: -120)
        }) { _ in
            DispatchQueue.main.asyncAfter(deadline: .now() + duration) {
                UIView.animate(withDuration: 0.5, animations: {
                    snackbar.transform = CGAffineTransform.identity
                }) { _ in
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
        Snackbar("Someting went wrong").showPreview()
    }
}
#endif
