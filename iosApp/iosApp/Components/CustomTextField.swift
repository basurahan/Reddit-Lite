//
//  PasswordTextField.swift
//  iosApp
//
//  Created by Apple on 10/6/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit

class CustomTextField: UITextField {

    override var isSecureTextEntry: Bool {
        didSet {
            if isFirstResponder {
                _ = becomeFirstResponder()
            }
        }
    }

    override func becomeFirstResponder() -> Bool {
        let success = super.becomeFirstResponder()
        if isSecureTextEntry, let text = self.text {
            self.text?.removeAll()
            insertText(text)
        }
        return success
    }
}
