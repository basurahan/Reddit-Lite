//
//  AvatarUtil.swift
//  iosApp
//
//  Created by Apple on 10/17/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit

func generateAvatar(initials: String, size: CGFloat) -> UIImage {
    let renderer = UIGraphicsImageRenderer(size: CGSize(width: size, height: size))
    let image = renderer.image { context in
        let ctx = context.cgContext
        
        // draw the background
        ctx.setFillColor(UIColor(red: 61/255.0, green: 99/255.0, blue: 115/255.0, alpha: 1.0).cgColor)
        ctx.fill(CGRect(x: 0, y: 0, width: size, height: size))
        
        // draw the text
        let paragraphStyle = NSMutableParagraphStyle()
        paragraphStyle.alignment = .center
        
        let attributes: [NSAttributedString.Key: Any] = [
            .foregroundColor: UIColor.white,
            .font: UIFont.systemFont(ofSize: 80, weight: .medium),
            .paragraphStyle: paragraphStyle
        ]
        
        let textSize = initials.size(withAttributes: attributes)
        
        let textRect = CGRect(
            x: 0,
            y: (CGFloat(size) - textSize.height) / 2,
            width: CGFloat(size),
            height: textSize.height
        )
        
        initials.draw(in: textRect, withAttributes: attributes)
    }
    
    return image
}
