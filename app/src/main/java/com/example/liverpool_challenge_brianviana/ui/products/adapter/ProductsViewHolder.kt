package com.example.liverpool_challenge_brianviana.ui.products.adapter

import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.liverpool_challenge_brianviana.R
import com.example.liverpool_challenge_brianviana.databinding.ItemProductsBinding
import com.example.liverpool_challenge_brianviana.domain.model.RecordModel
import com.example.liverpool_challenge_brianviana.ui.utils.ObjectUtils.applyStrikeThrough
import com.example.liverpool_challenge_brianviana.ui.utils.ObjectUtils.formatAsCurrency
import com.squareup.picasso.Picasso

class ProductsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemProductsBinding.bind(view)

    fun render(product: RecordModel) {
        binding.apply {
            tvTitle.text = product.productDisplayName
            if (product.listPrice == product.promoPrice) {
                tvOriginalPrice.visibility = View.GONE
            }
            tvOriginalPrice.applyStrikeThrough()
            tvOriginalPrice.text = product.listPrice?.formatAsCurrency()
            tvPromoPrice.text = product.promoPrice?.formatAsCurrency()
            Picasso.get().load(product.smImage).into(ivProduct)
            initColorItems(product)
        }
    }

    private fun initColorItems(product: RecordModel) {
        binding.apply {
            lvColorContainer.removeAllViews()
            product.variantsColor.forEach {
                it.colorHex?.let { colorHex ->
                    val colorView = View(itemView.context)
                    colorView.layoutParams = LinearLayout.LayoutParams(
                        52,
                        50
                    ).apply { marginStart = 5 }
                    colorView.background = ContextCompat.getDrawable(itemView.context, R.drawable.ic_color)?.mutate()
                    (colorView.background as GradientDrawable).setColor(Color.parseColor(it.colorHex))
                    lvColorContainer.addView(colorView)
                }
            }
        }

    }

}