package com.marko590.tabtestfinal.menu.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.marko590.tabtestfinal.R
import com.marko590.tabtestfinal.databinding.AboutFragmentBinding

class AboutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<AboutFragmentBinding>(inflater,
            R.layout.about_fragment,container,false)



        return binding.root
    }
}