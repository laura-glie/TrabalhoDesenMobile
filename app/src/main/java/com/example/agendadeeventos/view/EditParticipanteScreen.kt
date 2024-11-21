package com.example.agendadeeventos.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agendadeeventos.model.entities.Participante

@Composable
fun EditParticipanteScreen(
    participante: Participante,
    onSave: (Participante) -> Unit
) {
    var nome by remember { mutableStateOf(participante.nome) }
    var email by remember { mutableStateOf(participante.email) }
    var errorMessage by remember { mutableStateOf("") } // Mensagem de erro

    // Função auxiliar para validar os campos
    val camposValidos = nome.isNotBlank() && email.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Editar Participante",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        TextField(
            value = nome,
            onValueChange = {
                nome = it
                if (errorMessage.isNotBlank()) errorMessage = "" // Limpar mensagem de erro
            },
            label = { Text("Nome do Participante") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = email,
            onValueChange = {
                email = it
                if (errorMessage.isNotBlank()) errorMessage = "" // Limpar mensagem de erro
            },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Exibir mensagem de erro, se houver
        if (errorMessage.isNotBlank()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Button(
            onClick = {
                if (camposValidos) {
                    // Criar o participante atualizado e salvar as alterações
                    val participanteEditado = participante.copy(nome = nome, email = email)
                    onSave(participanteEditado)
                } else {
                    // Atualizar mensagem de erro se os campos não forem válidos
                    errorMessage = "Preencha todos os campos antes de salvar!"
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text("Salvar Alterações", color = Color.White)
        }
    }
}
