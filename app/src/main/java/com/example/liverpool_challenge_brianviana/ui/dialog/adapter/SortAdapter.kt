package com.example.liverpool_challenge_brianviana.ui.dialog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.liverpool_challenge_brianviana.R
import com.example.liverpool_challenge_brianviana.ui.utils.SortOptions

class SortAdapter(
    private var sortList: List<SortOptions> = emptyList(),
    private val onItemClickListener: ((SortOptions) -> Unit)
) : RecyclerView.Adapter<SortViewHolder>() {

    private var selectedIndex: Int = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SortViewHolder {
        return SortViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_sorts_options, parent, false)
        )
    }

    override fun getItemCount(): Int = sortList.size

    override fun onBindViewHolder(holder: SortViewHolder, position: Int) {
        val sortOption = sortList[position]
        holder.render(sortOption, position == selectedIndex) { clickedSortOption ->
            val clickedIndex = sortList.indexOf(clickedSortOption)
            if (selectedIndex != clickedIndex) {
                val previousIndex = selectedIndex
                selectedIndex = clickedIndex
                notifyItemChanged(previousIndex)
                notifyItemChanged(clickedIndex)
                onItemClickListener.invoke(clickedSortOption)
            }
        }
    }
}