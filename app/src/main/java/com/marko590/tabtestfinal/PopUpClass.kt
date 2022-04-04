package com.marko590.tabtestfinal

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import org.w3c.dom.Text


class PopUpClass {
    //PopupWindow display method
    var popUpView : View?=null
    var currentChoice: Int = 0
    fun showPopupWindow(view: View,msg: String) {

        val inflater =
            view.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        popUpView = inflater.inflate(R.layout.popup_view_layout, null)
        popUpView!!.setAnimation(AnimationUtils.loadAnimation(this.popUpView!!.context,R.animator.popupanim))
        val width = LinearLayout.LayoutParams.MATCH_PARENT
        val height = LinearLayout.LayoutParams.MATCH_PARENT

        val focusable = true

        val popupWindow = PopupWindow(popUpView, width, height, focusable)

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)


        val titleText = popUpView!!.findViewById<TextView>(R.id.titleText)
        titleText.setText(msg)

        val buttonEdit = popUpView!!.findViewById<Button>(R.id.messageButton)
        popupWindow.setBackgroundDrawable(null)

        buttonEdit.setOnClickListener {
            val months : Array<String> =popUpView!!.context.resources.getStringArray(R.array.months)
            Toast.makeText(view.context, "You have selected ${months[currentChoice]}", Toast.LENGTH_SHORT).show()
            popupWindow.dismiss()
        }

        var picker = popUpView!!.findViewById<NumberPicker>(R.id.picker1)
        setUpPicker(picker)
        val closeButton= popUpView!!.findViewById<ImageButton>(R.id.closeButton)
        closeButton.setOnClickListener {
            popupWindow.dismiss()
        }



    }


    fun setUpPicker(picker: NumberPicker){
        val months : Array<String> =popUpView!!.context.resources.getStringArray(R.array.months)
        picker.minValue=1
        picker.maxValue=12
        picker.textSize=75f
        picker.elevation=popUpView!!.resources.getDimension(com.google.android.material.R.dimen.m3_menu_elevation)
        picker.displayedValues=months
        picker.setOnValueChangedListener { picker, oldVal, newVal ->
            popUpView!!.findViewById<TextView>(R.id.titleText).text= "Enter the month you want: ${months[newVal-1]}"
            currentChoice=newVal-1

        }
    }
}