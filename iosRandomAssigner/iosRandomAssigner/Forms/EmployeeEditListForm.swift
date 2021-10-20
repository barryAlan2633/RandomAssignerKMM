//
//  EmployeeEditListForm.swift
//  iosRandomAssigner
//
//  Created by Alan Ramirez on 10/19/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct EmployeeEditListForm: View {
    
    @State private var name: String = ""
    @State private var buttonText: String = "Add"
    @State private var employeeToEditUUID:UUID? = nil
    
    var body: some View{
        VStack(alignment: HorizontalAlignment.leading){
            
            HStack{
                TextField("Enter Employee Name", text: $name)
                    .foregroundColor(Color.black)
                    .font(.system(size: 20, weight: .medium))
                
                Spacer()
                
                ButtonView(text: buttonText,width:75){
                    if(buttonText == "Add"){
                        if(name.trimmingCharacters(in: .whitespacesAndNewlines) != ""){
//                            addSidework(name: name)
                            name = ""
                        }
                    }else{
                        if(name.trimmingCharacters(in: .whitespacesAndNewlines) != ""){
//                            updateEmployeeName(employee:employees.first(where: {employee in employee.id == employeeToEditUUID}), newName: name)
                            employeeToEditUUID = nil
                            name = ""
                            buttonText = "Add"
                        }
                    }
                }
            }
            
//            Text("Employees:\(employees.count)")
//                .foregroundColor(.gray)
//
            
            ScrollView{
                VStack(alignment:HorizontalAlignment.leading){
//                    ForEach(employees, id: \.self) { data in
//
//                        HStack{
//
//                            HStack{
//                                Button(action:{toggleIsHere(data:data)}){
//
//                                    Image(systemName: data.isHere ? "checkmark.circle": "circle")
//                                        .font(data.isHere ? .system(size: 40):.system(size: 20) )
//                                        .foregroundColor(data.isHere ? Color.black: Color.gray)
//
//
//                                    Text(data.wrappedName)
//                                        .foregroundColor(data.isHere ? Color.black: Color.gray)
//                                        .font(.system(size: 20, weight: .medium))
//                                }
//
//                                Spacer()
//
//                                IconView(name:"pencil.circle.fill",
//                                         foregroundColor: employeeToEditUUID != nil && employeeToEditUUID == data.id ?
//                                            .red :.black){
//                                    if(employeeToEditUUID == data.id){
//                                        employeeToEditUUID = nil
//                                        name = ""
//                                        buttonText = "Add"
//
//                                    }else{
//                                        employeeToEditUUID = data.id
//                                        name = data.wrappedName
//                                        buttonText = "Save"
//                                    }
//                                }
//
//                                IconView(name: "trash.circle.fill", foregroundColor: .black) {
//                                    deleteItems(employee:data)
//                                    employeeToEditUUID = nil
//                                    buttonText = "Add"
//
//                                }
//                            }
//
//
//                        }
//                        .padding()
//                    }
                }
            }
        }
    }
     
//    private func toggleIsHere(data:Employee){
//
//    }
    
//    private func addSidework(name:String) {
//        withAnimation {
//
//        }
//    }
//
//    private func updateEmployeeName(employee:Employee?, newName:String) {
//
//
//    }
//
//    private func deleteItems(employee:Employee) {
//
//    }
}
