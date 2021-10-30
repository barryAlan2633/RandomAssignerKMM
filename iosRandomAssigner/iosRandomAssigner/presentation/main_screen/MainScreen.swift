//
//  ContentView.swift
//  SectionAssigner
//
//  Created by Alan Ramirez on 10/1/21.
//

import SwiftUI
import shared

struct MainScreen: View {
    @EnvironmentObject var appViewModel: AppViewModel
    
    
    //MARK: BODY
    var body: some View {
        GeometryReader{geo in
            NavigationView{
                ZStack{
                    BackgroundView(topColor: .blue, bottomColor: .white)
                    
                    VStack {
                        SideworkListForm()
 
                        HStack{
                            ButtonView(text:"Side Work",width:geo.size.width*0.4){
                                appViewModel.onTriggerEvent(stateEvent: AppEvents.ToggleEditSideworks())
                            }
                            ButtonView(text:"Employees",width:geo.size.width*0.4){
                                appViewModel.onTriggerEvent(stateEvent: AppEvents.ToggleEditEmployees())
                            }
                        }
                        
                        ButtonView(text:"Shuffle",width:geo.size.width*0.8,onButtonPressed:{
                            appViewModel.onTriggerEvent(stateEvent: AppEvents.ShuffleSideworks())
                        })
                    }
                    
                    if(appViewModel.state.isEmployeesEditShowing){
                        //Dimmed
                        GeometryReader{_ in
                            EmptyView()
                        }
                        .background(Color.gray.opacity(0.5))
                        .opacity(1)
                        
                        BottomSheetView(onTriggerEvent: {
                            appViewModel.onTriggerEvent(stateEvent: AppEvents.ToggleEditEmployees())
                        }) {
                            EmployeeEditListForm()
                        }
                    }
                    
                    if(appViewModel.state.isSideworksEditShowing){
                        //Dimmed
                        GeometryReader{_ in
                            EmptyView()
                        }
                        .background(Color.gray.opacity(0.5))
                        .opacity(1)
                        
                        BottomSheetView(
                            onTriggerEvent: {
                                appViewModel.onTriggerEvent(stateEvent: AppEvents.ToggleEditSideworks())
                            }
                        ){
                            SideworkEditListForm()
                        }
                    }
                }
                .edgesIgnoringSafeArea(.all)
                .alert(isPresented: $appViewModel.showDialog) {
                    let first = appViewModel.state.queue.peek()!
                    return GenericMessageInfoAlert().build(
                        message:first,
                        onRemoveHeadMessage:{
                            appViewModel.onTriggerEvent(stateEvent:
                                                            AppEvents.OnRemoveHeadMessageFromQueue())
                        })
                }
                //                .navigationBarItems(
                //                    trailing:
                //                        Button(action:{
                ////                            isShowingSettings = true
                //                        })  {
                //                            Image(systemName:"slider.horizontal.3")
                //                                .foregroundColor(Color.black)
                //                        }//:BUTTON
                //                        .sheet(isPresented: $isShowingSettings) {
                //                            SettingsForm()
                //                        }
                //                )
            }
            .navigationViewStyle(StackNavigationViewStyle())
            
        }
    }
}


