//
//  AtomicDictionary.swift
//  iosApp
//
//  Created by Apple on 10/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

class AtomicDictionary<Key: Hashable, Value> {
    private var dictionary = [Key: Value]()
    private let queue = DispatchQueue(label: "atomic.dictionary.queue", attributes: .concurrent)

    func get(_ key: Key) -> Value? {
        return queue.sync {
            return dictionary[key]
        }
    }

    func set(_ key: Key, value: Value) {
        queue.async(flags: .barrier) {
            self.dictionary[key] = value
        }
    }

    func remove(_ key: Key) {
        queue.async(flags: .barrier) {
            self.dictionary.removeValue(forKey: key)
        }
    }

    func getAll() -> [Key: Value] {
        return queue.sync {
            return dictionary
        }
    }
    
    func setAllValues(to newValue: Value) {
        queue.async(flags: .barrier) {
            for key in self.dictionary.keys {
                self.dictionary[key] = newValue
            }
        }
    }
}
