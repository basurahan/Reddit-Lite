//
//  EventBus.swift
//  iosApp
//
//  Created by Apple on 10/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import Foundation

class EventBus<T> {
    private let subject = CurrentValueSubject<T?, Never>(nil)
    private let observers = AtomicDictionary<String, AtomicBool>()
    
    func registerObserver(id: String, cancellables: inout Set<AnyCancellable>, _ callBack: @escaping (T) -> Void) {
        subject
            .receive(on: DispatchQueue.main)
            .sink { [weak self] value in
                guard let strongSelf = self else { return }
                
                let handled = strongSelf.observers.get(id)
                if handled == nil {
                    strongSelf.observers.set(id, value: AtomicBool(false))
                }
                
                guard let unwrapValue = value else { return }
                guard let unwrapHandled = strongSelf.observers.get(id) else { return }
                
                if unwrapHandled.get() {
                    return
                }
                
                callBack(unwrapValue)
                strongSelf.observers.get(id)?.toggle()
            }.store(in: &cancellables)
    }
    
    func emit(_ value: T?) {
        observers.setAllValues(to: AtomicBool(false))
        subject.send(value)
    }
}
