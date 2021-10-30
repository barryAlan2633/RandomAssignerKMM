//
//  BottomSheetForm.swift
//  iosRandomAssigner
//
//  Created by Alan Ramirez on 10/19/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct BottomSheetView<Content: View>: View{
//    @Binding var show:Bool
    var content: Content
    private let onTriggerEvent:() -> Void
    
    init(
        onTriggerEvent:@escaping()->Void,
        @ViewBuilder content: @escaping () -> Content
    ){
        self.onTriggerEvent = onTriggerEvent
        self.content = content()
    }
    
    var body: some View{
        
        ZStack{
            RoundedRectangle(cornerRadius: 30, style: .continuous)
                .fill(Color.white)
                .ignoresSafeArea(.all, edges: .bottom)
                .shadow(color: Color.black.opacity(0.3), radius: 20, x: 0, y: 0)
            VStack{
                content
                
                Spacer()
                
                ButtonView(text: "Finished",width:280, onButtonPressed: {
                    onTriggerEvent()
                })
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


//struct BottomSheetView_Previews: PreviewProvider {
//    static var previews: some View {
//        BottomSheetView(show:.constant(true),content:{
//
//        })
//    }
//}
