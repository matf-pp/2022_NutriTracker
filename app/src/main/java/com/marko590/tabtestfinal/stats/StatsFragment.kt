package com.marko590.tabtestfinal.stats


import android.animation.Animator
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.graphics.drawable.ColorDrawable
import android.os.*
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.marko590.tabtestfinal.R
import com.marko590.tabtestfinal.databinding.StatsFragmentBinding
import java.util.*


class StatsFragment : androidx.fragment.app.Fragment()  {

    var calorieChart : RoundedBarChart?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<StatsFragmentBinding>(
            inflater,
            R.layout.stats_fragment, container, false
        )

        //Setup the bar chart for calorie intake
        calorieChart = binding.calorieChart
        setupBarChart(calorieChart!!)
        populateBarChart(calorieChart!!)
        calorieChart!!.axisLeft.axisMinimum = 0f

        //Setup the bar chart for steps
        val stepChart = binding.stepChart
        setupBarChart(stepChart)
        populateBarChart(stepChart)

        stepChart.axisLeft.axisMinimum = 0f

        //Fetch array of months from resources
        val months: Array<String> =
            requireContext().resources.getStringArray(R.array.months)

        var calorieMonthButton = binding.button1

        //Show popup window on button click
        calorieMonthButton.setOnClickListener { v ->
            showPopupWindow(inflater,binding,calorieChart!!)
        }

        var stepMonthButton= binding.button2

        //Show popup window on button click
        stepMonthButton.setOnClickListener { v ->
            showPopupWindow(inflater,binding,stepChart!!)
        }
        return binding.root
    }
    fun showPopupWindow(inflater: LayoutInflater,binding: StatsFragmentBinding,barChart: BarChart){

        // Inflate the popup window layout
        val popupView: View =
            inflater.inflate(R.layout.popup_view_layout, null)

        // Make a popup window using the inflated view
        val popupWindow = PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true)

        // Set elevation of the popup
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(30f);
        }

        popupView.findViewById<CardView>(R.id.cardView).setBackgroundResource(R.drawable.pu_background1)
        val popupHandler: ChartPopUpHandler = ChartPopUpHandler(popupWindow,popupView)
        popupHandler.setup()

        val titleText =
            popupView.findViewById<TextView>(R.id.titleText)


        val buttonEdit =
            popupView.findViewById<Button>(R.id.messageButton)
        popupWindow.setBackgroundDrawable(null)

        val closeButton=
            popupView.findViewById<TextView>(R.id.cancelButton)


        //Set event listener for the close button
        closeButton.setOnClickListener{


            var anim=popupView.animate().translationX(1600f)
            anim.setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}
                override fun onAnimationEnd(animation: Animator) {
                    popupWindow.dismiss()
                }
                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })

        }


        val sharedPref : SharedPreferences= requireContext().getSharedPreferences("UserInfoPref", Context.MODE_PRIVATE)
        binding.calorieChartText.text=sharedPref.getString("usrName","No username entered")



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
                0 -> populateBarChart(barChart!!)
                1 -> populateBarChart1(barChart!!)
                else -> {
                    print("x is neither 1 nor 2")
                }
            }
            var anim=popupView.animate().translationX(1600f)
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
    private fun setupBarChart(barChart: RoundedBarChart){
        //adjusting left y axis

        barChart.axisLeft.setDrawGridLines(true)
        barChart.axisLeft.setDrawLabels(false)
        barChart.axisLeft.setDrawAxisLine(false)

        //adjusting right y axis
        barChart.axisRight.isEnabled = false

        //adjusting xAxis
        barChart.xAxis.setDrawGridLines(false)
        barChart.xAxis.setDrawAxisLine(false)
        barChart.xAxis.position=XAxis.XAxisPosition.BOTTOM
        barChart.xAxis.setDrawLabels(true)

        //misc customization
        barChart.legend.isEnabled = false
        barChart.description.isEnabled = false

        //making a linear gradient
        val mPaint: Paint = barChart.renderer.paintRender
        mPaint.setShader(LinearGradient(
            500f,
            0f,
            500f,
            1100f,
            intArrayOf
                (
                ContextCompat.getColor(requireContext(), R.color.primaryDarkColor) ,
                ContextCompat.getColor(requireContext(), R.color.primaryColor),
                ContextCompat.getColor(requireContext(), R.color.secondaryLightColor)
                ),
            floatArrayOf(0f,0.4f,1f),
            Shader.TileMode.CLAMP
            )
        )

        barChart.animateY(3000)
        barChart.invalidate()
    }

    private fun populateBarChart(barChart: BarChart){
        val entries: ArrayList<BarEntry> = ArrayList()
        //mocked data
        entries.add(BarEntry(1f, 4f))
        entries.add(BarEntry(2f, 10f))
        entries.add(BarEntry(3f, 2f))
        entries.add(BarEntry(4f, 15f))
        entries.add(BarEntry(5f, 13f))
        entries.add(BarEntry(6f, 2f))
        entries.add(BarEntry(7f, 15f))
        entries.add(BarEntry(8f, 13f))
        entries.add(BarEntry(9f, 2f))
        entries.add(BarEntry(10f, 15f))
        entries.add(BarEntry(11f, 13f))
        entries.add(BarEntry(12f, 2f))
        entries.add(BarEntry(13f, 15f))
        entries.add(BarEntry(14f, 13f))
        entries.add(BarEntry(15f, 2f))
        entries.add(BarEntry(16f, 15f))
        entries.add(BarEntry(17f, 13f))
        entries.add(BarEntry(18f, 2f))
        entries.add(BarEntry(19f, 15f))
        entries.add(BarEntry(20f, 13f))
        entries.add(BarEntry(21f, 2f))
        entries.add(BarEntry(22f, 15f))
        entries.add(BarEntry(23f, 13f))
        entries.add(BarEntry(24f, 2f))
        entries.add(BarEntry(25f, 15f))
        entries.add(BarEntry(26f, 13f))
        entries.add(BarEntry(27f, 2f))
        entries.add(BarEntry(28f, 7f))
        entries.add(BarEntry(29f, 20f))
        entries.add(BarEntry(30f, 5f))
        val barDataSet = BarDataSet(entries, "")

        val data = BarData(barDataSet)

        barChart.data = data

        //clean up the zoom levels
        barChart.resetZoom()

        //make the minimum horizontal zoom be 7 units, i.e. 7 days
        barChart.setVisibleXRange(30f,7f)

        //zoom to the last recorded week
        barChart.zoom(barChart.xChartMax/7,1f,0f,0f)

        val xAxis = barChart.xAxis
        xAxis.labelRotationAngle = -45f
        // move view to the current week
        barChart.moveViewToX(barChart.xChartMax-7)

        barChart.invalidate()
    }
    private fun populateBarChart1(barChart: BarChart){
        val entries: ArrayList<BarEntry> = ArrayList()
        entries.add(BarEntry(1f, 10f))
        entries.add(BarEntry(2f, 5f))
        entries.add(BarEntry(3f, 3f))
        entries.add(BarEntry(4f, 5f))
        entries.add(BarEntry(5f, 6f))
        entries.add(BarEntry(6f, 11f))
        entries.add(BarEntry(7f, 6f))
        entries.add(BarEntry(8f, 11f))
        entries.add(BarEntry(9f, 6f))
        entries.add(BarEntry(10f, 11f))
        val barDataSet = BarDataSet(entries, "")
        barDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)

        val data = BarData(barDataSet)

        barChart.data = data

        barChart.resetZoom()
        barChart.setVisibleXRange(30f,7f)
        barChart.zoom(barChart.xChartMax/7,1f,0f,0f)

        barChart.invalidate()
    }

}



