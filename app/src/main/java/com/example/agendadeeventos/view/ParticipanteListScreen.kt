package com.example.agendadeeventos.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.agendadeeventos.model.entities.Participante

@Composable
fun ParticipanteListScreen(
    eventoId: Int,
    participantes: List<Participante>,
    navController: NavController,
    onDelete: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Participantes do Evento",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
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
                        onDelete = onDelete
                    )
                }
            }
        }
    }
}


@Composable
fun ParticipanteItem(
    participante: Participante,
    navController: NavController,
    onDelete: (Int) -> Unit // Callback para excluir o participante
) {
    var showDialog by remember { mutableStateOf(false) } // Estado para exibir o diálogo

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Nome: ${participante.nome}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = "Email: ${participante.email}", fontSize = 16.sp)

            Spacer(modifier = Modifier.height(8.dp))

            // Botões de Editar e Excluir
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        navController.navigate("editarParticipantes/${participante.eventoId}/${participante.id}")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Text("Editar", color = Color.White)
                }

                Button(
                    onClick = { showDialog = true }, // Abre o diálogo de confirmação
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Excluir", color = Color.White)
                }
            }
        }
    }

    // Diálogo de confirmação para excluir o participante
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(text = "Confirmar Exclusão")
            },
            text = {
                Text(text = "Tem certeza que deseja excluir o participante?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        onDelete(participante.id) // Exclui o participante
                        showDialog = false // Fecha o diálogo
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Excluir", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}
