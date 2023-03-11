package expo.modules.samplegyroview

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.core.os.bundleOf
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition
import expo.modules.kotlin.viewevent.EventDispatcher

const val kOnGyroEvent = "onGyroEvent"

// Currently Screams about BlastBufferQueue on Android 13.
// It seems this warning can be safely ignored.
// Spams the logs really badly though
// See: https://stackoverflow.com/a/75041612
class StepEventListener(
  private val cb: (newValue: String) -> Unit
): SensorEventListener {
  var isTracking: Boolean = true

  override fun onSensorChanged(event: SensorEvent?) {
    if(isTracking && event != null) {
      val rawY = -event.values[1]
      if(event.timestamp % 5 == 0L) {
        cb(String.format("%.2f", rawY))
      }
    }
  }
  override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}
}

class ExpoSampleGyroViewModule : Module() {
  var sensorManager: SensorManager? = null
  var stepListener: StepEventListener? = null
  var isInitialized: Boolean = false
  var placeholderText: String? = null

  override fun definition() = ModuleDefinition {
    Name("ExpoSampleGyroView")

    View(ExpoSampleGyroView::class) {
      Events(kOnGyroEvent)

      Prop("placeholderText") { view: ExpoSampleGyroView, text: String? ->
        view.textView.text = text ?: "START"
        placeholderText = text
      }

      Prop("track") { view: ExpoSampleGyroView, isTracking: Boolean ->
        val activity = appContext.activityProvider?.currentActivity
        val applicationContext = activity?.applicationContext

        if(applicationContext != null && !isInitialized) {
          isInitialized = true
          sensorManager =
            applicationContext.applicationContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager

          stepListener = StepEventListener {
              view.updateText(it)
              view.onGyroEvent(mapOf("y" to it))
          }

          val stepSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
          sensorManager?.registerListener(stepListener, stepSensor, SensorManager.SENSOR_STATUS_ACCURACY_HIGH)
        }

        stepListener?.isTracking = isTracking
        view.textView.text = placeholderText ?: "START"
      }
    }
  }
}
