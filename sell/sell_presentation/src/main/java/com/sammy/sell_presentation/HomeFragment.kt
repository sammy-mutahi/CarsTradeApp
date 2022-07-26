package com.sammy.sell_presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.sammy.sell_presentation.databinding.FragmentHomeBinding
import com.sammy.sell_presentation.ui.BottomNavigationFragment
import com.sammy.sell_presentation.ui.DashboardViewModel
import com.sammy.sell_presentation.ui.MenuNavigationItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BottomNavigationFragment() {

    private val homeBinding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        homeBinding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    openScreen(MenuNavigationItem.HOME)
                }
                else -> {
                    Toast.makeText(requireContext(), "Feature Coming Soon !!", Toast.LENGTH_LONG)
                        .show()
                }
            }
            true
        }
    }

}