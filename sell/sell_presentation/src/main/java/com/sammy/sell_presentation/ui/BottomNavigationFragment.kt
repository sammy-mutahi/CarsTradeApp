package com.sammy.sell_presentation.ui

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BottomNavigationFragment : Fragment() {
    protected fun supportFragmentmanager() = activity?.supportFragmentManager
    protected fun openScreen(item: MenuNavigationItem) {
        findNavController().navigate(item.menuId)
    }
}