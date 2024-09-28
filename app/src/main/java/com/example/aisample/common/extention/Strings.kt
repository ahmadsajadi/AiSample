package com.example.aisample.common.extention

fun String.containsPersianCharacters(): Boolean {
    // Regex pattern for Persian characters
    val persianCharPattern = Regex("[\\u0600-\\u06FF\\u0750-\\u077F\\uFB50-\\uFDFF\\uFE70-\\uFEFF]")

    // Regex pattern for detecting numbers
    val numberPattern = Regex("^\\d+$")

    // If the string is only numbers, return false
    if (numberPattern.matches(this)) return false

    // Otherwise, check for Persian characters
    return persianCharPattern.containsMatchIn(this)
}
