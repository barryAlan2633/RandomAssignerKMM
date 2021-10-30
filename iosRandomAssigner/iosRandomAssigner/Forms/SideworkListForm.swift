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
    @EnvironmentObject var appViewModel: AppViewModel

    var h:[Int] = [0,1,2,3]
     
    var body: some View {
        
        ScrollView {
            
            LazyVStack(alignment: .leading) {
                ForEach(appViewModel.state.shuffledSideworks, id: \.self) { sidework in
//                ForEach(h,id:\.self) { sidework in
//                    Text("\(sidework)")
                        SideworkCardView(sideWorkWithEmployees: sidework)
                }
            }
            
        }

        .padding(30)
        .padding(.top,50)
    }
}
