package com.e.mood.view.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.e.mood.R
import com.e.mood.view.adapter.HomeTabPagerAdapter
import com.google.android.material.tabs.TabLayout


class HomeFragment : Fragment() {

    private lateinit var tabAdapter: HomeTabPagerAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var pager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout = view.findViewById(R.id.TBL_fragment_home_tab_layout)

        tabAdapter = HomeTabPagerAdapter(childFragmentManager)

        pager = view.findViewById(R.id.pager)
        pager.adapter = tabAdapter

        tabLayout.setupWithViewPager(pager)

        tabLayout.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                pager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                pager.currentItem = tab!!.position
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                pager.currentItem = tab!!.position
            }

        })


    }




}