package com.example.agendadeeventos.model.entities

data class Evento(
    val id: Int,
    val nome: String,
    val descricao: String,
    val data: String,
    val local: String
)
