//
//  SideworkEditListForm.swift
//  iosRandomAssigner
//
//  Created by Alan Ramirez on 10/19/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct SideworkEditListForm: View {
     
    
    
    @State private var name: String = ""
    @State private var buttonText: String = "Add"
    @State private var sideWorkToEditUUID:UUID? = nil
 
    
    var body: some View{
        VStack(alignment: HorizontalAlignment.leading){
            
            HStack{
                TextField("Enter Side Work Name", text: $name)
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
//                            updateSideworkName(sideWork:sideworks.first(where: {sideWork in sideWork.id == sideWorkToEditUUID}), newName: name)
                            sideWorkToEditUUID = nil
                            name = ""
                            buttonText = "Add"
                        }
                    }
                }
            }
            
//             Text("Sideworks:\(sideworks.count)")
//                    .foregroundColor(.gray)
//
            ScrollView{
                VStack(alignment:HorizontalAlignment.leading){
//                    ForEach(sideworks, id: \.self) { data in
//
//                        HStack{
//                            Button(action:{toggleIsHere(data:data)}){
//
//                                Image(systemName: data.todoToday ? "checkmark.circle": "circle")
//                                    .font(data.todoToday ? .system(size: 40):.system(size: 20) )
//                                    .foregroundColor(data.todoToday ? Color.black: Color.gray)
//
//
//                                Text(data.wrappedName)
//                                    .foregroundColor(data.todoToday ? Color.black: Color.gray)
//                                    .font(.system(size: 20, weight: .medium))
//
//
//                                Spacer()
//
//                                IconView(name:"pencil.circle.fill",
//                                         foregroundColor: sideWorkToEditUUID != nil && sideWorkToEditUUID == data.id ?
//                                            .red :.black){
//                                    if(sideWorkToEditUUID == data.id){
//                                        sideWorkToEditUUID = nil
//                                        name = ""
//                                        buttonText = "Add"
//
//                                    }else{
//                                        sideWorkToEditUUID = data.id
//                                        name = data.wrappedName
//                                        buttonText = "Save"
//                                    }
//                                }
//
//                                IconView(name: "trash.circle.fill", foregroundColor: .black) {
//                                    deleteItems(sideWork:data)
//                                    sideWorkToEditUUID = nil
//                                    buttonText = "Add"
//
//                                }
//                            }
//                        }
//                        .padding()
//                    }
                }
            }
        }
    }
 
 
//    private func toggleIsHere(data:SideworkWithEmployees){
//        data.todoToday.toggle()
//
//        do {
//            try data.managedObjectContext?.save()
//        } catch {
//            // Replace this implementation with code to handle the error appropriately.
//            // fatalError() causes the application to generate a crash log and terminate. You should not use this function in a shipping application, although it may be useful during development.
//            let nsError = error as NSError
//            fatalError("Unresolved error \(nsError), \(nsError.userInfo)")
//        }
//    }
    
//    private func addSidework(name:String) {
//
//    }
//
//
//    private func updateSideworkName(sideWork:SideworkWithEmployees?, newName:String) {
//
//
//    }
//
//    private func deleteItems(sideWork:SideworkWithEmployees) {
//
//    }
}
