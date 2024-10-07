//
//  PasswordToggle.swift
//  iosApp
//
//  Created by Apple on 10/6/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit

class PasswordToggle : UIView {
    
    // MARK: - properties
    private let onClick: () -> Void
    
    // MARK: - ui components
    lazy var button: UIButton = {
        let button = UIButton(type: .custom)
        button.setImage(UIImage(systemName: "eye.slash"), for: .normal)
        button.setImage(UIImage(systemName: "eye"), for: .selected)
        button.translatesAutoresizingMaskIntoConstraints = false
        button.addTarget(self, action: #selector(onButtonClick), for: .touchUpInside)
        return button
    }()
    
    // MARK: - lifecycle
    init(_ onClick: @escaping () -> Void) {
        self.onClick = onClick
        super.init(frame: CGRect.zero)
        setupViews()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func setupViews() {
        self.addSubview(button)
        
        NSLayoutConstraint.activate([
            button.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -10),
            button.topAnchor.constraint(equalTo: self.topAnchor),
            
            self.widthAnchor.constraint(equalToConstant: button.intrinsicContentSize.width + 10),
            self.heightAnchor.constraint(equalToConstant: button.intrinsicContentSize.height)
        ])
    }
    
    // MARK: - ui events
    @objc func onButtonClick() {
        button.isSelected.toggle()
        onClick()
    }
}

#if DEBUG
import SwiftUI

@available(iOS 13, *)
struct PasswordTogglePreview: PreviewProvider {
    static var previews: some View {
        PasswordToggle(){}.showPreview()
    }
}
#endif
