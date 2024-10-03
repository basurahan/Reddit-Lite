//
//  KeychainHelper.swift
//  iosApp
//
//  Created by Apple on 9/23/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Security

class KeychainHelper {
    
    class func save(token: String, forKey key: String) -> Bool {
        guard let tokenData = token.data(using: .utf8) else { return false }

        let query: [String: Any] = [
            kSecClass as String: kSecClassGenericPassword,
            kSecAttrAccount as String: key,
            kSecValueData as String: tokenData
        ]

        // Delete any existing items
        SecItemDelete(query as CFDictionary)

        // Add the new item
        let status = SecItemAdd(query as CFDictionary, nil)
        return status == errSecSuccess
    }

    class func load(forKey key: String) -> String? {
        let query: [String: Any] = [
            kSecClass as String: kSecClassGenericPassword,
            kSecAttrAccount as String: key,
            kSecReturnData as String: kCFBooleanTrue!,
            kSecMatchLimit as String: kSecMatchLimitOne
        ]

        var dataTypeRef: AnyObject?
        let status: OSStatus = SecItemCopyMatching(query as CFDictionary, &dataTypeRef)

        guard status == errSecSuccess else { return nil }

        if let data = dataTypeRef as? Data, let token = String(data: data, encoding: .utf8) {
            return token
        }
        return nil
    }

    class func delete(forKey key: String) {
        let query: [String: Any] = [
            kSecClass as String: kSecClassGenericPassword,
            kSecAttrAccount as String: key
        ]
        SecItemDelete(query as CFDictionary)
    }
}
