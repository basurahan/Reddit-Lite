//
//  ProfileView.swift
//  iosApp
//
//  Created by Apple on 9/19/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit

class ProfileView: UIView {

    // MARK: - ui components
    lazy var scrollView: UIScrollView = {
        let scrollView = UIScrollView()
        scrollView.delaysContentTouches = false
        scrollView.addSubview(container)
        
        NSLayoutConstraint.activate([
            container.leadingAnchor.constraint(equalTo: scrollView.leadingAnchor),
            container.trailingAnchor.constraint(equalTo: scrollView.trailingAnchor),
            container.topAnchor.constraint(equalTo: scrollView.topAnchor),
            container.bottomAnchor.constraint(equalTo: scrollView.bottomAnchor),
            container.widthAnchor.constraint(equalTo: scrollView.widthAnchor)
        ])
        
        scrollView.translatesAutoresizingMaskIntoConstraints = false
        return scrollView
    }()
    
    lazy var container: UIView = {
        let view = UIView()
        view.addSubview(avatarContainer)
        view.addSubview(username)
        view.addSubview(editInformation)
        view.addSubview(divider)
        view.addSubview(aboutTitle)
        view.addSubview(aboutBody)
        
        NSLayoutConstraint.activate([
            avatarContainer.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor, constant: 80),
            avatarContainer.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            avatarContainer.heightAnchor.constraint(equalToConstant: 200),
            avatarContainer.widthAnchor.constraint(equalToConstant: 200),
            
            username.topAnchor.constraint(equalTo: avatarContainer.bottomAnchor, constant: 24),
            username.centerXAnchor.constraint(equalTo: avatarContainer.centerXAnchor),
            
            editInformation.topAnchor.constraint(equalTo: username.bottomAnchor, constant: 16),
            editInformation.centerXAnchor.constraint(equalTo: avatarContainer.centerXAnchor),
            
            divider.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 16),
            divider.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -16),
            divider.topAnchor.constraint(equalTo: editInformation.bottomAnchor, constant: 16),
            divider.heightAnchor.constraint(equalToConstant: 1),
            
            aboutTitle.topAnchor.constraint(equalTo: divider.bottomAnchor, constant: 16),
            aboutTitle.centerXAnchor.constraint(equalTo: avatarContainer.centerXAnchor),
            
            aboutBody.topAnchor.constraint(equalTo: aboutTitle.bottomAnchor, constant: 16),
            aboutBody.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 16),
            aboutBody.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -16),
            aboutBody.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor, constant: -24)
        ])
        
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    lazy var avatarContainer: UIView = {
        let view = UIView()
        view.layer.cornerRadius = 100
        view.layer.shadowColor = UIColor.lightGray.cgColor
        view.layer.borderWidth = 10
        view.layer.borderColor = UIColor.white.cgColor
        view.layer.shadowOpacity = 0.5
        view.layer.shadowRadius = 100
        view.addSubview(avatar)
        
        NSLayoutConstraint.activate([
            avatar.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            avatar.trailingAnchor.constraint(equalTo: view.trailingAnchor),
            avatar.topAnchor.constraint(equalTo: view.topAnchor),
            avatar.bottomAnchor.constraint(equalTo: view.bottomAnchor),
        ])
        
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    lazy var avatar: UIImageView = {
        let imageView = UIImageView()
        imageView.contentMode = .scaleAspectFit
        imageView.layer.cornerRadius = 100
        imageView.layer.masksToBounds = true
        imageView.translatesAutoresizingMaskIntoConstraints = false
        return imageView
    }()
    
    lazy var username: UILabel = {
        let label = UILabel()
        label.font = UIFont.preferredFont(forTextStyle: .title1)
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    lazy var editInformation: UIButton = {
        var config = UIButton.Configuration.filled()
        config.title = "Edit Information"
        config.cornerStyle = .capsule
        config.baseBackgroundColor = .systemBlue
        config.baseForegroundColor = .white
        config.image = UIImage(systemName: "pencil")
        config.imagePlacement = .leading
        config.imagePadding = 8
        config.contentInsets = NSDirectionalEdgeInsets(top: 10, leading: 20, bottom: 10, trailing: 20)
        
        let button = UIButton(configuration: config)
        button.translatesAutoresizingMaskIntoConstraints = false
        return button
    }()
    
    lazy var divider: UIView = {
        let view = UIView()
        view.backgroundColor = .lightGray
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    lazy var aboutTitle: UILabel = {
        let label = UILabel()
        label.font = UIFont.preferredFont(forTextStyle: .title1)
        label.text = "About this app"
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    lazy var aboutBody: UILabel = {
        let label = UILabel()
        label.font = UIFont.preferredFont(forTextStyle: .body)
        label.textAlignment = .center
        label.numberOfLines = 0
        label.text = "Hey, I’m Renz Carlo Salanga, your friendly neighborhood fullstack developer. I built this app to prove that Kotlin Multiplatform is so awesome, even Chuck Norris would switch from roundhouse kicks to coding. 😄"
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    // MARK: - lifecycle
    override init(frame: CGRect) {
        super.init(frame: frame)
        setupView()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setupView()
    }
    
    private func setupView() {
        self.addSubview(scrollView)
        
        NSLayoutConstraint.activate([
            scrollView.leadingAnchor.constraint(equalTo: self.leadingAnchor),
            scrollView.trailingAnchor.constraint(equalTo: self.trailingAnchor),
            scrollView.topAnchor.constraint(equalTo: self.topAnchor),
            scrollView.bottomAnchor.constraint(equalTo: self.bottomAnchor)
        ])
    }
}

#if DEBUG
import SwiftUI

@available(iOS 13, *)
struct ProfileViewPreview: PreviewProvider {
    static var previews: some View {
        ProfileView().showPreview()
    }
}
#endif
