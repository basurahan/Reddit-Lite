//
//  UIViewExtension.swift
//  iosApp
//
//  Created by Apple on 9/19/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

extension UIView {
    
    @available(iOS 13, *)
    private struct Preview: UIViewRepresentable {
        typealias UIViewType = UIView
        let view: UIView
        
        func makeUIView(context: Context) -> UIView {
            return view
        }
        
        func updateUIView(_ uiView: UIView, context: Context) {
            
        }
    }
    
    @available(iOS 13, *)
    func showPreview() -> some View {
        Preview(view: self)
    }
}
