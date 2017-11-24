package com.framgia.forder.utils.common

/**
 * Created by Tuanlvt on 24/11/2017.
 */
object StringUtils {

    fun isBlank(input: String?): Boolean {
        return input == null || input.isEmpty()
    }

    fun isNotBlank(input: String): Boolean {
        return !isBlank(input)
    }

    fun convertStringToNumber(input: String?): Int {
        try {
            return Integer.parseInt(input)
        } catch (e: NumberFormatException) {
            return Integer.MIN_VALUE
        }
    }
}
