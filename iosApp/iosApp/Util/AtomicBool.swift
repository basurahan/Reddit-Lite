//
//  AtomicBool.swift
//  iosApp
//
//  Created by Apple on 10/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

class AtomicBool {
    private var value: Bool
    private let queue = DispatchQueue(label: "atomic.bool.queue", attributes: .concurrent)

    init(_ value: Bool) {
        self.value = value
    }

    func get() -> Bool {
        return queue.sync {
            return value
        }
    }

    func set(_ newValue: Bool) {
        queue.async(flags: .barrier) {
            self.value = newValue
        }
    }

    func toggle() {
        queue.async(flags: .barrier) {
            self.value.toggle()
        }
    }
}
