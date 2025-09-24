package com.example.matteapp_s356228.ui.modeller

data class SpillUiState(
    val oppgavetekst: String = "",
    val brukersvar: String ="",
    val antallOppgaver: Int = 0,
    val nåværendeOppgave: Int = 0,
    val svarSjekket : Boolean = false,
    val rettSvar: Boolean = false,
    val spillstatus: Spillstatus = Spillstatus.IKKE_STARTET,
    val score: Int = 0,
    val korrektSvar: String? = null
    )
