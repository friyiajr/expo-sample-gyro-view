import ExpoModulesCore

let kOnGyroEvent = "onGyroEvent"

public class ExpoSampleGyroViewModule: Module {
  public func definition() -> ModuleDefinition {
    Name("ExpoSampleGyroView")
    
    View(ExpoSampleGyroView.self) {
      Events(kOnGyroEvent)
      
      Prop("placeholderText") { (view, text: String) in
        view.gyroView.setPlaceholderText(text)
      }
      
      Prop("track") { (view, isTracking: Bool) in
        if isTracking {
          view.gyroView.startGyros()
        } else {
          view.gyroView.stopGyros()
        }
      }
    }
  }
}
