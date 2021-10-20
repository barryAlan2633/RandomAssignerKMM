//
//  OnBoardingForm.swift
//  iosRandomAssigner
//
//  Created by Alan Ramirez on 10/19/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import AVKit

struct OnBoardingForm: View {
//    let avPlayer = AVPlayer(url:  Bundle.main.url(forResource: "onboarding1", withExtension: "mov")!)
    //MARK: = BODY
    var body: some View {
        GeometryReader{geo in
            TabView{
                ForEach(0..<3){ index in
                    ZStack{
//                        BackgroundView(topColor: Color.red, bottomColor: Color.yellow)
//                            .ignoresSafeArea()
                        Color.black
                        
                        VStack{
                            Spacer()
                            
//                             VideoPlayer(player: avPlayer)
//                                .aspectRatio(contentMode: .fit)
//                                .frame(width:geo.size.width*0.9)
//                                .onDisappear {avPlayer.isMuted = false}
//                            
                            Spacer()
                            
                            StartButtonView()
                                .padding(50)
                        }
                                       }
                    .cornerRadius(20)
                    .padding()
                }//: LOOP
            }//: TAB
            
            .tabViewStyle(PageTabViewStyle())
            .padding(.vertical,20)
        }
        
    }
}


struct OnBoardingView_Previews: PreviewProvider {
    static var previews: some View {
        OnBoardingForm()
    }
}
