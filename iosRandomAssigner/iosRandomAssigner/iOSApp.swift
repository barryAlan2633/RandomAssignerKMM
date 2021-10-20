import SwiftUI
import shared

@main
struct iOSApp: App {
    @AppStorage("isOnboarding") var isOnboarding: Bool = true

 
    var body: some Scene {
        let sdk = AssignerSDK(databaseDriverFactory: DatabaseDriverFactory())
        WindowGroup {
            if(isOnboarding){
                OnBoardingForm()
            }else{
                ContentView(viewModel: .init(sdk: sdk))
             }
         }
    }
}
