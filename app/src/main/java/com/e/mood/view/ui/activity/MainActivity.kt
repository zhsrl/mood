package com.e.mood.view.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.e.mood.R
import com.e.mood.view.ui.fragment.*
import com.e.mood.view.ui.util.Pager
import com.e.mood.view.ui.util.ViewPagerAdapter
import com.e.mood.viewmodel.MainActivityViewModel
import com.e.mood.viewmodel.ViewModelProviderFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity() : AppCompatActivity() {

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

        var window: Window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        window.statusBarColor = ContextCompat.getColor(applicationContext, R.color.top_bar_color)

        val pager: Pager = findViewById(R.id.pager)
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, fragmentList)

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()

        bottomNavView.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {

                var selectedFragment: Fragment? = null

                when(item.itemId){
                    R.id.bottom_item_home -> {
                        selectedFragment = HomeFragment()
                        supportFragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, selectedFragment)
                                .commit()

                        item.isChecked = true
                    }

                    R.id.bottom_item_catalog -> {
                        selectedFragment = CatalogFragment()
                        supportFragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, selectedFragment)
                                .commit()

                        item.isChecked = true
                    }

                    R.id.bottom_item_bookmark -> {
                        selectedFragment = BookmarkFragment()
                        supportFragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, selectedFragment)
                                .disallowAddToBackStack()
                                .commit()

                        item.isChecked = true
                    }

                    R.id.bottom_item_shop_box -> {
                        selectedFragment = ShopBoxFragment()
                        supportFragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, selectedFragment)
                                .disallowAddToBackStack()
                                .commit()

                        item.setChecked(true)
                    }

                    R.id.bottom_item_profile -> {

                        var currentUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser

                        if(currentUser == null){
                            selectedFragment = ProfileFragment()
                            supportFragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container, selectedFragment)
                                    .disallowAddToBackStack()
                                    .commit()

                            item.isChecked = true
                        }else{
                            val oldFragment = ProfileFragment()
                            selectedFragment = SignedFragment()
                            supportFragmentManager.beginTransaction()
                                    .remove(oldFragment)
                                    .replace(R.id.fragment_container, selectedFragment)
                                    .disallowAddToBackStack()
                                    .commit()

                            item.isChecked = true
                        }



                    }
                }

                return false
            }
        })

    }



    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onStop() {
        super.onStop()
        finish()
    }

}