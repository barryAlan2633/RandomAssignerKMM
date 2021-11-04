import SwiftUI
import shared

@main
struct iOSApp: App {
    @StateObject var appViewModel = AppViewModel()

 
    var body: some Scene {
        WindowGroup {
                MainScreen()
                    .environmentObject(appViewModel)
                
        }
    }
}
