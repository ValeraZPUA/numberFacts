package com.example.numberfacts.ui.mainFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.numberfacts.R
import com.example.numberfacts.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainFragmentViewModel by viewModels()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        setObservers()
    }

    private fun setListeners() {
        binding.btnGetFact.setOnClickListener {
            viewModel.getNumberInfo(binding.etNumber.text.toString())
        }

        binding.btnGetRandomFact.setOnClickListener {
            viewModel.getRandomNumberInfo()
        }
    }

    private fun setObservers() {
        viewModel.commonError.observe(viewLifecycleOwner) {
            showToast(getString(R.string.something_went_wrong))
        }

        viewModel.numberFact.observe(viewLifecycleOwner) {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToNumberInfoFragment(it.number, it.fact))
        }

        viewModel.noNumberEnteredError.observe(viewLifecycleOwner) {
            showToast(getString(R.string.enter_number_warning))
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
