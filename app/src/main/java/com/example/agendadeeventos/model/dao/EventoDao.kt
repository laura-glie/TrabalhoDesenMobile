//package com.example.agendadeeventos.model.dao
//
//import androidx.room.*
//import com.example.agendadeeventos.model.entities.Evento
//
//@Dao
//interface EventoDao {
//
//    @Insert
//    suspend fun inserir(evento: Evento): Long
//
//    @Update
//    suspend fun atualizar(evento: Evento): Int
//
//    @Delete
//    suspend fun deletar(evento: Evento): Int
//
//    @Query("SELECT * FROM eventos ORDER BY data ASC")
//    suspend fun listarTodos(): List<Evento>
//
//    @Query("SELECT * FROM eventos WHERE id = :id")
//    suspend fun buscarPorId(id: Int): Evento?
//}
