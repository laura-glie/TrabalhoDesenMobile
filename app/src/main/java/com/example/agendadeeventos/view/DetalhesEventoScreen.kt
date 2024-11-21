package com.example.agendadeeventos.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.agendadeeventos.model.entities.Evento
import com.example.agendadeeventos.model.entities.Participante

@Composable
fun DetalhesEventoScreen(
    eventoId: Int,
    navController: NavController,
    evento: Evento?,
    participantes: List<Participante>,
    onDelete: (Int) -> Unit,
    onEdit: () -> Unit
) {
    // Variável de estado para controlar o diálogo de exclusão
    var showDeleteDialog by remember { mutableStateOf(false) }

    // Verifica se o evento é nulo
    if (evento == null) {
        // Se o evento não existir, redireciona para a tela de novo evento
        LaunchedEffect(Unit) {
            navController.navigate("novoEvento") {
                popUpTo("novoEvento") { inclusive = true }
            }
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Informações do evento
        Text(
            text = evento.nome,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Descrição: ${evento.descricao}",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Data: ${evento.data}",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Local: ${evento.local}",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        // Botão para listar participantes
        Button(
            onClick = {
                navController.navigate("participanteList/$eventoId") // Redireciona para a lista de participantes
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text("Listar Participantes", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão para excluir evento
        Button(
            onClick = { showDeleteDialog = true }, // Mostra o diálogo de exclusão
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text("Excluir Evento", color = Color.White)
        }

        // Diálogo de confirmação
        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                title = { Text("Confirmar Exclusão") },
                text = { Text("Tem certeza que deseja excluir este evento?") },
                confirmButton = {
                    Button(
                        onClick = {
                            onDelete(eventoId) // Remove o evento
                            showDeleteDialog = false // Fecha o diálogo
                            navController.navigate("novoEvento") { // Vai para a tela de Novo Evento
                                popUpTo("novoEvento") { inclusive = true }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text("Excluir", color = Color.White)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDeleteDialog = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão para editar evento
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Button(
                onClick = { onEdit() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text("Editar Evento", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de participantes
        Text(
            text = "Participantes",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        if (participantes.isEmpty()) {
            Text(
                text = "Nenhum participante cadastrado.",
                color = Color.Gray,
                fontSize = 16.sp,
                modifier = Modifier.padding(8.dp)
            )
        } else {
            LazyColumn {
                items(participantes) { participante ->
                    ParticipanteItem(
                        participante = participante,
                        navController = navController,
                        onDelete = { id -> onDelete(id) }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão para adicionar participantes
        Button(
            onClick = {
                navController.navigate("novoParticipante/$eventoId")
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
        ) {
            Text("Adicionar Participante", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão para listar eventos
        Button(
            onClick = {
                navController.navigate("eventos")
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
        ) {
            Text("Listar Eventos", color = Color.White)
        }
    }
}
