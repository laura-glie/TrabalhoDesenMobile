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
import com.example.agendadeeventos.model.entities.Participante

@Composable
fun AddParticipanteScreen(
    eventoId: Int,
    onSave: (Participante) -> Unit,
    navController: NavController
) {
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val camposValidos = nome.isNotBlank() && email.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Adicionar Participante ao Evento",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        TextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome do Participante") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

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
                    val participante = Participante(
                        id = (1..1000).random(),
                        nome = nome,
                        email = email,
                        eventoId = eventoId // Vincula ao evento
                    )
                    onSave(participante) // Adiciona o participante
                    navController.navigate("detalhesEvento/$eventoId") // Retorna à tela do evento
                } else {
                    errorMessage = "Preencha todos os campos antes de salvar!"
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text("Salvar Participante", color = Color.White)
        }
    }
}
