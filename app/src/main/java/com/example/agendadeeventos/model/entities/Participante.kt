package com.example.agendadeeventos.model.entities

data class Participante(
    val id: Int,
    val nome: String,
    val email: String,
    val eventoId: Int
)
