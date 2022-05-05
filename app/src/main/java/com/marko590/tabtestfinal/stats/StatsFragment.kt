package com.marko590.tabtestfinal.stats


import android.animation.Animator
import android.content.ContentProvider
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.*
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.Nullable
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import com.github.mikephil.charting.charts.BarChart
import com.marko590.tabtestfinal.R
import com.marko590.tabtestfinal.data.AppDatabase
import com.marko590.tabtestfinal.data.AppRepository
import com.marko590.tabtestfinal.data.Food
import com.marko590.tabtestfinal.data.UserViewFood
import com.marko590.tabtestfinal.databinding.StatsFragmentBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import java.text.SimpleDateFormat
import java.time.Month
import java.util.*
import java.util.Observer

class StatsFragment : androidx.fragment.app.Fragment()  {

    private lateinit var mUserViewModel : UserViewFood
    var calorieChart : RoundedBarChart?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = DataBindingUtil.inflate<StatsFragmentBinding>(
            inflater,
            R.layout.stats_fragment, container, false)

        var month= Calendar.getInstance().get(Calendar.MONTH)

        val chartHandler = ChartHandler(requireContext(),month)

        mUserViewModel= ViewModelProvider(this).get(UserViewFood::class.java)

        //Setup the bar chart for calorie intake
        calorieChart = binding.calorieChart
        chartHandler.setupBarChart(calorieChart!!)
        chartHandler.populateBarChart(calorieChart!!,month)
        val months: Array<String> =
           requireContext().resources.getStringArray(R.array.months)
        calorieChart!!.description



        //Show popup window on button click

        val calorieMonthButton = binding.button1


        calorieMonthButton.setOnClickListener {v -> showPopupWindow(inflater,calorieChart!!,view as View,month)}



        //Setup the bar chart for steps
        val stepChart = binding.stepChart
        chartHandler.setupBarChart(stepChart)
        chartHandler.populateBarChart(stepChart!!,month)
        var stepChoice=0

        //Show popup window on button click
        val stepMonthButton= binding.button2
        stepMonthButton.setOnClickListener {v -> showPopupWindow(inflater,stepChart,view as View,month)}

        return binding.root
    }

    fun showPopupWindow(inflater: LayoutInflater,barChart: BarChart,view : View,month: Int){
        // Inflate the popup window layout
        val popupView: View =
            inflater.inflate(R.layout.popup_view_layout, null)

        // Make a popup window using the inflated view
        val popupWindow = PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true)

        // Set elevation of the popup
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE))

        popupWindow.setElevation(30f)


        popupView.findViewById<CardView>(R.id.cardView).setBackgroundResource(R.drawable.pu_background1)
        val popupHandler = ChartPopUpHandler(popupWindow,popupView,requireContext(),month)
        popupHandler.setup()

        val buttonEdit =
            popupView.findViewById<Button>(R.id.messageButton)
        popupWindow.setBackgroundDrawable(null)

        val closeButton=
            popupView.findViewById<TextView>(R.id.cancelButton)

        //Set event listener for the close button
        closeButton.setOnClickListener{


            val anim=popupView.animate().translationX(1600f)
            anim.setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}
                override fun onAnimationEnd(animation: Animator) {
                    popupWindow.dismiss()
                }
                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })
        }

        val chartHandler = ChartHandler(requireContext(),month)

        //Display button listener
        buttonEdit.setOnClickListener{
            // Fetch month array from resources
            val months: Array<String> =
                popupView.context.resources.getStringArray(R.array.months)

            // Make toast message for the selected month
            Toast.makeText(
                requireView().context,
                "You have selected ${months[popupHandler.currentChoice]}",
                Toast.LENGTH_SHORT
            ).show()

            // Mocked data changing, need to replace
            when (popupHandler.currentChoice % 2) {
                0 -> chartHandler.populateBarChart(barChart,popupHandler.currentChoice)
                1 -> chartHandler.populateBarChart1(barChart,popupHandler.currentChoice)
                else -> {
                    print("x is neither 1 nor 2")
                }
            }
            val anim=popupView.animate().translationX(1600f)
            anim.setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}
                override fun onAnimationEnd(animation: Animator) {
                    popupWindow.dismiss()
                }
                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })
        }
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0)
    }

}

private fun <T> LiveData<T>.observe(viewLifecycleOwner: LifecycleOwner, observer: Observer) {

}



