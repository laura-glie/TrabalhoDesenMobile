//package com.example.agendadeeventos.model.dao
//
//import androidx.room.*
//import com.example.agendadeeventos.model.entities.Participante
//
//@Dao
//interface ParticipanteDao {
//
//    @Insert
//    suspend fun inserir(participante: Participante): Long
//
//    @Update
//    suspend fun atualizar(participante: Participante): Int
//
//    @Delete
//    suspend fun deletar(participante: Participante): Int
//
//    @Query("SELECT * FROM participantes WHERE eventoId = :eventoId")
//    suspend fun listarPorEvento(eventoId: Int): List<Participante>
//
//    @Query("SELECT * FROM participantes WHERE id = :id")
//    suspend fun buscarPorId(id: Int): Participante?
//}
