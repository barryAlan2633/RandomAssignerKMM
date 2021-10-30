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
            
            Text(sideWorkWithEmployees.name.capitalized)
                .font(.system(size: 25, weight: .medium))
                .foregroundColor( .black )
            
            Text(
                sideWorkWithEmployees.employees.map( { (employee) -> String in
                    employee.name
                    
                }).joined(separator: ", ")
            )
                .font(.system(size: 15, weight: .medium))
                .foregroundColor(.black)
        }
        
    }
}
