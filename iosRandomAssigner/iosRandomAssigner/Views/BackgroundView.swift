//
//  BackgroundView.swift
//  iosRandomAssigner
//
//  Created by Alan Ramirez on 10/19/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct BackgroundView: View {
    
    var topColor:Color
    var bottomColor:Color
    
    var body: some View {
        LinearGradient(gradient: Gradient(colors:[topColor,bottomColor]),
                       startPoint: .topLeading,
                       endPoint: .bottomTrailing)
            .edgesIgnoringSafeArea(.all)
    }
}

struct BackgroundView_Previews: PreviewProvider {
    static var previews: some View {
        BackgroundView(topColor:.blue,bottomColor:.white)
    }
}
