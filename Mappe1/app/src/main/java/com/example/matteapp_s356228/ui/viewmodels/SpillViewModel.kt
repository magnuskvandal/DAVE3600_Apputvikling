package com.example.matteapp_s356228.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.application
import androidx.lifecycle.viewModelScope
import com.example.matteapp_s356228.repositories.InnstillingerRepository
import com.example.matteapp_s356228.ui.modeller.Oppgave
import com.example.matteapp_s356228.ui.modeller.SpillUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.matteapp_s356228.ui.modeller.Spillstatus
import com.example.matteapp_s356228.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


// ViewModel for spillsiden som håndterer spilllogikk og tilstand
class SpillViewModel(application: Application): AndroidViewModel(application) {
    private val innstillingerRepository = InnstillingerRepository(application) // repository for innstillinger/preferanser
    private val _uiState: MutableStateFlow<SpillUiState> = MutableStateFlow(value = SpillUiState(
        antallOppgaver = innstillingerRepository.hentValgtAntallOppgaver()
    )) // tilstand for spill-UI
    private val _avbryteSpillDialog: MutableStateFlow<Boolean> = MutableStateFlow(value = false) // tilstand for dialog for å avbryte spill
    val uiState: StateFlow<SpillUiState> = _uiState.asStateFlow() // offentlig og lesbar tilstand for spill-UI
    val avbryteSpillDialog: StateFlow<Boolean> = _avbryteSpillDialog.asStateFlow() // offentlig og lesbar tilstand for dialog for å avbryte spill
    private lateinit var alleOppgaver: List<Oppgave> // liste over alle mulige oppgaver
    private var aktivSpilløktOppgaver: List<Oppgave> = emptyList() // liste over oppgaver i den aktive spillrunden
    private var gjeldendeOppgaveIndeks: Int = -1 // indeks for gjeldende oppgave i den aktive spillrunden

    init{
        lastInnRessurser()
        startNyttSpill()
    }
    // funksjon for å laste inn oppgaver og svar fra ressurser
    private fun lastInnRessurser(){
        val oppgaverArray = application.resources.getStringArray(R.array.oppgaver)
        val svarArray = application.resources.getIntArray(R.array.svar)

        alleOppgaver = oppgaverArray.mapIndexed {indeks, tekst -> Oppgave(tekst = tekst, svar = svarArray[indeks])}
    }

    // funksjon for å starte en ny spillrunde
     fun startNyttSpill(){
        gjeldendeOppgaveIndeks = 0
        aktivSpilløktOppgaver = alleOppgaver.shuffled().take(innstillingerRepository.hentValgtAntallOppgaver()) // velger et tilfeldig utvalg av oppgaver basert på valgt antall
        _uiState.update(function = {
            gjeldendeState -> gjeldendeState.copy(
                oppgavetekst = aktivSpilløktOppgaver[gjeldendeOppgaveIndeks].tekst,
                brukersvar = "",
                antallOppgaver = aktivSpilløktOppgaver.size,
                nåværendeOppgave = 1,
                svarSjekket = false,
                rettSvar = false,
                spillstatus = Spillstatus.PÅGÅR,
                score = 0,
                korrektSvar = null
            )
        })
    }

    // funksjon for å legge til et siffer i brukersvaret
    fun velgSiffer(tall: String){
        // sjekk for at spillet ikke er ferdig og svaret ikke er sjekket
        if(_uiState.value.spillstatus == Spillstatus.FERDIG || _uiState.value.svarSjekket){
            return
        }

        _uiState.update(function = {
            gjeldendeState -> gjeldendeState.copy(
                brukersvar = gjeldendeState.brukersvar + tall,
            )
        })
    }

    // funksjon for å tømme brukersvaret
    fun tømBrukersvar(){
        // sjekk for at spillet ikke er ferdig og svaret ikke er sjekket
        if(_uiState.value.spillstatus == Spillstatus.FERDIG || _uiState.value.svarSjekket){
            return
        }
        _uiState.update(function = {
            gjeldendeState -> gjeldendeState.copy(
                brukersvar = "",
            )
        })
    }

    // funksjon for å slette siste siffer i brukersvaret
    fun slettSisteSiffer(){
        // sjekk for at spillet ikke er ferdig, svaret ikke er sjekket og at brukersvaret ikke er tomt
        if(_uiState.value.spillstatus == Spillstatus.FERDIG || _uiState.value.svarSjekket || _uiState.value.brukersvar.isEmpty()){
            return
        }
        _uiState.update(function = {
            gjeldendeState -> gjeldendeState.copy(
                brukersvar = gjeldendeState.brukersvar.dropLast(1),
            )
        })
    }

    // funksjon for å sjekke brukersvaret mot korrekt svar
    fun sjekkSvar(){
        if(_uiState.value.brukersvar.isBlank() || _uiState.value.svarSjekket){
            return
        }

        val korrektSvar = aktivSpilløktOppgaver[gjeldendeOppgaveIndeks].svar // korrekt svar for gjeldende oppgave
        val brukersvarInt = _uiState.value.brukersvar.toIntOrNull() // konverterer brukersvar til Int
        val erRett = korrektSvar == brukersvarInt


        _uiState.update(function = {
                gjeldendeState -> gjeldendeState.copy(
            rettSvar = erRett,
            svarSjekket = true,
            score = if (erRett) gjeldendeState.score + 1 else gjeldendeState.score, // øker score hvis svaret er rett
            korrektSvar = if(!erRett) korrektSvar.toString() else null // viser korrekt svar hvis brukersvar var feil
            )
        })
    }

    // funksjon for å gå til neste oppgave om ikke siste oppgave
    fun nesteOppgave(){
        // sjekk for om spillet er ferdig
        if (_uiState.value.spillstatus == Spillstatus.FERDIG) {
            return
        }
        //  neste oppgave hvis ikke siste oppgave
        if(gjeldendeOppgaveIndeks < aktivSpilløktOppgaver.size - 1){
            gjeldendeOppgaveIndeks++
            _uiState.update(function = {
                gjeldendeState -> gjeldendeState.copy(
                    oppgavetekst = aktivSpilløktOppgaver[gjeldendeOppgaveIndeks].tekst,
                    brukersvar = "",
                    nåværendeOppgave = gjeldendeState.nåværendeOppgave + 1,
                    rettSvar = false,
                    svarSjekket = false,
                    spillstatus = Spillstatus.PÅGÅR,
                    korrektSvar = null
                )
            })
        }
    }

    // funksjon for å avslutte spillet og sette spillstatus til FERDIG
    fun avsluttSpill() {
        _uiState.update(function = {
            gjeldendeState -> gjeldendeState.copy(
                spillstatus = Spillstatus.FERDIG
            )
        })
    }

    // funksjon for å vise dialog for å avbryte spill
    fun visAvbryteSpillDialog(){
        _avbryteSpillDialog.value = true
    }

    // funksjon for å lukke dialog for å avbryte spill
    fun lukkAvbryteSpillDialog(){
        _avbryteSpillDialog.value = false
    }
}