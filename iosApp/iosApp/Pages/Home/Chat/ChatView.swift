//
//  ChatView.swift
//  iosApp
//
//  Created by Apple on 9/19/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit

class ChatView: UIView {

    lazy var button: UIButton = {
        let button = UIButton(type: .system)
        button.setTitle("Chat", for: .normal)
        button.translatesAutoresizingMaskIntoConstraints = false
        return button
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setupView()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setupView()
    }
    
    private func setupView() {
        // Add the horizontal stack view to the main view
        self.addSubview(button)
        
        // Set up constraints
        NSLayoutConstraint.activate([
            button.centerXAnchor.constraint(equalTo: self.centerXAnchor),
            button.centerYAnchor.constraint(equalTo: self.centerYAnchor)
        ])
    }
}

#if DEBUG
import SwiftUI

@available(iOS 13, *)
struct ChatViewPreview: PreviewProvider {
    static var previews: some View {
        ChatView().showPreview()
    }
}
#endif
