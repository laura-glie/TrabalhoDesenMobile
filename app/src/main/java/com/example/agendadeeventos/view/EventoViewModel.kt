package com.example.agendadeeventos

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.agendadeeventos.model.entities.Evento
import com.example.agendadeeventos.model.entities.Participante

class EventoViewModel(context: Context) : ViewModel() {
    private val sharedPreferences = context.getSharedPreferences("eventosPrefs", Context.MODE_PRIVATE)

    private val _eventos = mutableStateOf<List<Evento>>(emptyList())
    val eventos: State<List<Evento>> = _eventos

    private val _participantes = mutableStateOf<List<Participante>>(emptyList())
    val participantes: State<List<Participante>> = _participantes

    private var nextEventoId = sharedPreferences.getInt("nextEventoId", 1)
    private var nextParticipanteId = sharedPreferences.getInt("nextParticipanteId", 1)

    fun addEvento(evento: Evento) {
        // Validar os campos do evento
        if (evento.nome.isBlank() || evento.descricao.isBlank() || evento.data.isBlank() || evento.local.isBlank()) {
            throw IllegalArgumentException("Todos os campos do evento devem ser preenchidos.")
        }

        val eventoComId = evento.copy(id = nextEventoId)
        _eventos.value = _eventos.value + eventoComId
        nextEventoId++
        saveNextEventoId()
    }

    fun addParticipante(participante: Participante) {
        // Validar os campos do participante
        if (participante.nome.isBlank() || participante.email.isBlank()) {
            throw IllegalArgumentException("Todos os campos do participante devem ser preenchidos.")
        }

        val participanteComId = participante.copy(id = nextParticipanteId)
        _participantes.value = _participantes.value + participanteComId
        nextParticipanteId++
        saveNextParticipanteId()
    }

    fun removeEvento(id: Int) {
        _eventos.value = _eventos.value.filter { it.id != id }
        _participantes.value = _participantes.value.filter { it.eventoId != id }
    }

    fun removeParticipante(id: Int) {
        _participantes.value = _participantes.value.filter { it.id != id }
    }

    private fun saveNextEventoId() {
        sharedPreferences.edit().putInt("nextEventoId", nextEventoId).apply()
    }

    private fun saveNextParticipanteId() {
        sharedPreferences.edit().putInt("nextParticipanteId", nextParticipanteId).apply()
    }
}
