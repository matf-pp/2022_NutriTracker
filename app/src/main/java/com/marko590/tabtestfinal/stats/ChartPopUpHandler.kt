package com.marko590.tabtestfinal.stats

import android.animation.Animator
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.cardview.widget.CardView
import com.github.mikephil.charting.charts.BarChart
import com.marko590.tabtestfinal.R
import com.marko590.tabtestfinal.databinding.StatsFragmentBinding
import java.time.Month

class ChartPopUpHandler(private val popupWindow: PopupWindow,
                        private val popupView: View,
                        private val context: Context,
                        private var month: Int
) {

     var currentChoice=0

     fun setup(){
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(200f);
        }

        val titleText =
            popupView.findViewById<TextView>(R.id.titleText)

        val buttonEdit =
            popupView.findViewById<Button>(R.id.messageButton)
            popupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE))
            popupView!!.setAnimation(AnimationUtils.loadAnimation(this.popupView!!.context,
                R.anim.popupanim
            ))

        var picker =
            popupView.findViewById<NumberPicker>(R.id.picker1)

        setUpPicker(picker)
    }

    fun setUpPicker(picker: NumberPicker) {
        val months: Array<String> =
            popupView!!.context.resources.getStringArray(R.array.months)
        picker.minValue = 1
        picker.maxValue = 12
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            picker.textSize = 75f
        }
        popupView.findViewById<NumberPicker>(R.id.picker1).setOnValueChangedListener { picker, oldVal, newVal ->
            currentChoice = newVal - 1
        }
        picker.elevation =
            popupView!!.resources.getDimension(com.google.android.material.R.dimen.m3_menu_elevation)
        picker.displayedValues = months

        picker.value=month+1
    }


}