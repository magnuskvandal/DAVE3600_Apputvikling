package com.example.matteapp_s356228.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.application
import com.example.matteapp_s356228.repositories.InnstillingerRepository
import com.example.matteapp_s356228.ui.modeller.Oppgave
import com.example.matteapp_s356228.ui.modeller.SpillUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.matteapp_s356228.ui.modeller.Spillstatus
import com.example.matteapp_s356228.R
import kotlinx.coroutines.flow.update

class SpillViewModel(application: Application): AndroidViewModel(application) {
    private val innstillingerRepository = InnstillingerRepository(application)
    private val _uiState: MutableStateFlow<SpillUiState> = MutableStateFlow(value = SpillUiState(
        antallOppgaver = innstillingerRepository.hentValgtAntallOppgaver()
    ))
    val uiState: StateFlow<SpillUiState> = _uiState.asStateFlow()
    private lateinit var alleOppgaver: List<Oppgave>
    private var aktivSpilløktOppgaver: List<Oppgave> = emptyList()
    private var gjeldendeOppgaveIndeks: Int = -1

    init{
        lastInnRessurser()
        startNyttSpill()
    }

    private fun lastInnRessurser(){
        val oppgaverArray = application.resources.getStringArray(R.array.oppgaver)
        val svarArray = application.resources.getIntArray(R.array.svar)

        alleOppgaver = oppgaverArray.mapIndexed {indeks, tekst -> Oppgave(tekst = tekst, svar = svarArray[indeks])}
    }

     fun startNyttSpill(){
        gjeldendeOppgaveIndeks = 0
        aktivSpilløktOppgaver = alleOppgaver.shuffled().take(innstillingerRepository.hentValgtAntallOppgaver())
        _uiState.update(function = {
            gjeldendeState -> gjeldendeState.copy(
                oppgavetekst = aktivSpilløktOppgaver[gjeldendeOppgaveIndeks].tekst,
                brukersvar = "",
                antallOppgaver = aktivSpilløktOppgaver.size,
                nåværendeOppgave = 1,
                svarSjekket = false,
                rettSvar = false,
                spillstatus = Spillstatus.PÅGÅR,
                score = 0
            )
        })
    }

    fun velgSiffer(tall: String){
        if(_uiState.value.spillstatus == Spillstatus.FERDIG || _uiState.value.rettSvar){
            return
        }

        _uiState.update(function = {
            gjeldendeState -> gjeldendeState.copy(
                brukersvar = gjeldendeState.brukersvar + tall,
                rettSvar = false,
                svarSjekket = false
            )
        })
    }

    fun tømBrukersvar(){
        if(_uiState.value.spillstatus == Spillstatus.FERDIG || _uiState.value.rettSvar){
            return
        }
        _uiState.update(function = {
            gjeldendeState -> gjeldendeState.copy(
                brukersvar = "",
                rettSvar = false,
                svarSjekket = false
            )
        })
    }

    fun slettSisteSiffer(){
        if(_uiState.value.spillstatus == Spillstatus.FERDIG || _uiState.value.rettSvar || _uiState.value.brukersvar.isEmpty()){
            return
        }
        _uiState.update(function = {
            gjeldendeState -> gjeldendeState.copy(
                brukersvar = gjeldendeState.brukersvar.dropLast(1),
                rettSvar = false,
                svarSjekket = false
            )
        })
    }

    fun sjekkSvar(){
        if(_uiState.value.brukersvar.isBlank()){
            return
        }

        val korrektSvar = aktivSpilløktOppgaver[gjeldendeOppgaveIndeks].svar
        val brukersvarInt = _uiState.value.brukersvar.toIntOrNull()
        val sisteOppgave = gjeldendeOppgaveIndeks == aktivSpilløktOppgaver.size - 1

        if(korrektSvar == brukersvarInt){
            _uiState.update(function = {
                gjeldendeState -> gjeldendeState.copy(
                    rettSvar = true,
                    svarSjekket = true,
                    score = gjeldendeState.score + 1,
                    spillstatus = if(sisteOppgave) Spillstatus.FERDIG else gjeldendeState.spillstatus
                )
            } )
        }else{
            _uiState.update(function = {
                gjeldendeState -> gjeldendeState.copy(
                    rettSvar = false,
                    svarSjekket = true,
            )})
        }
    }

    fun nesteOppgave(){
        if (_uiState.value.spillstatus == Spillstatus.FERDIG) {
            return
        }
        if(gjeldendeOppgaveIndeks < aktivSpilløktOppgaver.size - 1){
            gjeldendeOppgaveIndeks++
            _uiState.update(function = {
                gjeldendeState -> gjeldendeState.copy(
                    oppgavetekst = aktivSpilløktOppgaver[gjeldendeOppgaveIndeks].tekst,
                    brukersvar = "",
                    nåværendeOppgave = gjeldendeState.nåværendeOppgave + 1,
                    rettSvar = false,
                    svarSjekket = false,
                    spillstatus = Spillstatus.PÅGÅR
                )
            })
        }else{
            _uiState.update(function = {
                gjeldendeState -> gjeldendeState.copy(
                    spillstatus = Spillstatus.FERDIG
                )
            })
        }
    }

    // fun avsluttSpill() { ... }
}