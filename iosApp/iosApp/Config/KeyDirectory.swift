//
//  KeyDirectory.swift
//  iosApp
//
//  Created by Apple on 9/23/24.
//  Copyright © 2024 orgName. All rights reserved.
//

enum KeyDirectory {
    case token(String)
    
    var key: String {
        switch self {
        case .token(let value):
            return value
        }
    }
}

extension KeyDirectory {
    static let token = KeyDirectory.token("userToken")
}
