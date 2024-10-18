//
//  TextFieldWithValidation.swift
//  iosApp
//
//  Created by Apple on 9/22/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit

class TextFieldWithValidation : UIView {
    
    // MARK: - properties
    private var hint: String
    private var isPrivate: Bool
    private var rightView: UIView?
    
    // MARK: - ui components
    lazy var textField: CustomTextField = {
        let textField = CustomTextField()
        textField.placeholder = hint
        textField.borderStyle = .roundedRect
        textField.autocapitalizationType = .none
        textField.rightView = rightView
        textField.rightViewMode = .always
        textField.clearsOnBeginEditing = false
        textField.isSecureTextEntry = isPrivate
        textField.autocorrectionType = .no
        textField.spellCheckingType = .no
        textField.translatesAutoresizingMaskIntoConstraints = false
        return textField
    }()
    
    lazy var errorLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont.systemFont(ofSize: 12)
        label.textAlignment = .right
        label.textColor = .red
        label.numberOfLines = 1
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    // MARK: - lifecycle
    init(hint: String, isPrivate: Bool, rightView: UIView?) {
        self.hint = hint
        self.isPrivate = isPrivate
        self.rightView = rightView
        super.init(frame: .zero)
        setupViews()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func setupViews() {
        self.addSubview(textField)
        self.addSubview(errorLabel)
        
        NSLayoutConstraint.activate([
            textField.leadingAnchor.constraint(equalTo: self.leadingAnchor),
            textField.trailingAnchor.constraint(equalTo: self.trailingAnchor),
            textField.topAnchor.constraint(equalTo: self.topAnchor),
            
            errorLabel.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 8),
            errorLabel.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -8),
            errorLabel.topAnchor.constraint(equalTo: textField.bottomAnchor, constant: 4),
            errorLabel.bottomAnchor.constraint(equalTo: self.bottomAnchor),
            errorLabel.heightAnchor.constraint(equalToConstant: errorLabel.font.lineHeight)
        ])
    }
    
    // MARK: - class helper
    func getText() -> String? {
        return textField.text
    }
    
    func setError(message: String?) {
        errorLabel.text = message
    }
    
    func toggleSecurityText() {
        textField.isSecureTextEntry.toggle()
    }
    
    func setDelegate(_ delegate: UITextFieldDelegate) {
        textField.delegate = delegate
    }
}

#if DEBUG
import SwiftUI

@available(iOS 13, *)
struct TextFieldWithValidationPreview: PreviewProvider {
    static var previews: some View {
        TextFieldWithValidation(hint: "Hello", isPrivate: true, rightView: nil).showPreview()
    }
}
#endif
