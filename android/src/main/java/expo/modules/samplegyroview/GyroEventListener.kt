package expo.modules.samplegyroview

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

// Currently Screams about BlastBufferQueue on Android 13.
// It seems this warning can be safely ignored.
// Spams the logs really badly though
// See: https://stackoverflow.com/a/75041612
class GyroEventListener(
    private val applicationContext: Context,
    private val cb: (newValue: String) -> Unit
): SensorEventListener {
    var isTracking: Boolean = true
    var sensorManager: SensorManager? = null

    init {
        sensorManager =
            applicationContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val stepSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager?.registerListener(this, stepSensor, SensorManager.SENSOR_STATUS_ACCURACY_HIGH)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(isTracking && event != null) {
            val rawY = -event.values[1]
            if(event.timestamp % 5 == 0L) {
                cb(String.format("%.2f", rawY / 10))
            }
        }
    }
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}
}