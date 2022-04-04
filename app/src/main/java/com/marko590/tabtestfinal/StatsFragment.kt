package com.marko590.tabtestfinal

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.marko590.tabtestfinal.databinding.StatsFragmentBinding


class StatsFragment : Fragment()  {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<StatsFragmentBinding>(inflater,
            com.marko590.tabtestfinal.R.layout.stats_fragment,container,false)

        val calorieChart =binding.calorieChart
        setupBarChart(calorieChart)
        populateBarChart(calorieChart)
        calorieChart.axisLeft.axisMinimum=0f

        val stepChart=binding.stepChart
        setupBarChart(stepChart)
        populateBarChart(stepChart)
        stepChart.axisLeft.axisMinimum=0f

        val months : Array<String> = requireContext().resources.getStringArray(com.marko590.tabtestfinal.R.array.months)

        binding.picker.minValue=1
        binding.picker.maxValue=12
        binding.picker.displayedValues=months
        binding.picker.setOnValueChangedListener { picker, oldVal, newVal ->

            //Display the newly selected number to text view
            binding.textView3.text = "Selected Value : $newVal from $oldVal"

            when (newVal%2) {
                0 -> populateBarChart(calorieChart)
                1 -> populateBarChart1(calorieChart)
                else -> {
                    print("x is neither 1 nor 2")
                }
            }
        }


        var calorieMonthButton= binding.button1

        calorieMonthButton.setOnClickListener { v ->
            val popUpClass = PopUpClass()
            popUpClass.showPopupWindow(v,"Enter the month you want")

        }


        var stepMonthButton= binding.button2

        stepMonthButton.setOnClickListener { v ->
            val popUpClass = PopUpClass()
            popUpClass.showPopupWindow(v,"Enter the month you want")
        }
        return binding.root
    }

    private fun setupBarChart(barChart: BarChart){
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
            ContextCompat.getColor(requireContext(),R.color.primaryDarkColor),
            ContextCompat.getColor(requireContext(),R.color.secondaryLightColor),
            Shader.TileMode.CLAMP))

        barChart.animateY(3000)
        barChart.invalidate()
    }

    private fun populateBarChart(barChart: BarChart){
        val entries: ArrayList<BarEntry> = ArrayList()
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

        barChart.resetZoom()
        barChart.setVisibleXRange(30f,7f)
        //zoom to the last recorded week
        barChart.zoom(barChart.xChartMax/7,1f,0f,0f)
        // move view to the current week
        barChart.moveViewToX(barChart.xChartMax-7)
        barChart.invalidate()
    }
    private fun populateBarChart1(barChart: BarChart){
        val entries: ArrayList<BarEntry> = ArrayList()
        entries.add(BarEntry(1f, 1000f))
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



