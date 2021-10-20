//
//  SideworkListForm.swift
//  iosRandomAssigner
//
//  Created by Alan Ramirez on 10/19/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SideworkListForm: View {
 
    var sideworks:[Sidework]
     
    var body: some View {
        
        ScrollView {
            
            LazyVStack(alignment: .leading) {
//                ForEach(sideworks.indices, id:\.self) { index in
//                    SideworkCardView(
//                        sideWorkWithEmployees: sideworks[index]
//                    )
//                }
            }
        }
        .padding(30)
        .padding(.top,50)
    }
}
