package com.marko590.tabtestfinal

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.marko590.tabtestfinal.databinding.WaterFragmentBinding
import kotlin.math.round
import kotlin.math.roundToInt

class WaterFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<WaterFragmentBinding>(inflater,
            R.layout.water_fragment,container,false)



        return binding.root
    }
}