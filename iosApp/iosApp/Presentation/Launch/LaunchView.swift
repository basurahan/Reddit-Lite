//
//  LaunchViewController.swift
//  iosApp
//
//  Created by Apple on 10/13/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit

class LaunchView : UIView {
    
    // MARK: - ui components
    lazy var logo: UIImageView = {
        let image = UIImageView()
        image.image = UIImage(named: "RedditLiteIcon")
        image.contentMode = .scaleAspectFit
        image.translatesAutoresizingMaskIntoConstraints = false
        return image
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
        self.backgroundColor = .white
        self.addSubview(logo)
        
        NSLayoutConstraint.activate([
            logo.leadingAnchor.constraint(equalTo: self.safeAreaLayoutGuide.leadingAnchor),
            logo.trailingAnchor.constraint(equalTo: self.safeAreaLayoutGuide.trailingAnchor),
            logo.topAnchor.constraint(equalTo: self.safeAreaLayoutGuide.topAnchor),
            logo.bottomAnchor.constraint(equalTo: self.safeAreaLayoutGuide.bottomAnchor)
        ])
    }
}

#if DEBUG
import SwiftUI

@available(iOS 13, *)
struct LaunchViewPreview: PreviewProvider {
    static var previews: some View {
        LaunchView().showPreview()
    }
}
#endif
