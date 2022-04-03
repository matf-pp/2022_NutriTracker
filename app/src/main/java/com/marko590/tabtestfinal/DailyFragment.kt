package com.marko590.tabtestfinal
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.marko590.tabtestfinal.databinding.DailyFragmentBinding


class DailyFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<DailyFragmentBinding>(inflater,
            R.layout.daily_fragment,container,false)

        binding.tvPercentage.text = binding.pbCaloriesTracker.progress.toString()
        binding.pbCaloriesTracker.progress = binding.pbCaloriesTracker.progress

        binding.btnAdd.setOnClickListener {
            Intent(context, UserInfoActivity::class.java).also {
                startActivity(it)
            }
        }
        return binding.root
    }

}
