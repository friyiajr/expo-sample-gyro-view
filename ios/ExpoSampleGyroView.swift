import ExpoModulesCore

class ExpoSampleGyroView: ExpoView {
  let onGyroEvent = EventDispatcher()
  let gyroView = GyroView()
  
  required init(appContext: AppContext? = nil) {
      super.init(appContext: appContext)
      clipsToBounds = true
      addSubview(gyroView)
      gyroView.setEventDispatcher(onGyroEvent)
    }

    override func layoutSubviews() {
      gyroView.frame = bounds
    }
}
