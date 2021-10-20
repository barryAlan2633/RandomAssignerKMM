//
//  SettingsForm.swift
//  iosRandomAssigner
//
//  Created by Alan Ramirez on 10/19/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct SettingsForm: View {
    // MARK: - PROPERTIES
    
    @Environment(\.presentationMode) var presentationMode
    @AppStorage("isOnboarding") var isOnBoarding: Bool = false
    
    // MARK: - BODY
    
    var body: some View {
        NavigationView {
            ScrollView(.vertical, showsIndicators: false){
                VStack(spacing:20) {
                    
                  
                    //MARK: - SECTION 2
                    
                    GroupBox(
                        label: SettingsLabelView(labelText: "Customization", labelImage: "paintbrush")
                    ){
                        Divider().padding(.vertical,4)
                        
                        Text("If you wish, you can restart the application by toggling the switch in this box. That way it starts the onboarding process and you will see the welcome screen again.")
                            .padding(.vertical, 8)
                            .frame(minHeight:60)
                            .layoutPriority(1)
                            .font(.footnote)
                            .multilineTextAlignment(.leading)
                        
                        Toggle(isOn: $isOnBoarding){
                            if isOnBoarding {
                                Text("Restarted".uppercased())
                                    .fontWeight(/*@START_MENU_TOKEN@*/.bold/*@END_MENU_TOKEN@*/)
                                    .foregroundColor(Color.green)
                            } else {
                                Text("Restart".uppercased())
                                    .fontWeight(.bold)
                                    .foregroundColor(Color.secondary)
                            }
                        }
                        .padding()
                        .background(
                            Color(UIColor.tertiarySystemBackground)
                                .clipShape(RoundedRectangle(cornerRadius: 8,style:.continuous))
                        )
                    }
                    
                    
                    //MARK: - SECTION 3
                    GroupBox(
                        label: SettingsLabelView(labelText:"Application", labelImage:"apps.iphone")
                    ){
                        SettingsRowView(name: "Developer", content: "Alan Ramirez")
                       
                        SettingsRowView(name: "Version", content: "1.0.0")
                    }//:BOX
                    
 
                }//:VSTACK
                .navigationBarTitle(Text("Settings"),displayMode:.large)
                .navigationBarItems(
                    trailing:
                        Button(action:{
                            presentationMode.wrappedValue.dismiss()
                        }){
                            Image(systemName:"xmark")
                                .foregroundColor(Color.black)
                        }
                )
                .padding()
                
            }//:SCROLL
        }//: NAVIGATION
        
    }
}

// MARK - PREVIEW
struct SettingsView_Previews: PreviewProvider {
    static var previews: some View {
        SettingsForm()
            .preferredColorScheme(.dark)
            .previewDevice("iphone 11 pro")
    }
}
