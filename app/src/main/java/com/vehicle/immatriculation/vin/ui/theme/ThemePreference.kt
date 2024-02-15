package com.vehicle.immatriculation.vin.ui.theme

import android.content.Context

enum class ThemePreference { LIGHT, DARK, SYSTEM }


fun saveThemePreference(context: Context, themePreference: ThemePreference) {
    val sharedPreferences = context.getSharedPreferences("ThemePreferences", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString("themePref", themePreference.name)
        apply()
    }
}


fun loadThemePreference(context: Context): ThemePreference {
    val sharedPreferences = context.getSharedPreferences("ThemePreferences", Context.MODE_PRIVATE)
    val themePrefString = sharedPreferences.getString("themePref", ThemePreference.SYSTEM.name)
    return ThemePreference.valueOf(themePrefString!!)
}
