package com.example.liverpool_challenge_brianviana.ui.utils

import android.graphics.Paint
import android.widget.TextView

object ObjectUtils {

    fun Double.formatAsCurrency(): String {
        return String.format("$ %.2f", this)
    }

    fun TextView.applyStrikeThrough() {
        paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }


}