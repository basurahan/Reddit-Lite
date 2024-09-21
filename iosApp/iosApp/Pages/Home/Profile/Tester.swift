//
//  Tester.swift
//  iosApp
//
//  Created by Apple on 9/21/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import shared

class Tester: Kotlinx_coroutines_coreFlowCollector {
    
    func emit(value: Any?, completionHandler: @escaping ((any Error)?) -> Void) {
        // Ensure that the value is not nil
        guard let value = value as? String else {
            print("Received nil or non-string value")
            completionHandler(NSError(domain: "", code: -1, userInfo: [NSLocalizedDescriptionKey: "Invalid value type"]))
            return
        }
        
        // Process the received value
        print("Received: \(value)")
        
        // Call the completion handler to signal success
        completionHandler(nil)
    }
}
