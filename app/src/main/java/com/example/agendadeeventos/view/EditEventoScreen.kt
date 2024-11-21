package com.example.agendadeeventos.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.agendadeeventos.model.entities.Evento

@Composable
fun EditEventoScreen(
    navController: NavController,
    eventoId: Int,
    evento: Evento,
    onEventoEdited: (Evento) -> Unit
) {
    var nome by remember { mutableStateOf(evento.nome) }
    var descricao by remember { mutableStateOf(evento.descricao) }
    var data by remember { mutableStateOf(evento.data) }
    var local by remember { mutableStateOf(evento.local) }
    var errorMessage by remember { mutableStateOf("") }

    // Função auxiliar para validar os campos
    val camposValidos = nome.isNotBlank() && descricao.isNotBlank() && data.isNotBlank() && local.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Editar Evento",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome do Evento") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = descricao,
            onValueChange = { descricao = it },
            label = { Text("Descrição do Evento") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = data,
            onValueChange = { data = it },
            label = { Text("Data (DD/MM/YYYY)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = local,
            onValueChange = { local = it },
            label = { Text("Local") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        if (errorMessage.isNotBlank()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Column {
            Button(
                onClick = {
                    if (camposValidos) {
                        val eventoEditado = evento.copy(
                            nome = nome,
                            descricao = descricao,
                            data = data,
                            local = local
                        )
                        onEventoEdited(eventoEditado)
                        navController.navigate("eventos") { popUpTo("eventos") { inclusive = true } }
                    } else {
                        errorMessage = "Preencha todos os campos antes de salvar!"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text("Salvar Alterações", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))


        }
    }
}
