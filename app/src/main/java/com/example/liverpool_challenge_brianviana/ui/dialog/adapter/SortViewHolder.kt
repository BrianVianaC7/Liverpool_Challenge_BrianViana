package com.example.liverpool_challenge_brianviana.ui.dialog.adapter

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.liverpool_challenge_brianviana.R
import com.example.liverpool_challenge_brianviana.databinding.ItemSortsOptionsBinding
import com.example.liverpool_challenge_brianviana.ui.utils.SortOptions

class SortViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemSortsOptionsBinding.bind(view)

    fun render(sort: SortOptions, isSelected: Boolean, onItemClickListener: ((SortOptions) -> Unit)? = null) {
        binding.tvTitle.text = sort.value
        updateColor(isSelected)
        binding.lvSortContainer.setOnClickListener {
            onItemClickListener?.invoke(sort)
        }
    }

    private fun updateColor(isSelected: Boolean) {
        val colorView = binding.vColorItem
        val color = if (isSelected) {
            ContextCompat.getColor(itemView.context, R.color.purple)
        } else {
            Color.WHITE
        }
        colorView.background = ContextCompat.getDrawable(itemView.context, R.drawable.ic_color)?.mutate()
        (colorView.background as? GradientDrawable)?.setColor(color)
    }
}