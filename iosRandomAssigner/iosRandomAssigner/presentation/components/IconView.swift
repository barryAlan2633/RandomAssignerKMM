//
//  IconView.swift
//  iosRandomAssigner
//
//  Created by Alan Ramirez on 10/19/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct IconView:View {
    
    var name :String
    var foregroundColor:Color
    var onIconPressed: () -> Void
    
    var body: some View{
        Image(systemName:name)
            .resizable()
            .aspectRatio(contentMode: .fit)
            .frame(width: 30, height: 30)
            .onTapGesture {onIconPressed()}
            .foregroundColor(foregroundColor)
            .padding(.trailing,20)
    }
}

 
