package com.sammy.gebeyacartrademobileapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.sammy.gebeyacartrademobileapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/*
* We are using one activity approach for the whole project
* This activity acts as a container for our navigation
* Creates the host fragment and sets the primary navigation
* */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment = NavHostFragment.create(
            com.sammy.sell_presentation.R.navigation.core_navigation_graph
        )

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host, navHostFragment)
            .setPrimaryNavigationFragment(navHostFragment)
            .commit()
    }
}