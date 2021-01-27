package com.e.mood.view.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.e.mood.R
import com.e.mood.databinding.ActivityMainBinding
import com.e.mood.view.ui.fragment.*
import com.e.mood.viewmodel.MainActivityViewModel
import com.e.mood.viewmodel.ViewModelProviderFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var viewModel: MainActivityViewModel

    private var fragmentList: MutableList<Fragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        bottomNavView = findViewById(R.id.MAIN_bottom_nav_view)

        val providerFactory = ViewModelProviderFactory(this)
        viewModel = ViewModelProvider(this, providerFactory).get(MainActivityViewModel::class.java)

        fragmentList.add(HomeFragment())
        fragmentList.add(CatalogFragment())
        fragmentList.add(BookmarkFragment())
        fragmentList.add(ShopBoxFragment())
        fragmentList.add(ProfileFragment())


    }
}