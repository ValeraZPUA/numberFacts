package com.example.numberfacts.ui.xmls.numberInfoFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.numberfacts.databinding.FragmentNumberInfoBinding
import com.example.numberfacts.ui.xmls.MainActivity

class NumberInfoFragment : Fragment() {

    private var _binding: FragmentNumberInfoBinding? = null
    private val binding get() = _binding!!
    private val args: NumberInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNumberInfoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDate()
        (activity as MainActivity).isShowBackArrow(true)
    }

    private fun setDate() {
        binding.tvNumber.text = args.number.toString()
        binding.tvNumberFact.text = args.fact
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
