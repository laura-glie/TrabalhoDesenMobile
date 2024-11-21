package com.example.agendadeeventos

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.agendadeeventos.model.entities.Evento
import com.example.agendadeeventos.model.entities.Participante
import com.example.agendadeeventos.view.*

class MainActivity : ComponentActivity() {
    private val eventos = mutableStateListOf<Evento>()
    private val participantes = mutableStateListOf<Participante>()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val EVENTO_ID = "eventoId"
            val PARTICIPANTE_ID = "participanteId"
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "telaInicial") {
                // Tela Inicial
                composable("telaInicial") { TelaInicialScreen(navController) }

                // Lista de Eventos
                composable("eventos") {
                    EventoListScreen(
                        eventos = eventos,
                        navController = navController
                    )
                }

                // Criar Novo Evento
                composable("novoEvento") {
                    AddEventoScreen(navController) { evento ->
                        eventos.add(evento)
                        navController.navigate("eventos") {
                            popUpTo("eventos") { inclusive = true }
                        }
                    }
                }

                // Detalhes de um Evento
                composable(
                    route = "detalhesEvento/{$EVENTO_ID}",
                    arguments = listOf(navArgument(EVENTO_ID) { type = NavType.IntType })
                ) { backStackEntry ->
                    val eventoId = backStackEntry.arguments?.getInt(EVENTO_ID) ?: -1
                    val evento = eventos.find { it.id == eventoId }

                    if (evento != null) {
                        val eventoParticipantes = participantes.filter { it.eventoId == eventoId }
                        DetalhesEventoScreen(
                            eventoId = eventoId,
                            navController = navController,
                            evento = evento,
                            participantes = eventoParticipantes,
                            onDelete = { id ->
                                eventos.removeIf { it.id == id }
                                participantes.removeIf { it.eventoId == id }
                                navController.navigate("novoEvento") {
                                    popUpTo("novoEvento") { inclusive = true }
                                }
                            },
                            onEdit = { navController.navigate("editarEventos/$eventoId") }
                        )
                    } else {
                        navController.navigate("errorScreen")
                    }
                }

                // Listar Participantes de um Evento
                composable(
                    route = "participanteList/{$EVENTO_ID}",
                    arguments = listOf(navArgument(EVENTO_ID) { type = NavType.IntType })
                ) { backStackEntry ->
                    val eventoId = backStackEntry.arguments?.getInt(EVENTO_ID) ?: -1
                    val eventoParticipantes = participantes.filter { it.eventoId == eventoId }

                    ParticipanteListScreen(
                        eventoId = eventoId,
                        participantes = eventoParticipantes,
                        navController = navController,
                        onDelete = { participanteId ->
                            participantes.removeIf { it.id == participanteId }
                        }
                    )
                }

                // Criar Novo Participante
                composable(
                    route = "novoParticipante/{$EVENTO_ID}",
                    arguments = listOf(navArgument(EVENTO_ID) { type = NavType.IntType })
                ) { backStackEntry ->
                    val eventoId = backStackEntry.arguments?.getInt(EVENTO_ID) ?: -1

                    if (eventoId != -1) {
                        AddParticipanteScreen(
                            eventoId = eventoId,
                            onSave = { participante ->
                                participantes.add(participante)
                            },
                            navController = navController
                        )
                    } else {
                        navController.navigate("errorScreen")
                    }
                }

                // Editar Evento
                composable(
                    route = "editarEventos/{$EVENTO_ID}",
                    arguments = listOf(navArgument(EVENTO_ID) { type = NavType.IntType })
                ) { backStackEntry ->
                    val eventoId = backStackEntry.arguments?.getInt(EVENTO_ID) ?: -1
                    val evento = eventos.find { it.id == eventoId }

                    if (evento != null) {
                        EditEventoScreen(
                            navController = navController,
                            eventoId = eventoId,
                            evento = evento,
                            onEventoEdited = { eventoEditado ->
                                val index = eventos.indexOfFirst { it.id == eventoId }
                                if (index != -1) {
                                    eventos[index] = eventoEditado
                                }
                                navController.navigate("eventos") {
                                    popUpTo("eventos") { inclusive = true }
                                }
                            }
                        )
                    } else {
                        navController.navigate("errorScreen")
                    }
                }

                // Editar Participante
                composable(
                    route = "editarParticipantes/{$EVENTO_ID}/{$PARTICIPANTE_ID}",
                    arguments = listOf(
                        navArgument(EVENTO_ID) { type = NavType.IntType },
                        navArgument(PARTICIPANTE_ID) { type = NavType.IntType }
                    )
                ) { backStackEntry ->
                    val eventoId = backStackEntry.arguments?.getInt(EVENTO_ID) ?: -1
                    val participanteId = backStackEntry.arguments?.getInt(PARTICIPANTE_ID) ?: -1
                    val participante =
                        participantes.find { it.id == participanteId && it.eventoId == eventoId }

                    if (participante != null) {
                        EditParticipanteScreen(participante) { participanteEditado ->
                            val index = participantes.indexOfFirst { it.id == participanteId }
                            if (index != -1) {
                                participantes[index] = participanteEditado
                            }
                            navController.navigate("detalhesEvento/$eventoId")
                        }
                    } else {
                        navController.navigate("errorScreen")
                    }
                }

                // Tela de Erro
                composable("errorScreen") {
                    ErrorScreen(navController = navController)
                }
            }
        }
    }
}
