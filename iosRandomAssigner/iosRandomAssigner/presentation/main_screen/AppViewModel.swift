//
//  AppViewModel.swift
//  iosRandomAssigner
//
//  Created by Alan Ramirez on 10/27/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared


class AppViewModel:ObservableObject {
    
    //MARK: - VARIABLES (Use cases)
    let shuffleSideworksUseCase:ShuffleSideworks
    let getEmployeesUseCase:GetEmployees
    let getSideworksUseCase:GetSideworks
    let deleteEmployeeUseCase:DeleteEmployee
    let deleteSideworkUseCase:DeleteSidework
    let editEmployeeUseCase:EditEmployee
    let editSideworkUseCase:EditSidework
    private let cacheModule:CacheModule
    
    @Published var state: AppState = AppState()
    
    @Published var showDialog:Bool = false
    
    //MARK: - INIT
    init(){
        self.cacheModule = CacheModule()
        
        self.shuffleSideworksUseCase = ShuffleSideworksModule(cacheModule: self.cacheModule).shuffleSideworks
        self.getEmployeesUseCase = GetEmployeesModule(cacheModule: self.cacheModule).getEmployees
        self.getSideworksUseCase = GetSideworksModule(cacheModule: self.cacheModule).getSideworks
        self.deleteEmployeeUseCase = DeleteEmployeeModule(cacheModule: self.cacheModule).deleteEmployee
        self.deleteSideworkUseCase = DeleteSideworkModule(cacheModule: self.cacheModule).deleteSidework
        self.editEmployeeUseCase = EditEmployeeModule(cacheModule: self.cacheModule).editEmployee
        self.editSideworkUseCase = EditSideworkModule(cacheModule: self.cacheModule).editSidework
        
    }
    
    //MARK: - UPDATE STATE
    func updateState(
        queue:Queue<GenericMessageInfo>? = nil
    ){
        let currentState:AppState  = (self.state.copy() as! AppState)
        self.state = self.state.doCopy(
            shuffledSideworks: currentState.shuffledSideworks,
            sideworks:  currentState.sideworks,
            employees: currentState.employees,
            isSideworksEditShowing: currentState.isSideworksEditShowing,
            isEmployeesEditShowing: currentState.isEmployeesEditShowing,
            isSideworkEditShowing: currentState.isSideworksEditShowing,
            isEmployeeEditShowing: currentState.isEmployeeEditShowing,
            newSideworkName: currentState.newSideworkName,
            newEmployeeName: currentState.newEmployeeName,
            selectedSideworkID: currentState.selectedSideworkID,
            selectedEmployeeID: currentState.selectedEmployeeID,
            employeeButtonText: currentState.employeeButtonText,
            sideworkButtonText: currentState.sideworkButtonText,
            queue: queue ?? currentState.queue
        )
        shouldShowDialog()
    }
    
    //MARK: - ON TRIGGER EVENT
    func onTriggerEvent(stateEvent:AppEvents){
        switch stateEvent {
            
        case is AppEvents.ShuffleSideworks:
            assignSideworks()
            
        case is AppEvents.DeleteEmployee:
            deleteEmployee(employeeID:(stateEvent as! AppEvents.DeleteEmployee).employeeID)
            
        case is AppEvents.DeleteSidework:
            deleteSidework(sideworkID:(stateEvent as! AppEvents.DeleteSidework).sideworkID)
            
        case is AppEvents.SaveEmployee:
            saveEmployee(employee:(stateEvent as! AppEvents.SaveEmployee).employee)
            
        case is AppEvents.SaveSidework:
            saveSidework(sidework:(stateEvent as! AppEvents.SaveSidework).sidework)
            
        case is AppEvents.SetNewEmployeeName:
            setNewEmployeeName(name:(stateEvent as! AppEvents.SetNewEmployeeName).name)
            
        case is AppEvents.SetNewSideworkName:
            setNewSideworkName(name:(stateEvent as! AppEvents.SetNewSideworkName).name)
            
        case is AppEvents.ToggleEditEmployee:
            toggleEditEmployee(employee:(stateEvent as! AppEvents.ToggleEditEmployee).employee)
            
        case is AppEvents.ToggleEditSidework:
            toggleEditSidework(sidework:(stateEvent as! AppEvents.ToggleEditSidework).sidework)
            
        case is AppEvents.ToggleEditEmployees:
            toggleEditEmployees()
            
        case is AppEvents.ToggleEditSideworks:
            toggleEditSideworks()
            
        case is AppEvents.ToggleIsHere:
            saveEmployee(employee:(stateEvent as! AppEvents.ToggleIsHere).employee)
            
        case is AppEvents.ToggleTodoToday:
            saveSidework(sidework:(stateEvent as! AppEvents.ToggleTodoToday).sidework)
            
        case is AppEvents.OnRemoveHeadMessageFromQueue:
            removeHeadFromQueue()
            
        default:
            doNothing()
        }
    }
    
    //MARK: - TOGGLE EDIT SIDEWORKS
    private func toggleEditSideworks(){
        self.showEditSideworks()
        
        getSideworksUseCase.execute()
            .collectCommon(
                coroutineScope: nil,
                callback: { dataState in
                    if dataState != nil {
                        let data = dataState?.data
                        let message = dataState?.message
                        
                        
                        self.updateState()
                        
                        if(data != nil){
                            self.setSideworks(sideworks: data as! [Sidework])
                        }
                        
                        if message != nil{
                            self.handleMessageByUIComponentType(message!.build())
                        }
                    }
                })
    }
    
    //MARK: - TOGGLE EDIT EMPLOYEES
    private func toggleEditEmployees(){
        
        self.showEditEmployees()
        
        getEmployeesUseCase.execute()
            .collectCommon(
                coroutineScope: nil,
                callback: { dataState in
                    if dataState != nil {
                        let data = dataState?.data
                        let message = dataState?.message
                        
                        
                        self.updateState()
                        
                        if(data != nil){
                            if (data as! [Sidework]).isEmpty {
                                print("toggleEditSideworks, empty")
                            }
                            self.setEmployees(employees: data as! [Employee])
                        }
                        
                        if message != nil{
                            self.handleMessageByUIComponentType(message!.build())
                        }
                    }
                })
    }
    
    //MARK: - TOGGLE EDIT SIDEWORK
    private func toggleEditSidework(sidework:Sidework){
        var currentState = (self.state.copy() as! AppState)
        if(state.selectedSideworkID == sidework.id){
            self.state = self.state.doCopy(
                shuffledSideworks: currentState.shuffledSideworks,
                sideworks: currentState.sideworks,
                employees: currentState.employees,
                isSideworksEditShowing: currentState.isSideworksEditShowing,
                isEmployeesEditShowing: currentState.isEmployeesEditShowing,
                isSideworkEditShowing: false,
                isEmployeeEditShowing: currentState.isEmployeeEditShowing,
                newSideworkName: "",
                newEmployeeName: currentState.newEmployeeName,
                selectedSideworkID: "",
                selectedEmployeeID: currentState.selectedEmployeeID,
                employeeButtonText: currentState.employeeButtonText,
                sideworkButtonText: "Add",
                queue: currentState.queue
            )
            
        }else{
            self.state = self.state.doCopy(
                shuffledSideworks: currentState.shuffledSideworks,
                sideworks: currentState.sideworks,
                employees: currentState.employees,
                isSideworksEditShowing: currentState.isSideworksEditShowing,
                isEmployeesEditShowing: currentState.isEmployeesEditShowing,
                isSideworkEditShowing: true,
                isEmployeeEditShowing: currentState.isEmployeeEditShowing,
                newSideworkName: sidework.name,
                newEmployeeName: currentState.newEmployeeName,
                selectedSideworkID: sidework.id,
                selectedEmployeeID: currentState.selectedEmployeeID,
                employeeButtonText: currentState.employeeButtonText,
                sideworkButtonText: "Save",
                queue: currentState.queue
            )
            
        }
        
        currentState = (self.state.copy() as! AppState)
    }
    
    //MARK: - TOGGLE EDIT EMPLOYEE
    private func toggleEditEmployee(employee:Employee){
        
        var currentState = (self.state.copy() as! AppState)
        if(state.selectedEmployeeID == employee.id){
            self.state = self.state.doCopy(
                shuffledSideworks: currentState.shuffledSideworks,
                sideworks: currentState.sideworks,
                employees: currentState.employees,
                isSideworksEditShowing: currentState.isSideworksEditShowing,
                isEmployeesEditShowing: currentState.isEmployeesEditShowing,
                isSideworkEditShowing: currentState.isSideworkEditShowing,
                isEmployeeEditShowing: false,
                newSideworkName: currentState.newSideworkName,
                newEmployeeName: "",
                selectedSideworkID: currentState.selectedSideworkID,
                selectedEmployeeID: "",
                employeeButtonText: "Add",
                sideworkButtonText: currentState.sideworkButtonText,
                queue: currentState.queue
            )
            
        }else{
            self.state = self.state.doCopy(
                shuffledSideworks: currentState.shuffledSideworks,
                sideworks: currentState.sideworks,
                employees: currentState.employees,
                isSideworksEditShowing: currentState.isSideworksEditShowing,
                isEmployeesEditShowing: currentState.isEmployeesEditShowing,
                isSideworkEditShowing: currentState.isSideworkEditShowing,
                isEmployeeEditShowing: true,
                newSideworkName: currentState.newSideworkName,
                newEmployeeName: employee.name,
                selectedSideworkID: currentState.selectedSideworkID,
                selectedEmployeeID: employee.id,
                employeeButtonText: "Save",
                sideworkButtonText: currentState.sideworkButtonText,
                queue: currentState.queue
            )
            
        }
        
        currentState = (self.state.copy() as! AppState)
    }
    
    //MARK: - SET NEW SIDEWORK NAME
    private func setNewSideworkName(name : String){
        var currentState = (self.state.copy() as! AppState)
        self.state = self.state.doCopy(
            shuffledSideworks: currentState.shuffledSideworks,
            sideworks: currentState.sideworks,
            employees: currentState.employees,
            isSideworksEditShowing: currentState.isSideworksEditShowing,
            isEmployeesEditShowing: currentState.isEmployeesEditShowing,
            isSideworkEditShowing: currentState.isSideworkEditShowing,
            isEmployeeEditShowing: currentState.isEmployeeEditShowing,
            newSideworkName: name,
            newEmployeeName: currentState.newEmployeeName,
            selectedSideworkID: currentState.selectedSideworkID,
            selectedEmployeeID: currentState.selectedEmployeeID,
            employeeButtonText: currentState.employeeButtonText,
            sideworkButtonText: currentState.sideworkButtonText,
            queue: currentState.queue
        )
        
        currentState = (self.state.copy() as! AppState)
        
        
        
    }
    
    //MARK: - SET NEW EMPLOYEE NAME
    private func setNewEmployeeName(name : String){
        var currentState = (self.state.copy() as! AppState)
        self.state = self.state.doCopy(
            shuffledSideworks: currentState.shuffledSideworks,
            sideworks: currentState.sideworks,
            employees: currentState.employees,
            isSideworksEditShowing: currentState.isSideworksEditShowing,
            isEmployeesEditShowing: currentState.isEmployeesEditShowing,
            isSideworkEditShowing: currentState.isSideworkEditShowing,
            isEmployeeEditShowing: currentState.isEmployeeEditShowing,
            newSideworkName: currentState.newSideworkName,
            newEmployeeName: name,
            selectedSideworkID: currentState.selectedSideworkID,
            selectedEmployeeID: currentState.selectedEmployeeID,
            employeeButtonText: currentState.employeeButtonText,
            sideworkButtonText: currentState.sideworkButtonText,
            queue: currentState.queue
        )
        
        currentState = (self.state.copy() as! AppState)
        
        
        
    }
    
    //MARK: - DELETE SIDEWORK
    private func deleteSidework(sideworkID:String){
        deleteSideworkUseCase.execute(sideworkID: sideworkID)
            .collectCommon(
                coroutineScope: nil,
                callback: { dataState in
                    if dataState != nil {
                        let data = dataState?.data
                        let message = dataState?.message
                        
                        
                        self.updateState()
                        
                        if(data != nil){
                            if (data as! [Sidework]).isEmpty {
                                print("sideworks are empty")
                            }
                            self.setSideworks(sideworks: data as! [Sidework])
                        }
                        
                        if message != nil{
                            self.handleMessageByUIComponentType(message!.build())
                        }
                    }
                })
    }
    
    //MARK: - DELETE EMPLOYEE
    private func deleteEmployee(employeeID:String){
        deleteEmployeeUseCase.execute(employeeID: employeeID)
            .collectCommon(
                coroutineScope: nil,
                callback: { dataState in
                    if dataState != nil {
                        let data = dataState?.data
                        let message = dataState?.message
                        
                        
                        self.updateState()
                        
                        if(data != nil){
                            self.setEmployees(employees: data as! [Employee])
                        }
                        
                        if message != nil{
                            self.handleMessageByUIComponentType(message!.build())
                        }
                    }
                })
        
    }
    
    //MARK: - SAVE SIDEWORK
    private func saveSidework(sidework:Sidework){
        editSideworkUseCase.execute(sidework: sidework)
            .collectCommon(
                coroutineScope: nil,
                callback: { dataState in
                    if dataState != nil {
                        let data = dataState?.data
                        let message = dataState?.message
                        
                        
                        self.updateState()
                        
                        if(data != nil){
                            self.setSideworks(sideworks: data as! [Sidework])
                        }
                        
                        if message != nil{
                            self.handleMessageByUIComponentType(message!.build())
                        }
                    }
                })
    }
    
    //MARK: - SAVE EMPLOYEE
    private func saveEmployee(employee:Employee){
        editEmployeeUseCase.execute(employee: employee)
            .collectCommon(
                coroutineScope: nil,
                callback: { dataState in
                    if dataState != nil {
                        let data = dataState?.data
                        let message = dataState?.message
                        
                        
                        self.updateState()
                        
                        if(data != nil){
                            self.setEmployees(employees: data as! [Employee])
                        }
                        
                        if message != nil{
                            self.handleMessageByUIComponentType(message!.build())
                        }
                    }
                })
    }
    
    //MARK: - ASSIGN SIDEWORKS
    private func assignSideworks(){
        shuffleSideworksUseCase.execute()
            .collectCommon(
                coroutineScope: nil,
                callback: { dataState in
                    if dataState != nil {
                        let data = dataState?.data
                        let message = dataState?.message
                        
                        
                        self.updateState()
                        
                        if(data != nil){
                            if (data as! [Sidework]).isEmpty {
                                print("Shuffled sideworks are empty")
                            }
                            self.setShuffledSideworks(sideworks: data as! [Sidework])
                        }
                        
                        if message != nil{
                            self.handleMessageByUIComponentType(message!.build())
                        }
                    }
                })
    }
    
    //MARK: - SET SHUFFLED SIDEWORKS
    private func setShuffledSideworks(sideworks:[Sidework]){
        var currentState = (self.state.copy() as! AppState)
        self.state = self.state.doCopy(
            shuffledSideworks: sideworks,
            sideworks: currentState.sideworks,
            employees: currentState.employees,
            isSideworksEditShowing: currentState.isSideworksEditShowing,
            isEmployeesEditShowing: currentState.isEmployeesEditShowing,
            isSideworkEditShowing: currentState.isSideworkEditShowing,
            isEmployeeEditShowing: currentState.isEmployeeEditShowing,
            newSideworkName: currentState.newSideworkName,
            newEmployeeName: currentState.newEmployeeName,
            selectedSideworkID: currentState.selectedSideworkID,
            selectedEmployeeID: currentState.selectedEmployeeID,
            employeeButtonText: currentState.employeeButtonText,
            sideworkButtonText: currentState.sideworkButtonText,
            queue: currentState.queue
        )
        
        currentState = (self.state.copy() as! AppState)
        
    }
    
    //MARK: - SET SIDEWORKS
    private func setSideworks(sideworks:[Sidework]){
        var currentState = (self.state.copy() as! AppState)
        self.state = self.state.doCopy(
            shuffledSideworks: currentState.shuffledSideworks,
            sideworks: sideworks,
            employees: currentState.employees,
            isSideworksEditShowing: currentState.isSideworksEditShowing,
            isEmployeesEditShowing: currentState.isEmployeesEditShowing,
            isSideworkEditShowing: false,
            isEmployeeEditShowing: currentState.isEmployeeEditShowing,
            newSideworkName: "",
            newEmployeeName: currentState.newEmployeeName,
            selectedSideworkID: currentState.selectedSideworkID,
            selectedEmployeeID: "",
            employeeButtonText: currentState.employeeButtonText,
            sideworkButtonText: "Add",
            queue: currentState.queue
        )
        
        currentState = (self.state.copy() as! AppState)
        
    }
    
    //MARK: - SET EMPLOYEES
    private func setEmployees(employees:[Employee]){
        var currentState = (self.state.copy() as! AppState)
        self.state = self.state.doCopy(
            shuffledSideworks: currentState.shuffledSideworks,
            sideworks: currentState.sideworks,
            employees: employees,
            isSideworksEditShowing: currentState.isSideworksEditShowing,
            isEmployeesEditShowing: currentState.isEmployeesEditShowing,
            isSideworkEditShowing: currentState.isSideworkEditShowing,
            isEmployeeEditShowing: false,
            newSideworkName: currentState.newSideworkName,
            newEmployeeName: "",
            selectedSideworkID: currentState.selectedSideworkID,
            selectedEmployeeID: "",
            employeeButtonText: "Add",
            sideworkButtonText: currentState.sideworkButtonText,
            queue: currentState.queue
        )
        currentState = (self.state.copy() as! AppState)
        
    }
    
    //MARK: - SHOW MAIN SCREEN
    private func showMainScreen(sideworks:[Sidework]){
        
        var currentState = (self.state.copy() as! AppState)
        self.state = self.state.doCopy(
            shuffledSideworks: currentState.shuffledSideworks,
            sideworks: currentState.sideworks,
            employees: currentState.employees,
            isSideworksEditShowing: false,
            isEmployeesEditShowing: false,
            isSideworkEditShowing: currentState.isSideworkEditShowing,
            isEmployeeEditShowing: currentState.isEmployeeEditShowing,
            newSideworkName: currentState.newSideworkName,
            newEmployeeName: currentState.newEmployeeName,
            selectedSideworkID: currentState.selectedSideworkID,
            selectedEmployeeID: currentState.selectedEmployeeID,
            employeeButtonText: currentState.employeeButtonText,
            sideworkButtonText: currentState.sideworkButtonText,
            queue: currentState.queue
        )
        
        currentState = (self.state.copy() as! AppState)
        
    }
    
    //MARK: - SHOW EDIT EMPLOYEES
    private func showEditEmployees(){
        
        var currentState = (self.state.copy() as! AppState)
        self.state = self.state.doCopy(
            shuffledSideworks: currentState.shuffledSideworks,
            sideworks: currentState.sideworks,
            employees: currentState.employees,
            isSideworksEditShowing: currentState.isSideworksEditShowing,
            isEmployeesEditShowing: !currentState.isEmployeesEditShowing,
            isSideworkEditShowing: currentState.isSideworkEditShowing,
            isEmployeeEditShowing: currentState.isEmployeeEditShowing,
            newSideworkName: currentState.newSideworkName,
            newEmployeeName: currentState.newEmployeeName,
            selectedSideworkID: currentState.selectedSideworkID,
            selectedEmployeeID: currentState.selectedEmployeeID,
            employeeButtonText: currentState.employeeButtonText,
            sideworkButtonText: currentState.sideworkButtonText,
            queue: currentState.queue
        )
        
        currentState = (self.state.copy() as! AppState)
        
    }
    
    //MARK: - SHOW EDIT SIDEWORKS
    private func showEditSideworks(){
        
        var currentState = (self.state.copy() as! AppState)
        self.state = self.state.doCopy(
            shuffledSideworks: currentState.shuffledSideworks,
            sideworks: currentState.sideworks,
            employees: currentState.employees,
            isSideworksEditShowing: !currentState.isSideworksEditShowing,
            isEmployeesEditShowing: currentState.isEmployeesEditShowing,
            isSideworkEditShowing: currentState.isSideworkEditShowing,
            isEmployeeEditShowing: currentState.isEmployeeEditShowing,
            newSideworkName: currentState.newSideworkName,
            newEmployeeName: currentState.newEmployeeName,
            selectedSideworkID: currentState.selectedSideworkID,
            selectedEmployeeID: currentState.selectedEmployeeID,
            employeeButtonText: currentState.employeeButtonText,
            sideworkButtonText: currentState.sideworkButtonText,
            queue: currentState.queue
        )
        
        currentState = (self.state.copy() as! AppState)
    }
    
    //MARK: - ERROR HANDLER
    private func handleMessageByUIComponentType(_ message:GenericMessageInfo){
        switch message.uiComponentType {
        case UIComponentType.Dialog():
            appendToQueue(message: message)
        case UIComponentType.None():
            print("\(message.description)")
        default:
            doNothing()
        }
    }
    
    func doNothing(){
        
    }
    
    
    func shouldShowDialog(){
        let currentState = self.state.copy() as! AppState
        showDialog = currentState.queue.items.count > 0
    }
    
    func appendToQueue(message: GenericMessageInfo){
        let currentState = self.state.copy() as! AppState
        let queue = currentState.queue
        let queueUtil = GenericMessageInfoQueueUtil()
        
        if(!queueUtil.doesMessageAlreadyExistInQueue(queue: queue, messageInfo: message)){
            queue.add(element: message)
            updateState(queue: queue)
        }
    }
    
    func removeHeadFromQueue(){
        let currentState = self.state.copy() as! AppState
        let queue = currentState.queue
        do {
            try queue.remove()
            updateState(queue: queue)
            
        }
        catch{
            print("\(error)")
        }
    }
}
