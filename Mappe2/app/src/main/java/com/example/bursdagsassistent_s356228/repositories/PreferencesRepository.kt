package com.example.bursdagsassistent_s356228.repositories

import android.app.Application
import android.content.Context
import android.content.SharedPreferences


class PreferencesRepository(application: Application) {
    private val sharedPref: SharedPreferences = application.getSharedPreferences(
        "birthday_assistant_pref",
        Context.MODE_PRIVATE
    )

    fun setSmsService(enabled: Boolean){
        val editor = sharedPref.edit()
        editor.putBoolean("sms_service", enabled)
        editor.apply()
    }

    fun getSmsService(): Boolean{
        return sharedPref.getBoolean("sms_service", false)
    }

    fun setDefaultSmsMessage(message: String){
        val editor = sharedPref.edit()
        editor.putString("default_sms_message", message)
        editor.apply()
    }

    fun getDefaultSmsMessage(): String{
        return sharedPref.getString("default_sms_message", "Gratulerer med dagen!") ?: ""
    }
}