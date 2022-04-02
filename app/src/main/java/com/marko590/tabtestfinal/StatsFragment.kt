package com.marko590.tabtestfinal

import android.R
import android.content.Context
import android.os.Bundle
import android.text.AutoText
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.NumberPicker
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate
import com.marko590.tabtestfinal.databinding.StatsFragmentBinding
import javax.xml.datatype.DatatypeConstants.MONTHS


class StatsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<StatsFragmentBinding>(inflater,
            com.marko590.tabtestfinal.R.layout.stats_fragment,container,false)
        binding.textView.text="Statistics"

        val barChart =binding.calorieChart

        setupBarChart(barChart)
        populateBarChart(barChart)

        val barChart1=binding.stepChart
        //hide grid lines
        setupBarChart(barChart1)
        populateBarChart(barChart1)

        //draw chart




        val MONTHS = arrayOf(
            "January",
            "Februrary",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
        )
        //val customerAutoTV: AutoCompleteTextView = binding.customerTextView

        //val adapter: ArrayAdapter<String> =
        //    ArrayAdapter<String>(context as Context, R.layout.simple_spinner_item, MONTHS)
        //draw chart
        //customerAutoTV.setAdapter(adapter)

        binding.picker.minValue=1
        binding.picker.maxValue=12
        binding.picker.displayedValues=MONTHS
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

        return binding.root
    }


    private fun setupBarChart(barChart: BarChart){
        barChart.axisLeft.setDrawGridLines(true)
        barChart.axisRight.setDrawGridLines(false)

        barChart.axisLeft.maxWidth=4f
        barChart.xAxis.setDrawGridLines(true)
        barChart.xAxis.setDrawAxisLine(true)
        barChart.axisRight.setDrawLabels(false)
        //remove right y-axis
        barChart.axisRight.isEnabled = false
        barChart.xAxis.position=XAxis.XAxisPosition.BOTTOM

        //remove legend
        barChart.legend.isEnabled = false


        //remove description label
        barChart.description.isEnabled = false


        //add animation
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



