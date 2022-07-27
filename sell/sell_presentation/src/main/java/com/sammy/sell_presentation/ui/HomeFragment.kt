package com.sammy.sell_presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.sammy.sell_presentation.R
import com.sammy.sell_presentation.adapters.CarSearchAdapter
import com.sammy.sell_presentation.adapters.MostBoughtCarsAdapter
import com.sammy.sell_presentation.adapters.PopularCarLoadStateAdapter
import com.sammy.sell_presentation.databinding.FragmentHomeBinding
import com.sammy.sell_presentation.hideSoftKeyboard
import com.sammy.sell_presentation.remove
import com.sammy.sell_presentation.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BottomNavigationFragment() {

    private val homeBinding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private lateinit var popularCarsAdapter: MostBoughtCarsAdapter
    private lateinit var carSearchAdapter: CarSearchAdapter

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBottomNavigation()
        setupPopularCarsRecycler()
        setupSearchRecycler()
        initSearchWidget()

    }

    private fun setupSearchRecycler() {
        carSearchAdapter = CarSearchAdapter()
        homeBinding.searchResultsRecyclerView.apply {
            adapter = carSearchAdapter
                .withLoadStateHeaderAndFooter(
                    header = PopularCarLoadStateAdapter { carSearchAdapter.retry() },
                    footer = PopularCarLoadStateAdapter { carSearchAdapter.retry() }
                )
            hasFixedSize()
        }
        initCarSearchAdapter()
        submitSearch("")
    }

    private fun setupPopularCarsRecycler() {
        popularCarsAdapter = MostBoughtCarsAdapter()
        homeBinding.popularCarsRecyclerView.apply {
            adapter = popularCarsAdapter
                .withLoadStateHeaderAndFooter(
                    header = PopularCarLoadStateAdapter { popularCarsAdapter.retry() },
                    footer = PopularCarLoadStateAdapter { popularCarsAdapter.retry() }
                )
            hasFixedSize()
        }
        initPopularCarAdapter()
        loadPopularCars()
    }

    private fun initCarSearchAdapter() {
        lifecycleScope.launchWhenStarted {
            carSearchAdapter.loadStateFlow.collectLatest { state ->
                val isRefreshing = state.refresh is LoadState.Loading
                val isError = state.refresh is LoadState.Error
                homeBinding.searchResultsRecyclerView.isVisible = !isRefreshing
                homeBinding.carSearchContainer.isVisible = isRefreshing
                homeBinding.noPopularCarsFoundTextView.isVisible =
                    state.refresh.endOfPaginationReached
                homeBinding.retryGroup.isVisible = isError

                homeBinding.retryGroup.setOnClickListener {
                    //setupPopularCarsRecycler()
                }
                //toast on any error that came from paging source
                val errorState = state.source.append as? LoadState.Error
                    ?: state.source.prepend as? LoadState.Error
                    ?: state.append as? LoadState.Error
                errorState?.let {
                    Toast.makeText(
                        requireContext(),
                        "Failed to load: ${it.error}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun initPopularCarAdapter() {
        lifecycleScope.launchWhenStarted {
            popularCarsAdapter.loadStateFlow.collectLatest { state ->

                val isRefreshing = state.refresh is LoadState.Loading
                val isError = state.refresh is LoadState.Error
                homeBinding.popularCarsRecyclerView.isVisible = !isRefreshing
                homeBinding.PopularCarsAnimation.isVisible = isRefreshing
                homeBinding.noPopularCarsFoundTextView.isVisible =
                    state.refresh.endOfPaginationReached
                homeBinding.retryGroup.isVisible = isError

                homeBinding.retryGroup.setOnClickListener {
                    //setupPopularCarsRecycler()
                }
                //toast on any error that came from paging source
                val errorState = state.source.append as? LoadState.Error
                    ?: state.source.prepend as? LoadState.Error
                    ?: state.append as? LoadState.Error
                errorState?.let {
                    Toast.makeText(
                        requireContext(),
                        "Failed to load: ${it.error}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun loadPopularCars() {
        lifecycleScope.launchWhenStarted {
            viewModel.getPopularCars()
                .collectLatest {
                    popularCarsAdapter.submitData(it)
                }
        }
    }

    private fun initSearchWidget() {
        homeBinding.carSearchWidget.searchBarEditText.text = null
        homeBinding.carSearchWidget.searchBarTextInputLayout.editText?.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                homeBinding.carSearchWidget.apply {
                    searchWidgetClose.show()
                    searchWidgetMagnifier.remove()

                }
            }
        }
        homeBinding.carSearchWidget.searchBarTextInputLayout.editText?.doOnTextChanged { text, _, _, _ ->
            val searchQuery = text.toString()
            submitSearch(searchQuery)
            loadPopularCars()
        }

        homeBinding.carSearchWidget.searchWidgetClose.setOnClickListener {
            hideSoftKeyboard()
            homeBinding.carSearchWidget.searchBarTextInputLayout.clearFocus()
            homeBinding.carSearchWidget.searchBarEditText.text = null
            homeBinding.carSearchWidget.searchWidgetClose.remove()
            homeBinding.carSearchWidget.searchWidgetMagnifier.show()
        }
    }

    private fun submitSearch(searchQuery: String) {
        val query = searchQuery.ifEmpty { "" }
        lifecycleScope.launch {
            viewModel.searchCar(query = query)
                .collectLatest { pagingData ->
                    carSearchAdapter.submitData(pagingData)
                }
        }
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