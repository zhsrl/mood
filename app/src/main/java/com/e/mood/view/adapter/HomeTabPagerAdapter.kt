package com.e.mood.view.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.e.mood.view.ui.fragment.HomeFragment
import com.e.mood.view.ui.fragment.home_tab_fragment.FemaleFragment
import com.e.mood.view.ui.fragment.home_tab_fragment.MaleFragment

class HomeTabPagerAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {

        var fragment: Fragment? = null

        when(position){
            0 -> {
                fragment = MaleFragment()

                return fragment
            }

            1 -> {
                fragment = FemaleFragment()

                return fragment
            }
        }

        return fragment!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> {
                return "Мужской"
            }

            1 -> {
                return "Женский"
            }
        }

        return null
    }

}