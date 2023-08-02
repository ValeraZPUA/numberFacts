package com.example.numberfacts.ui.mainFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.numberfacts.R
import com.example.numberfacts.data.models.NumberItem
import com.example.numberfacts.databinding.FragmentMainBinding
import com.example.numberfacts.ui.MainActivity
import com.example.numberfacts.ui.mainFragment.tools.ItemDecorator
import com.example.numberfacts.ui.mainFragment.tools.NumberFactAdapter
import com.example.numberfacts.ui.mainFragment.tools.OnFactClickListener
import com.example.numberfacts.utils.launchAndRepeatOn
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), OnFactClickListener {

    private val viewModel: MainFragmentViewModel by viewModels()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val numberFactHistoryAdapter: NumberFactAdapter by lazy { NumberFactAdapter(this) }

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
        getHistory()
        (activity as MainActivity).isShowBackArrow(false)
    }

    private fun setListeners() {
        binding.btnGetFact.setOnClickListener {
            viewModel.getNumberFact(binding.etNumber.text.toString())
        }

        binding.btnGetRandomFact.setOnClickListener {
            viewModel.getRandomNumberFact()
        }
    }

    private fun setObservers() {
        viewModel.commonError.observe(viewLifecycleOwner) {
            showToast(getString(R.string.something_went_wrong))
        }

        viewLifecycleOwner.launchAndRepeatOn(Lifecycle.State.STARTED) {
            viewModel.numberFact.collect {
                if (it is MainFragmentViewModel.NumberFactState.SuccessState) {
                    binding.etNumber.text.clear()
                    findNavController().navigate(MainFragmentDirections.actionMainFragmentToNumberInfoFragment(it.numberFact.number, it.numberFact.fact))
                }
            }
        }

        viewModel.noNumberEnteredError.observe(viewLifecycleOwner) {
            showToast(getString(R.string.enter_number_warning))
        }

        viewModel.numberFactsHistory.observe(viewLifecycleOwner) {
            setHistoryRecycler(it)
        }
    }

    private fun setHistoryRecycler(numberItemList: List<NumberItem>) {
        binding.rvHistory.adapter = numberFactHistoryAdapter
        binding.rvHistory.addItemDecoration(ItemDecorator())
        numberFactHistoryAdapter.submitList(numberItemList)
    }

    private fun getHistory() {
        viewModel.getHistory()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onFactClicked(numberItem: NumberItem) {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToNumberInfoFragment(numberItem.number, numberItem.fact))
    }

}
