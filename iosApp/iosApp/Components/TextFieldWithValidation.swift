//
//  TextFieldWithValidation.swift
//  iosApp
//
//  Created by Apple on 9/22/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit

class TextFieldWithValidation : UIView {
    
    var hint: String
    
    lazy var textField: UITextField = {
        let textField = UITextField()
        textField.placeholder = hint
        textField.borderStyle = .roundedRect
        textField.autocapitalizationType = .none
        textField.translatesAutoresizingMaskIntoConstraints = false
        return textField
    }()
    
    lazy var errorLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont.systemFont(ofSize: 12)
        label.textColor = .red
        label.numberOfLines = 0
        label.isHidden = true
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    init(_ hint: String) {
        self.hint = hint
        super.init(frame: .zero)
        setupViews()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func setupViews() {
        addSubview(textField)
        addSubview(errorLabel)
        
        NSLayoutConstraint.activate([
            textField.leadingAnchor.constraint(equalTo: self.leadingAnchor),
            textField.trailingAnchor.constraint(equalTo: self.trailingAnchor),
            textField.topAnchor.constraint(equalTo: self.topAnchor),
            
            errorLabel.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -8),
            errorLabel.topAnchor.constraint(equalTo: textField.bottomAnchor, constant: 4),
            errorLabel.bottomAnchor.constraint(equalTo: self.bottomAnchor)
        ])
    }
    
    func getText() -> String? {
        return textField.text
    }
    
    func showError(message: String) {
        errorLabel.text = message
        errorLabel.isHidden = false
    }

    func clearError() {
        errorLabel.isHidden = true
    }
}

#if DEBUG
import SwiftUI

@available(iOS 13, *)
struct TextFieldWithValidationPreview: PreviewProvider {
    static var previews: some View {
        TextFieldWithValidation("Hello").showPreview()
    }
}
#endif