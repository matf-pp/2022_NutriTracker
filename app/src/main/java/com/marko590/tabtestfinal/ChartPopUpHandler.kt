package com.marko590.tabtestfinal

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*

class ChartPopUpHandler(private val popupWindow: PopupWindow, private val popupView: View) {
     var currentChoice=0

     fun setup(){
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(200f);
        }

        val titleText =
            popupView.findViewById<TextView>(com.marko590.tabtestfinal.R.id.titleText)

        val buttonEdit =
            popupView.findViewById<Button>(com.marko590.tabtestfinal.R.id.messageButton)
            popupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE))
            popupView!!.setAnimation(AnimationUtils.loadAnimation(this.popupView!!.context,R.anim.popupanim))

        var picker =
            popupView.findViewById<NumberPicker>(com.marko590.tabtestfinal.R.id.picker1)

        setUpPicker(picker)
    }

    fun setUpPicker(picker: NumberPicker) {
        val months: Array<String> =
            popupView!!.context.resources.getStringArray(com.marko590.tabtestfinal.R.array.months)
        picker.minValue = 1
        picker.maxValue = 12
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            picker.textSize = 75f
        }
        popupView.findViewById<NumberPicker>(com.marko590.tabtestfinal.R.id.picker1).setOnValueChangedListener { picker, oldVal, newVal ->
            currentChoice = newVal - 1
        }
        picker.elevation =
            popupView!!.resources.getDimension(com.google.android.material.R.dimen.m3_menu_elevation)
        picker.displayedValues = months

    }
}