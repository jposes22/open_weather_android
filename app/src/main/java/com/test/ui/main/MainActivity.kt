package com.test.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.test.R
import com.test.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

//LSIT OF TODOS SORT BY PRIORITY. SURELY IT HAVE TO GROW A LOT

//TODO: HIGH PRIO DECISION: Content and utility of Fragments
//TODO: Search and implement a weather library ESSENTIAL PRIO https://github.com/KwabenBerko/OpenWeatherMap-Android-Library
//TODO: click listener in cities searcher to add it in a List<city> to make a "fav list" easily accessible to the user AND MAYBE select one of them to set as main city HIGH PRIO
//TODO: Make relation between our cities database and weather info HIGH PRIO
//TODO: FIRST FRAGMENT. See saved cities and their weather resume OR see ONE saved city and have an access to another less important... MEDIUM/HIGH PRIO
//TODO: 2ND FRAGMENT. Use it to see the List<City> if it isnt in the FIRST FRAG or use it to expand information of a city of the observable List of FIRST FRAGMENT... MEDIUM PRIO
//TODO: make design decisions, theme, colors, text size... LOW PRIO
//TODO: Search for a library to replace countryName (fragment_city_list_item.xml) to flags and maybe implement this function LOW PRIO (more accesible app)
//TODO: App should save data of ONE city OR MORE to offline consult LOW PRIO
//TODO: "onTouchEvent(MotionEvent event)" to change color of search bar item on click LOW PRIO
//TODO: Comment existing code to better understanding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        //binding.loaderView.visibility = if(mainViewModel.isVisibleLoader) View.VISIBLE else View.GONE
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        mainViewModel.updateDataIfNeeded()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                val navController = findNavController(R.id.nav_host_fragment_content_main)
                navController.navigate(R.id.action_Navigate_to_PracticeFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}