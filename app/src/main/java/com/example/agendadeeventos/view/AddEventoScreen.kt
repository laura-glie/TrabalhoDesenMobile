package com.example.agendadeeventos.view

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.agendadeeventos.model.entities.Evento

@Composable
fun AddEventoScreen(
    navController: NavController,
    onSave: (Evento) -> Unit
) {
    var nome by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var data by remember { mutableStateOf("") }
    var local by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Novo Evento",
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome do Evento") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = descricao,
                onValueChange = { descricao = it },
                label = { Text("Descrição do Evento") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = data,
                onValueChange = { data = it },
                label = { Text("Data do Evento") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = local,
                onValueChange = { local = it },
                label = { Text("Local do Evento") },
                modifier = Modifier.fillMaxWidth()
            )

            if (errorMessage.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }

        Column { // Agrupa os botões na parte inferior
            Button(
                onClick = {
                    if (nome.isNotBlank() && descricao.isNotBlank() && data.isNotBlank() && local.isNotBlank()) {
                        val novoEvento = Evento(
                            id = (1..1000).random(), // Gera um ID fictício
                            nome = nome,
                            descricao = descricao,
                            data = data,
                            local = local
                        )
                        onSave(novoEvento)
                        Toast.makeText(context, "Evento salvo com sucesso!", Toast.LENGTH_SHORT).show()
                        navController.navigate("detalhesEvento/${novoEvento.id}")
                    } else {
                        errorMessage = "Preencha todos os campos antes de salvar!"
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Salvar Evento", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp)) // Adiciona espaço entre os botões

            Button(
                onClick = {
                    navController.navigate("eventos") // Navega para a lista de eventos
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text("Listar Eventos", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp)) // Adiciona espaço entre os botões

            Button(
                onClick = {
                    navController.navigate("telaInicial") // Navega para a tela inicial
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text("Voltar para o Início", color = Color.White)
            }
        }
    }
}
