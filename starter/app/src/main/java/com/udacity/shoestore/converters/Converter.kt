package com.udacity.shoestore.converters

import androidx.databinding.InverseMethod

object Converter {

    @JvmStatic
    fun stringToDouble(value: String): Double {
        return value.toDouble()
    }

    @InverseMethod("stringToDouble")
    @JvmStatic
    fun doubleToString(value: Double): String {
        return value.toString()
    }

}