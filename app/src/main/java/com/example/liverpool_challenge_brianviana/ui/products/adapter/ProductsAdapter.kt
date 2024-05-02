package com.example.liverpool_challenge_brianviana.ui.products.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.liverpool_challenge_brianviana.R
import com.example.liverpool_challenge_brianviana.domain.model.RecordModel

class ProductsAdapter(
    private var productList: List<RecordModel> = emptyList()
) : RecyclerView.Adapter<ProductsViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<RecordModel>) {
        productList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_products, parent, false)
        )
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.render(productList[position])
    }
}