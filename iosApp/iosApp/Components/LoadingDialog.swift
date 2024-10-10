//
//  LoadingDialog.swift
//  iosApp
//
//  Created by Apple on 10/10/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit

class LoadingDialog : UIView {
    
    // MARK: - ui components
    lazy var loadingIndicator: UIActivityIndicatorView = {
        let loading = UIActivityIndicatorView(style: .large)
        loading.startAnimating()
        loading.translatesAutoresizingMaskIntoConstraints = false
        return loading
    }()
    
    lazy var container: UIView = {
        let view = UIView()
        view.backgroundColor = .white
        view.layer.cornerRadius = 10
        view.layer.masksToBounds = true
        
        view.addSubview(loadingIndicator)
        
        NSLayoutConstraint.activate([
            loadingIndicator.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 16),
            loadingIndicator.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -16),
            loadingIndicator.topAnchor.constraint(equalTo: view.topAnchor, constant: 16),
            loadingIndicator.bottomAnchor.constraint(equalTo: view.bottomAnchor, constant: -16)
        ])
        
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    // MARK: - lifecycle
    override init(frame: CGRect) {
        super.init(frame: frame)
        setupViews()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func setupViews() {
        self.backgroundColor = UIColor.black.withAlphaComponent(0.1)
        self.addSubview(container)
        
        NSLayoutConstraint.activate([
            container.centerXAnchor.constraint(equalTo: self.centerXAnchor),
            container.centerYAnchor.constraint(equalTo: self.centerYAnchor)
        ])
    }
}

#if DEBUG
import SwiftUI

@available(iOS 13, *)
struct LoadingDialogPreview: PreviewProvider {
    static var previews: some View {
        LoadingDialog().showPreview()
    }
}
#endif
