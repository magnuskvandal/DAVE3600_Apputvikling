package com.example.matteapp_s356228.repositories

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel

// Repository for å håndtere lagring og henting av innstillinger/preferanser i SharedPreferences
class InnstillingerRepository(application: Application): AndroidViewModel(application) {
    private val sharedPref: SharedPreferences = application.getSharedPreferences(
        "matteapp_pref",
        Context.MODE_PRIVATE
    )

    // Funksjon for å lagre valgt antall oppgaver i SharedPreferences
    fun lagreValgtAntallOppgaver(antall: Int){
        val editor = sharedPref.edit()
        editor.putInt("antall_oppgaver", antall).apply()
    }

    // Funksjon for å hente valgt antall oppgaver fra SharedPreferences
    fun hentValgtAntallOppgaver() : Int{
        return sharedPref.getInt("antall_oppgaver", 5)
    }
}