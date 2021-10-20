//
//  ButtonView.swift
//  iosRandomAssigner
//
//  Created by Alan Ramirez on 10/19/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct ButtonView: View {
    var text:String
    var width:CGFloat
    var onButtonPressed: () -> Void
    
    var body:some View{
        Button(action: onButtonPressed){
            Text(text)
                .foregroundColor(.white)
                .frame(width: width, height: 50)
                .background(Color.black)
                .cornerRadius(8.0)
                .padding(.bottom,10)
        }
         
    }
}

struct ButtonView_Previews: PreviewProvider {
    static var previews: some View {
        ButtonView(text:"Button",width:200 ,onButtonPressed:{})
    }
}
