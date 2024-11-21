//package com.example.agendadeeventos.model.database
//
//import android.content.Context
//import androidx.room.Room
//
//object DatabaseBuilder {
//    private var INSTANCE: AppDatabase? = null
//
//    fun getDatabase(context: Context): AppDatabase {
//        return INSTANCE ?: synchronized(this) {
//            val instance = Room.databaseBuilder(
//                context.applicationContext,
//                AppDatabase::class.java,
//                "agenda_eventos_db"
//            ).build()
//            INSTANCE = instance
//            instance
//        }
//    }
//}
