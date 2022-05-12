package com.ilyapiskunov.simplecovidtracker

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("ConstantLocale")
val DATE_FORMAT = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

fun countryCodeToEmojiFlag(countryCode: String) : String {
    return countryCode
        .uppercase(Locale.US)
        .map { char ->
            Character.codePointAt("$char", 0) - 0x41 + 0x1F1E6
        }
        .map { codePoint ->
            Character.toChars(codePoint)
        }
        .joinToString(separator = "") { charArray ->
            String(charArray)
        }
}