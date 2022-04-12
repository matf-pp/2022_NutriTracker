package com.marko590.tabtestfinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.marko590.tabtestfinal.databinding.WaterFragmentBinding

class WaterFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<WaterFragmentBinding>(inflater,
            R.layout.water_fragment,container,false)


        return binding.root
    }
}