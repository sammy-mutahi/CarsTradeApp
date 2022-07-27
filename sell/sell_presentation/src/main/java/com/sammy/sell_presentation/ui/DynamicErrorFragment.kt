package com.sammy.sell_presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sammy.sell_presentation.data.StartErrorInput
import com.sammy.sell_presentation.databinding.FragmentDynamicErrorBinding

class DynamicErrorFragment : Fragment() {

    private val binding: FragmentDynamicErrorBinding by lazy {
        FragmentDynamicErrorBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val startError = arguments?.get("error") as StartErrorInput
        binding.input = startError
        initListeners()
    }

    private fun initListeners() {
        binding.buttonTryAgain.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}