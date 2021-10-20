//
//  BottomSheetForm.swift
//  iosRandomAssigner
//
//  Created by Alan Ramirez on 10/19/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct BottomSheetView<Content: View>: View{
    @Binding var show:Bool
    @ViewBuilder var content: Content
    
    var body: some View{
        
        ZStack{
            RoundedRectangle(cornerRadius: 30, style: .continuous)
                .fill(Color.white)
                .ignoresSafeArea(.all, edges: .bottom)
                .shadow(color: Color.black.opacity(0.3), radius: 20, x: 0, y: 0)
            VStack{
                content
                
                Spacer()
                
                ButtonView(text: "Finished",width:280, onButtonPressed: {show.toggle()})
            }
            .padding()
        }
        .padding()
        .padding(.top,150)
        .animation(.spring(response: 0.5, dampingFraction: 0.8))
        .transition(.move(edge: .bottom))
        .zIndex(1)
    }
}


struct BottomSheetView_Previews: PreviewProvider {
    static var previews: some View {
        BottomSheetView(show:.constant(true),content:{
            
        })
    }
}
