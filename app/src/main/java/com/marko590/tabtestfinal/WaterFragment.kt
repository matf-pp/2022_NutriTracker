package com.marko590.tabtestfinal

import android.app.Activity
import android.hardware.SensorEventListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.marko590.tabtestfinal.databinding.WaterFragmentBinding
import kotlin.math.roundToInt
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorManager
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContentProviderCompat.requireContext
import kotlinx.android.synthetic.main.water_fragment.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class WaterFragment : Fragment(), SensorEventListener {

    var running = false
    var sensorManager:SensorManager? = null

    override fun onResume() {
        super.onResume()
        running = true
        var stepsSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (stepsSensor == null) {
            Toast.makeText(requireContext(), "No Step Counter Sensor !", Toast.LENGTH_SHORT).show()
        } else {
            sensorManager?.registerListener(this, stepsSensor, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        running = false
        sensorManager?.unregisterListener(this)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onSensorChanged(event: SensorEvent) {

        val sharedPref = requireContext().getSharedPreferences("mySharedPref", Context.MODE_PRIVATE)
        val stepsGoal = sharedPref.getInt("stepsGoal", 0)
        if (running) {
            if(event.values[0].toInt() > stepsGoal){
                event.values[0] = stepsGoal.toFloat()
            }
            tvStepsProgress.text = "" + event.values[0].toInt() + "/$stepsGoal"
        }

        val progress = ((event.values[0].toInt() * 100) / stepsGoal)
        pbStepsTracker.progress = progress

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorManager = requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        super.onCreateView(inflater, container, savedInstanceState)

        val binding = DataBindingUtil.inflate<WaterFragmentBinding>(inflater,
            R.layout.water_fragment,container,false)


        val progressBar = binding.pbWaterIntake
        val sharedPrefIntake = requireActivity().getSharedPreferences("mySharedPref", Activity.MODE_PRIVATE)
        val glassSize = resources.getStringArray(R.array.glassSize)
        val spinner = binding.sGlasses
        if(spinner != null) {
            val adapter = ArrayAdapter(
                requireContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                glassSize
            )
            spinner.adapter = adapter
        }

        val editorProgress = sharedPrefIntake.edit()

        var progress = sharedPrefIntake.getInt("waterProgress", 0)
        var waterToday = sharedPrefIntake.getInt("waterIntake", 0)
        binding.tvWaterIntake.text = waterToday.toString()+"ml/2000ml"
        progressBar.progress=progress
        binding.tvWaterProgress.text = "("+progress.toString()+"%)"
        binding.btnPlus.setOnClickListener {
            if(progress < 100) {
                val selectedSize = spinner.selectedItem.toString().take(3).toInt()

                waterToday = waterToday + selectedSize
                progress = (100.0 * waterToday / 2000).toInt()
                if(progress > 100){
                    progress = 100

                }
                binding.tvWaterIntake.text = waterToday.toString()+"ml/2000ml"
                binding.tvWaterProgress.text = "("+progress.toString()+"%)"
                sharedPrefIntake.edit().putInt("waterProgress", progress).apply()
                sharedPrefIntake.edit().putInt("waterIntake", waterToday).apply()

                progressBar.progress = progress

            }
            val selectedSize = spinner.selectedItem.toString().take(3).toInt()
            binding.cupsLeft.text = getString(R.string.cupsLeft, ((2000 - waterToday ) / selectedSize))


        }

        var stepsProgress = 0
        var stepsGoal = 0
        binding.btnOk.setOnClickListener {
            pbStepsTracker.progress = 0
            stepsGoal = binding.txtSteps.text.toString().toInt()
            editorProgress.apply {
                putInt("stepsGoal", stepsGoal)
                apply()
            }
            binding.tvStepsProgress.text =  getString(R.string.stepsProgress, stepsProgress, stepsGoal)
        }



        val currDate = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val currDateFormatted = currDate.format(formatter)
        val today = sharedPrefIntake.getString("dateToday", null)
        if(today == null){
            editorProgress.apply{
                putString("dateToday", currDateFormatted)
                apply()
            }
        } else if(currDateFormatted != today){
            editorProgress.apply {
                putString("dateToday", currDateFormatted)
                putInt("stepsProgress", stepsProgress)
                apply()
            }
            editorProgress.apply {
                putInt("stepsProgress", 0)
                putInt("waterIntake", 0)
                putInt("waterProgress", 0)
                stepsProgress = sharedPrefIntake.getInt("stepsProgress", 0)
                binding.tvStepsProgress.text =  getString(R.string.stepsProgress, stepsProgress, stepsGoal)
                binding.tvWaterIntake.text = "0ml/2000ml"
                progressBar.progress = 0
                binding.tvWaterProgress.text = "(0%)"
                apply()

            }
        }







        return binding.root
    }
}