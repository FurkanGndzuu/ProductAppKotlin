package com.frkn.productappkotlin.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class screenSlideAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity)  {

    var fragments = ArrayList<Fragment>()
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    fun addFragment(fragment : Fragment){
        fragments.add(fragment)
    }
}