//
//  SideworkEditListForm.swift
//  iosRandomAssigner
//
//  Created by Alan Ramirez on 10/19/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SideworkEditListForm: View {
    @EnvironmentObject var appViewModel: AppViewModel
    
    
    @State private var name: String = ""
    @State private var buttonText: String = "Add"
    
    
    var body: some View{
        VStack(alignment: HorizontalAlignment.leading){
            
            HStack{
                TextField("",text: $name)
                    .placeholder(when: name.isEmpty){
                        Text("Enter Side Work Name").foregroundColor(Color("HintGray"))
                    }
                    .onChange(of: name, perform: { value in
                        appViewModel.onTriggerEvent(stateEvent: AppEvents.SetNewSideworkName(name: value))
                    })
                    .foregroundColor(Color.black)
                    .font(.system(size: 20, weight: .medium))
                     
                
                
                Spacer()
                
                //MARK: - ADD SAVE BUTTON
                ButtonView(text: appViewModel.state.sideworkButtonText,width:75){
                    
                    name = ""
                    
                    if(appViewModel.state.sideworkButtonText == "Add"){
                        
                        appViewModel.onTriggerEvent(
                            stateEvent: AppEvents.SaveSidework(
                                sidework:Sidework(
                                    id: UUID().uuidString,
                                    name: appViewModel.state.newSideworkName,
                                    employees: [],
                                    todoToday: false
                                )
                            )
                        )
                        
                        
                    }else{
                        
                        appViewModel.onTriggerEvent(
                            stateEvent: AppEvents.SaveSidework(
                                sidework:Sidework(
                                    id: appViewModel.state.selectedSideworkID,
                                    name: appViewModel.state.newSideworkName,
                                    employees: [],
                                    todoToday:(appViewModel.state.sideworks.first(where: {it in
                                        it.id == appViewModel.state.selectedSideworkID})?.todoToday ?? false )
                                )
                            )
                        )
                    }
                }
            }
            Text("Sideworks:\(appViewModel.state.sideworks.count)")
                .foregroundColor(Color("HintGray"))
            
            ScrollView{
                VStack(alignment:HorizontalAlignment.leading){
                    ForEach(appViewModel.state.sideworks, id: \.self) { sidework in
                        
                        HStack{
                            Button(action:{
                                appViewModel.onTriggerEvent(stateEvent: AppEvents.ToggleTodoToday(sidework: Sidework(id: sidework.id,name: sidework.name,  employees: [], todoToday: !sidework.todoToday)))
                            }){
                                
                                Image(systemName: sidework.todoToday ? "checkmark.circle": "multiply.circle")
                                    .font(sidework.todoToday ? .system(size: 40):.system(size: 20) )
                                    .foregroundColor(sidework.todoToday ? Color.black: Color.gray)
                                
                                
                                Text(sidework.name)
                                    .foregroundColor(sidework.todoToday ? Color.black: Color.gray)
                                    .font(.system(size: 20, weight: .medium))
                                
                                Spacer()
                                
                                IconView(name:"pencil.circle.fill",
                                         foregroundColor: appViewModel.state.isSideworkEditShowing && appViewModel.state.selectedSideworkID == sidework.id ? .red : .black){
                                    
                                    if(appViewModel.state.selectedSideworkID == sidework.id){
                                        name = ""
                                    }else{
                                        name = sidework.name
                                    }
                                    appViewModel.onTriggerEvent(stateEvent: AppEvents.ToggleEditSidework(sidework: sidework))
                                }
                                
                                IconView(name: "trash.circle.fill", foregroundColor: .black) {
                                    appViewModel.onTriggerEvent(stateEvent: AppEvents.DeleteSidework(sideworkID: sidework.id))
                                }
                            }
                        }
                        .padding()
                    }
                }
            }
        }
        .onAppear(perform: {
            self.name = appViewModel.state.newSideworkName
        })
    }
}


extension View {
    func placeholder<Content: View>(
        when shouldShow: Bool,
        alignment: Alignment = .leading,
        @ViewBuilder placeholder: () -> Content) -> some View {

        ZStack(alignment: alignment) {
            placeholder().opacity(shouldShow ? 1 : 0)
            self
        }
    }
}
