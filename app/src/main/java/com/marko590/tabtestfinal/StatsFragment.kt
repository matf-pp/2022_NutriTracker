package com.marko590.tabtestfinal

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
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

        val barChart =binding.calorieChart
        setupBarChart(barChart)
        populateBarChart(barChart)

        val barChart1=binding.stepChart
        setupBarChart(barChart1)
        populateBarChart(barChart1)

        val months : Array<String> = requireContext().resources.getStringArray(com.marko590.tabtestfinal.R.array.months)

        binding.picker.minValue=1
        binding.picker.maxValue=12
        binding.picker.displayedValues=months
        binding.picker.setOnValueChangedListener { picker, oldVal, newVal ->

            //Display the newly selected number to text view
            binding.textView3.text = "Selected Value : $newVal from $oldVal"

            when (newVal%2) {
                0 -> populateBarChart(barChart)
                1 -> populateBarChart1(barChart)
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
        barChart.axisLeft.setDrawGridLines(true)
        barChart.axisRight.setDrawGridLines(false)


        barChart.axisLeft.maxWidth=4f
        barChart.xAxis.setDrawGridLines(false)
        barChart.xAxis.setDrawAxisLine(true)
        barChart.axisRight.setDrawLabels(false)


        barChart.axisRight.isEnabled = false
        barChart.xAxis.position=XAxis.XAxisPosition.BOTTOM


        barChart.legend.isEnabled = false
        barChart.description.isEnabled = false


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

        val barDataSet = BarDataSet(entries, "")
        barDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)

        val data = BarData(barDataSet)

        barChart.data = data

        barChart.invalidate()
    }
    private fun populateBarChart1(barChart: BarChart){
        val entries: ArrayList<BarEntry> = ArrayList()
        entries.add(BarEntry(1f, 1f))
        entries.add(BarEntry(2f, 5f))
        entries.add(BarEntry(3f, 3f))
        entries.add(BarEntry(4f, 5f))
        entries.add(BarEntry(5f, 6f))
        entries.add(BarEntry(6f, 11f))

        val barDataSet = BarDataSet(entries, "")
        barDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)

        val data = BarData(barDataSet)
        barChart.data = data
        barChart.invalidate()

    }
}



