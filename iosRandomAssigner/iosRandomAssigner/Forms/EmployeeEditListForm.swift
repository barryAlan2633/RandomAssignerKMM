//
//  EmployeeEditListForm.swift
//  iosRandomAssigner
//
//  Created by Alan Ramirez on 10/19/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct EmployeeEditListForm: View {
    @EnvironmentObject var appViewModel: AppViewModel
    
    
    @State private var name: String = ""
    @State private var buttonText: String = "Add"
    
    var body: some View{
        VStack(alignment: HorizontalAlignment.leading){
            
            HStack{
                TextField("Enter Employee Name", text: $name)
                    .onChange(of: name, perform: { value in
                        appViewModel.onTriggerEvent(stateEvent: AppEvents.SetNewEmployeeName(name: value))
                    })
                    .foregroundColor(Color.black)
                    .font(.system(size: 20, weight: .medium))
                
                Spacer()
                
                //MARK: - ADD SAVE BUTTON
                ButtonView(text: appViewModel.state.employeeButtonText,width:75){
                    
                    name = ""
                    
                    if(appViewModel.state.employeeButtonText == "Add"){
                        
                        appViewModel.onTriggerEvent(
                            stateEvent: AppEvents.SaveEmployee(
                                employee:Employee(
                                    id: UUID().uuidString,
                                    name: appViewModel.state.newEmployeeName,
                                    isHere: false
                                )
                            )
                        )
                    }else{
                        
                        appViewModel.onTriggerEvent(
                            stateEvent: AppEvents.SaveEmployee(
                                employee:Employee(
                                    id: appViewModel.state.selectedEmployeeID,
                                    name: appViewModel.state.newEmployeeName,
                                    isHere:(appViewModel.state.employees.first(where: {it in
                                        it.id == appViewModel.state.selectedEmployeeID})?.isHere ?? false )
                                )
                            )
                        )
                    }
                }
            }
            
            Text("Employees:\(appViewModel.state.employees.count)")
                .foregroundColor(.gray)
            
            
            ScrollView{
                VStack(alignment:HorizontalAlignment.leading){
                    ForEach(appViewModel.state.employees, id: \.self) { employee in
                        
                        HStack{
                            
                            HStack{
                                Button(action:{
                                    appViewModel.onTriggerEvent(stateEvent: AppEvents.ToggleIsHere(employee: Employee(id: employee.id,name: employee.name, isHere: !employee.isHere)))
                                }){
                                    
                                    Image(systemName: employee.isHere ? "checkmark.circle": "multiply.circle")
                                        .font(employee.isHere ? .system(size: 40):.system(size: 20) )
                                        .foregroundColor(employee.isHere ? Color.black: Color.gray)
                                    
                                    
                                    Text(employee.name)
                                        .foregroundColor(employee.isHere ? Color.black: Color.gray)
                                        .font(.system(size: 20, weight: .medium))
                                }
                                
                                Spacer()
                                
                                IconView(name:"pencil.circle.fill",
                                         foregroundColor: appViewModel.state.isEmployeeEditShowing && appViewModel.state.selectedEmployeeID == employee.id ? .red :.black){
                                    
                                    if(appViewModel.state.selectedEmployeeID == employee.id){
                                        name = ""
                                    }else{
                                        name = employee.name
                                    }
                                    
                                    appViewModel.onTriggerEvent(stateEvent: AppEvents.ToggleEditEmployee(employee: employee))
                                }
                                
                                IconView(name: "trash.circle.fill", foregroundColor: .black) {
                                    appViewModel.onTriggerEvent(stateEvent: AppEvents.DeleteEmployee(employeeID: employee.id))
                                }
                            }
                        }
                        .padding()
                    }
                }
            }
        }
        .onAppear(perform: {
            self.name = appViewModel.state.newEmployeeName
            self.buttonText = appViewModel.state.employeeButtonText
        })
    }
}
