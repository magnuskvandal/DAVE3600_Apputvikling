package com.example.matteapp_s356228.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.matteapp_s356228.repositories.InnstillingerRepository
import com.example.matteapp_s356228.ui.modeller.SpillUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.matteapp_s356228.ui.modeller.Spillstatus

class SpillViewModel(application: Application): AndroidViewModel(application) {
    private val innstillingerRepository = InnstillingerRepository(application)

    private val _uiState: MutableStateFlow<SpillUiState> = MutableStateFlow(value = SpillUiState(
        antallOppgaver = innstillingerRepository.hentValgtAntallOppgaver()
    ))
    val uiState: StateFlow<SpillUiState> = _uiState.asStateFlow()

    // Her vil funksjoner for spill-logikk komme etter hvert:
    // fun startNyttSpill() { ... }
    // fun velgSiffer(siffer: String) { ... }
    // fun slettSisteSiffer() { ... }
    // fun toemBrukerSvar() { ... }
    // fun sjekkSvar() { ... }
    // fun nesteOppgave() { ... }
    // fun avsluttSpill() { ... }
}