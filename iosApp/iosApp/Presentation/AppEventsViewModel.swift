//
//  AppEventsViewModel.swift
//  iosApp
//
//  Created by Apple on 10/13/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import shared

class AppEventsViewModel {
    static let shared = AppEventsViewModel()
    
    let onSessionStarted = EventBus<String>()
}
