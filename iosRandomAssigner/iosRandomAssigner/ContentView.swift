//
//  ContentView.swift
//  SectionAssigner
//
//  Created by Alan Ramirez on 10/1/21.
//

import SwiftUI
import shared

struct ContentView: View {
    
    //MARK: PROPERTIES
    @ObservedObject private(set) var viewModel: ViewModel
    
    @State var showEditEmployee = false
    @State var showEditSideWork = false
    @State var showAlert = false
    @State var alertMessage = "Error"
    @State var index = 0
    @State private var isShowingSettings: Bool = false
    
    //MARK: BODY
    var body: some View {
        
        GeometryReader{geo in
            
            
            NavigationView{
                ZStack{
                    BackgroundView(topColor: .blue, bottomColor: .white)
                    
                    VStack {
                        SideworkListForm(sideworks: viewModel.sideworks)
                        
                        
                        HStack{
                            ButtonView(text:"Side Work",width:geo.size.width*0.4){showEditSideWork.toggle()}
                            ButtonView(text:"Employees",width:geo.size.width*0.4){showEditEmployee.toggle()}
                        }
                        
                        ButtonView(text:"Shuffle",width:geo.size.width*0.8,onButtonPressed:{
                            //                            assignEmployees()
                        })
                            .alert(isPresented: $showAlert) {
                                Alert(
                                    title: Text("Important message"),
                                    message: Text(alertMessage),
                                    dismissButton: .default(Text("Got it!"))
                                )
                            }
                    }
                    
                    if(showEditEmployee){
                        //Dimmed
                        GeometryReader{_ in
                            EmptyView()
                        }
                        .background(Color.gray.opacity(0.5))
                        .opacity(showEditEmployee ? 1 : 0)
                        
                        BottomSheetView(show: $showEditEmployee) {
                            EmployeeEditListForm()
                        }
                    }
                    
                    if(showEditSideWork){
                        //Dimmed
                        GeometryReader{_ in
                            EmptyView()
                        }
                        .background(Color.gray.opacity(0.5))
                        .opacity(showEditSideWork ? 1 : 0)
                        
                        BottomSheetView(show: $showEditSideWork){
                            SideworkEditListForm()
                        }
                    }
                }
                .edgesIgnoringSafeArea(.all)
                .navigationBarItems(
                    trailing:
                        Button(action:{
                            isShowingSettings = true
                        })  {
                            Image(systemName:"slider.horizontal.3")
                                .foregroundColor(Color.black)
                        }//:BUTTON
                        .sheet(isPresented: $isShowingSettings) {
                            SettingsForm()
                        }
                )
            }
            .navigationViewStyle(StackNavigationViewStyle())
            
        }
    }
    
    //    func assignEmployees(){
    //
    //        if(sideworks.isEmpty){
    //            for sidework in sideworks{
    //                sidework.employees = []
    //            }
    //
    //            showAlert = true
    //            alertMessage = "You do not have any side works!"
    //            return
    //        }
    //
    //        if(employees.isEmpty){
    //            for sidework in sideworks{
    //                sidework.employees = []
    //            }
    //            showAlert = true
    //            alertMessage = "You do not have any employees!"
    //            return
    //        }
    //
    //
    //
    //        //get selected list of sideworks
    //        var selectedSideWorks = [SideworkWithEmployees]()
    //        for sidework in sideworks{
    //            if(sidework.todoToday){
    //                selectedSideWorks.append(sidework)
    //            }
    //        }
    //        if(selectedSideWorks.isEmpty){
    //            for sidework in sideworks{
    //                sidework.employees = []
    //            }
    //
    //            showAlert = true
    //            alertMessage = "You do not have any side works selected!"
    //            return
    //        }
    //
    //
    //
    //        //get correct list of employees
    //        var selectedEmployees = [String]()
    //        for employee in employees {
    //            if(employee.isHere){
    //                selectedEmployees.append(employee.wrappedName)
    //            }
    //        }
    //        selectedEmployees = selectedEmployees.shuffled()
    //        if(selectedEmployees.isEmpty){
    //            for sidework in sideworks{
    //                sidework.employees = []
    //            }
    //
    //            showAlert = true
    //            alertMessage = "You do not have any employees selected!"
    //            return
    //        }
    //
    //
    //
    //
    //        //Make sure there are enough employees to fill the jobs
    //        if (selectedEmployees.count < selectedSideWorks.count){
    //            var employeesMissing = selectedSideWorks.count - selectedEmployees.count
    //            var employeeIndex = selectedEmployees.count-1
    //
    //            while(employeesMissing > 0) {
    //                selectedEmployees.append(selectedEmployees[employeeIndex])
    //                employeesMissing -= 1
    //                employeeIndex -= 1
    //
    //                if(employeeIndex == -1){
    //                    employeeIndex = selectedEmployees.count-1
    //                }
    //            }
    //        }
    //
    //
    //        //Assign each employee to however many sideworks they are supposed to have evenly
    //        selectedEmployees = selectedEmployees.shuffled()
    //        var employeeIndex = 0
    //        var sideWorksPerEmployee:Double = floor(Double(selectedEmployees.count)/Double(selectedSideWorks.count))
    //
    //        for sidework in sideworks{
    //            sidework.employees = []
    //
    //            if(sidework.todoToday){
    //                while(sideWorksPerEmployee > 0){
    //                    sidework.employees?.append(selectedEmployees[employeeIndex])
    //                    sideWorksPerEmployee -= 1
    //                    employeeIndex += 1
    //                }
    //                sideWorksPerEmployee = floor(Double(selectedEmployees.count)/Double(selectedSideWorks.count))
    //            }
    //        }
    //
    //
    //        //If there are employees left then assign them to random sideworks
    //        if(selectedEmployees.count > selectedSideWorks.count){
    //            for sideWork in sideworks{
    //
    //                if(sideWork.todoToday){
    //                    sideWork.employees?.append(selectedEmployees[employeeIndex])
    //                    employeeIndex += 1
    //                }
    //
    //                if(employeeIndex > selectedEmployees.count-1){
    //                    break
    //                }
    //            }
    //        }
    //
    //        do {
    //            try moc.save()
    //        } catch {
    //            // Replace this implementation with code to handle the error appropriately.
    //            // fatalError() causes the application to generate a crash log and terminate. You should not use this function in a shipping application, although it may be useful during development.
    //            let nsError = error as NSError
    //            fatalError("Unresolved error \(nsError), \(nsError.userInfo)")
    //        }
    //
    //    }
    
    
}

//MARK: VIEWMODEL
extension ContentView {
    
 
    
    
    class ViewModel: ObservableObject {
        let sdk: AssignerSDK
        @Published var sideworks:[Sidework]
        @Published var employees:[Employee]

        
        init(sdk:AssignerSDK){
            self.sdk = sdk
            self.sideworks = sdk.getSideWorks()
            self.employees = sdk.getEmployees()

            sdk.saveEmployee(employee: Employee(id: 1, name: "Alan Ramirez", isHere: false))
            sdk.saveSidework(sidework: Sidework(id: 1, name: "Kitchen", employee: nil, isDueToday: false))
            print(sideworks)
            print(employees)
        }
    }
}

extension Sidework: Identifiable { }


//MARK: PREVIEW
//struct ContentView_Previews: PreviewProvider {
//    static var previews: some View {
//        ContentView()
//    }
//}
