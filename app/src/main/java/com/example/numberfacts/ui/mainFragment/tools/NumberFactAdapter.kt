package com.example.numberfacts.ui.mainFragment.tools

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.numberfacts.data.models.NumberItem
import com.example.numberfacts.databinding.RvNumberItemBinding
import com.example.numberfacts.utils.DiffSubmission

class NumberFactAdapter(
    private val onFactClickListener: OnFactClickListener
) : ListAdapter<NumberItem, NumberFactAdapter.NumberItemViewHolder>(DiffSubmission<NumberItem>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberItemViewHolder {
        val binding = RvNumberItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NumberItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NumberItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NumberItemViewHolder(private val binding: RvNumberItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onFactClickListener.onFactClicked(getItem(bindingAdapterPosition))
            }
        }

        fun bind(numberItem: NumberItem) {
            binding.tvNumber.text = numberItem.number.toString()
            binding.tvFact.text = numberItem.fact
        }

    }

}