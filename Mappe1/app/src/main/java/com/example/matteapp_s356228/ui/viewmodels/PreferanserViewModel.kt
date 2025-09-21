package com.example.matteapp_s356228.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.matteapp_s356228.R
import com.example.matteapp_s356228.repositories.InnstillingerRepository
import com.example.matteapp_s356228.ui.modeller.AntallOppgaverValg

class PreferanserViewModel(application: Application): AndroidViewModel(application) {
    private val innstillingerRepository = InnstillingerRepository(application)
    private val _valgtAntallState: MutableState<Int> = mutableStateOf(innstillingerRepository.hentValgtAntallOppgaver())
    val valgtAntallState: State<Int> = _valgtAntallState
    val muligeAntall : List<AntallOppgaverValg> = listOf(
        AntallOppgaverValg(5, R.string.antallOppgaver5),
        AntallOppgaverValg(10, R.string.antallOppgaver10),
        AntallOppgaverValg(15, R.string.antallOppgaver15)
    )

    fun settAntall(antall: Int){
        innstillingerRepository.lagreValgtAntallOppgaver(antall)
        _valgtAntallState.value = antall
    }
}