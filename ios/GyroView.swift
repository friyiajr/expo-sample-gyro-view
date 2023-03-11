//
//  GyroView.swift
//  Gyros
//
//  Created by Daniel Friyia on 2023-03-11.
//

import ExpoModulesCore
import UIKit
import CoreMotion

class GyroView: UIView {
  let motion = CMMotionManager()
  var onGyroEvent: EventDispatcher? = nil
  
  var timer: Timer? = nil
  
  lazy var xPositionTextView = UILabel()
  
  func setPlaceholderText(_ text: String?) {
    self.xPositionTextView.text = text ?? "Start"
  }
  
  func setEventDispatcher(_ eventDispatcher: EventDispatcher) {
    self.onGyroEvent = eventDispatcher
  }
  
  func startGyros() {
    if motion.isGyroAvailable {
      self.motion.gyroUpdateInterval = 30.0 / 60.0
      self.motion.startAccelerometerUpdates()
      
      self.timer = Timer(fire: Date(), interval: (30.0/60.0), repeats: true, block: { (timer) in
        if let data = self.motion.accelerometerData {
          let x = data.acceleration.x
          let y = data.acceleration.y
          let z = data.acceleration.z
          self.xPositionTextView.text = "\(round(y * 100) / 100.0)"
          self.onGyroEvent?([
            "y": y
          ])
        }
      })
      
      RunLoop.current.add(self.timer!, forMode: .default)
    }
  }
  
  func stopGyros() {
    if self.timer != nil {
      self.timer?.invalidate()
      self.timer = nil
      self.motion.stopGyroUpdates()
    }
  }
  
  override init(frame: CGRect) {
    super.init(frame: frame)
    self.addSubview(xPositionTextView)
    self.xPositionTextView.translatesAutoresizingMaskIntoConstraints = false
    self.xPositionTextView.centerXAnchor.constraint(equalTo: self.centerXAnchor).isActive = true
    self.xPositionTextView.centerYAnchor.constraint(equalTo: self.centerYAnchor).isActive = true
    self.xPositionTextView.font = UIFont.systemFont(ofSize: 100)
    self.xPositionTextView.text = "START"
  }
  
  required init?(coder: NSCoder) {
    fatalError("init(coder:) has not been implemented")
  }
}
