package com.example.androidhardviewlesson10

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.androidhardviewlesson10.databinding.ActivityMainBinding
import io.alterac.blurkit.BlurLayout

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var blurLayout: BlurLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    var home: Boolean = false
    var popular: Boolean = false
    var random: Boolean = false
    var liked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val decorView = window.decorView

        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                or View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        setSupportActionBar(binding.appBarMain.toolbar)
        binding.navView.itemIconTintList = null
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val bottomNavigationView = binding.appBarMain. contentMain.navigationBottom
        val     navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        binding.navView.setNavigationItemSelectedListener(this)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_popular,
                R.id.nav_random,
                R.id.nav_liked,
                R.id.nav_history,
                R.id.nav_about
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        bottomNavigationView.setupWithNavController(navController)

        blurLayout = binding.appBarMain.contentMain.blurLayout

        navController.addOnDestinationChangedListener { _, destination, _ ->

            binding.appBarMain.contentMain.indicatorHome.isInvisible =
                destination.id != R.id.nav_home
            binding.appBarMain.contentMain.indicatorPopular.isInvisible =
                destination.id != R.id.nav_popular
            binding.appBarMain.contentMain.indicatorRandom.isInvisible =
                destination.id != R.id.nav_random
            binding.appBarMain.contentMain.indicatorLiked.isInvisible =
                destination.id != R.id.nav_liked
            //bottomNavigationView.isVisible = destination.id !in destinationsWithoutBottomNav
            binding.appBarMain.contentMain.navigationButtonLayout.isVisible =
                destination.id !in destinationsWithoutBottomNav

        }


    }

    private val destinationsWithoutBottomNav = listOf(
        R.id.nav_history,
        R.id.nav_about,
        R.id.imageFragment,
        R.id.setImageFragment
    )

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//
//        menuInflater.inflate(R.menu.main, menu)
//        return true
//    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when(item.itemId)
//        {
//            R.id.nav_home ->{
//                item.isChecked = true
//                binding.appBarMain.toolbar.inflateMenu(R.menu.main)
//            }
//            R.id.nav_random->{
//                item.isChecked=true
//                binding.appBarMain.toolbar.inflateMenu(R.menu.random_menu)
//            }
//        }
        return true
    }


}