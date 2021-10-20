//
//  CheckView.swift
//  iosRandomAssigner
//
//  Created by Alan Ramirez on 10/19/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct CheckView: View {
   @Binding var isChecked:Bool
   var title:String
   func toggle(){isChecked = !isChecked}
   var body: some View {
       Button(action: toggle){
           HStack{
               Image(systemName: isChecked ? "checkmark.square": "square")
                   .font(.system(size: 40))
                   .foregroundColor(.black)

               
               Text(title)
                   .foregroundColor(Color.black)
                   .font(.system(size: 20, weight: .medium))
               
           }

       }

   }

}
 
