package com.pokemon.core.design_system.util

import android.content.Context
import android.content.res.Configuration
import com.pokemon.core.design_system.R
import java.util.Locale

enum class Language(val type: String, val stringId: Int) {
    Korean(type = "ko", stringId = R.string.korean),
    Japanese(type = "ja", stringId = R.string.japanese),
    Chinese(type = "zh", stringId = R.string.chinese),
    English(type = "en", stringId = R.string.english),
    French(type = "fr", stringId = R.string.french),
    Italian(type = "it", stringId = R.string.italian),
    Spanish(type = "es", stringId = R.string.spanish),
    German(type = "de", stringId = R.string.german),
}

fun Language.changeLanguage(context: Context) {
    val locale = Locale(type)
    Locale.setDefault(locale)
    val config = Configuration()
    config.setLocale(locale)
    context.resources.updateConfiguration(config, context.resources.displayMetrics)
}