//
//  SideworkCardView.swift
//  iosRandomAssigner
//
//  Created by Alan Ramirez on 10/19/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SideworkCardView: View {
    var sideWorkWithEmployees : Sidework
    
    var body: some View {
        
        VStack(alignment: .leading){
            
            Text(sideWorkWithEmployees.name)
                .font(sideWorkWithEmployees.isDueToday ? .system(size: 25, weight: .medium) : .system(size:15,weight: .light))
                .foregroundColor( .black )
            
            Text((sideWorkWithEmployees.employee != nil) ? sideWorkWithEmployees.employee!.name : "")
            //sideWorkWithEmployees.isDueToday ? sideWorkWithEmployees.employee.joined(separator: ", "): "")
                .font(.system(size: 15, weight: .medium))
                .foregroundColor(.black)
        }
        
    }
}
