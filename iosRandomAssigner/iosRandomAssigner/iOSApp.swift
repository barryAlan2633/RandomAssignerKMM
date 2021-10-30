import SwiftUI
import shared

@main
struct iOSApp: App {
    @AppStorage("isOnboarding") var isOnboarding: Bool = true
    @StateObject var appViewModel = AppViewModel()

 
    var body: some Scene {
        WindowGroup {
            if(isOnboarding){
                OnBoardingForm()
            }else{
                MainScreen()
                    .environmentObject(appViewModel)
             }
                
        }
    }
}
