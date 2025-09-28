package com.example.matteapp_s356228.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.matteapp_s356228.R
import com.example.matteapp_s356228.repositories.InnstillingerRepository
import com.example.matteapp_s356228.ui.modeller.AntallOppgaverValg

// ViewModel for preferansesiden hvor brukeren kan sette antall oppgaver per spillrunde
class PreferanserViewModel(application: Application): AndroidViewModel(application) {
    private val innstillingerRepository = InnstillingerRepository(application) // repository for innstillinger/preferanser
    private val _valgtAntallState: MutableState<Int> = mutableStateOf(innstillingerRepository.hentValgtAntallOppgaver()) // tilstand for valgt antall oppgaver
    val valgtAntallState: State<Int> = _valgtAntallState // offentlig og lesbar tilstand for valgt antall oppgaver

    // Mulige valg for antall oppgaver med tilhørende ressurs-IDer for visningstekst
    val muligeAntall : List<AntallOppgaverValg> = listOf(
        AntallOppgaverValg(5, R.string.antallOppgaver5),
        AntallOppgaverValg(10, R.string.antallOppgaver10),
        AntallOppgaverValg(15, R.string.antallOppgaver15)
    )

    // Funksjon for å sette og lagre valgt antall oppgaver
    fun settAntall(antall: Int){
        innstillingerRepository.lagreValgtAntallOppgaver(antall)
        _valgtAntallState.value = antall
    }
}