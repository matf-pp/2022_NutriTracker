package com.marko590.tabtestfinal.stats

import android.content.Context
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.view.View
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.marko590.tabtestfinal.R
import java.util.ArrayList

class ChartHandler(var context: Context,var month: Int) {

    fun setupBarChart(barChart: RoundedBarChart){
        //adjusting left y axis

        barChart.axisLeft.setDrawGridLines(true)
        barChart.axisLeft.setDrawLabels(false)
        barChart.axisLeft.setDrawAxisLine(false)
        barChart.axisLeft.axisMinimum = 0f
        //adjusting right y axis
        barChart.axisRight.isEnabled = false

        //adjusting xAxis
        barChart.xAxis.setDrawGridLines(false)
        barChart.xAxis.setDrawAxisLine(false)
        barChart.xAxis.position= XAxis.XAxisPosition.BOTTOM
        barChart.xAxis.setDrawLabels(true)

        //misc customization
        barChart.legend.isEnabled = false
        barChart.description.isEnabled = true
        barChart.description= Description()

        barChart.description.textSize=25f


        //making a linear gradient
        val mPaint: Paint = barChart.renderer.paintRender
        mPaint.setShader(
            LinearGradient(
            500f,
            0f,
            500f,
            1100f,
            intArrayOf
                (
                ContextCompat.getColor(context, R.color.primaryDarkColor) ,
                ContextCompat.getColor(context, R.color.primaryColor),
                ContextCompat.getColor(context, R.color.secondaryLightColor)
            ),
            floatArrayOf(0f,0.4f,1f),
            Shader.TileMode.CLAMP
        )
        )


        barChart.invalidate()
    }


    fun populateBarChart(barChart: BarChart,selected_month: Int){
        val entries: ArrayList<BarEntry> = ArrayList()
        //mocked data
        entries.add(BarEntry(1f, 4000f))
        entries.add(BarEntry(2f, 1000f))
        entries.add(BarEntry(3f, 2000f))
        entries.add(BarEntry(4f, 1500f))
        entries.add(BarEntry(5f, 1300f))
        entries.add(BarEntry(6f, 2000f))
        entries.add(BarEntry(7f, 1500f))
        entries.add(BarEntry(8f, 1300f))
        entries.add(BarEntry(9f, 200f))
        entries.add(BarEntry(10f, 1500f))
        entries.add(BarEntry(11f, 1300f))
        entries.add(BarEntry(12f, 200f))
        entries.add(BarEntry(13f, 1500f))
        entries.add(BarEntry(14f, 1300f))
        entries.add(BarEntry(15f, 2000f))
        entries.add(BarEntry(16f, 1500f))
        entries.add(BarEntry(17f, 1300f))
        entries.add(BarEntry(18f, 2000f))
        entries.add(BarEntry(19f, 1500f))
        entries.add(BarEntry(20f, 1300f))
        entries.add(BarEntry(21f, 2000f))
        entries.add(BarEntry(22f, 1500f))
        entries.add(BarEntry(23f, 1300f))
        entries.add(BarEntry(24f, 2000f))
        entries.add(BarEntry(25f, 1500f))
        entries.add(BarEntry(26f, 1300f))
        entries.add(BarEntry(27f, 2000f))
        entries.add(BarEntry(28f, 1500f))
        entries.add(BarEntry(29f, 2000f))
        entries.add(BarEntry(30f, 5000f))
        val barDataSet = BarDataSet(entries, "")

        val data = BarData(barDataSet)

        barChart.data = data

        //clean up the zoom levels
        barChart.resetZoom()

        //make the minimum horizontal zoom be 7 units, i.e. 7 days
        barChart.setVisibleXRange(30f,7f)
        val months: Array<String> =
            context.resources.getStringArray(R.array.months)
        barChart.description.setPosition(550f, 75f)
        barChart.description.text=months[selected_month]
        //zoom to the last recorded week
        barChart.zoom(barChart.xChartMax/7,1f,0f,0f)

        val xAxis = barChart.xAxis
        xAxis.labelRotationAngle = -45f
        // move view to the current week
        barChart.moveViewToX(barChart.xChartMax-7)
        barChart.animateY(1000)
        barChart.invalidate()
    }


    fun populateBarChart1(barChart: BarChart,selected_month: Int){
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
        val months: Array<String> =
            context.resources.getStringArray(R.array.months)
        barChart.description.setPosition(550f, 75f)
        barChart.description.text=months[selected_month]
        barChart.resetZoom()
        barChart.setVisibleXRange(30f,7f)
        barChart.zoom(barChart.xChartMax/7,1f,0f,0f)
        barChart.notifyDataSetChanged()
        barChart.animateY(1000)
        barChart.invalidate()
    }


}