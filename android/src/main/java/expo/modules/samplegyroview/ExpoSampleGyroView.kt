package expo.modules.samplegyroview

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView
import expo.modules.kotlin.AppContext
import expo.modules.kotlin.viewevent.EventDispatcher
import expo.modules.kotlin.views.ExpoView

class ExpoSampleGyroView(context: Context, appContext: AppContext) : ExpoView(context, appContext) {
    val onGyroEvent by EventDispatcher()

    internal val textView = TextView(context).also {
        addView(it)
        it.layoutParams = LayoutParams(
            android.view.ViewGroup.LayoutParams.MATCH_PARENT,
            android.view.ViewGroup.LayoutParams.MATCH_PARENT,
        )
        it.gravity = Gravity.CENTER
        it.setTextSize(TypedValue.COMPLEX_UNIT_SP,70f);
    }

    fun updateText(newValue: String) {
        textView.text = newValue
    }
}
