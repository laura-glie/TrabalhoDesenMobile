package com.example.agendadeeventos.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.agendadeeventos.model.entities.Evento

@Composable
fun EventoListScreen(eventos: List<Evento>, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (eventos.isEmpty()) {
            // Exibir mensagem quando a lista estiver vazia
            Box(
                modifier = Modifier
                    .weight(1f) // Garante que a mensagem preencha o espaço restante
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Nenhum evento disponível.\nClique em 'Novo Evento' para começar.",
                    color = Color.Gray,
                    fontSize = 18.sp,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        } else {
            // Lista de eventos
            LazyColumn(
                modifier = Modifier.weight(1f) // Garante que a lista ocupe o espaço restante
            ) {
                items(eventos) { evento ->
                    EventoItem(evento = evento, navController = navController)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botões para Novo Evento e Listar Eventos
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween // Coloca os botões nas extremidades
        ) {
            Button(
                onClick = { navController.navigate("novoEvento") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text(
                    text = "Novo Evento",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }

            Button(
                onClick = { navController.navigate("eventos") },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text(
                    text = "Listar Eventos",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun EventoItem(evento: Evento, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = { navController.navigate("detalhesEvento/${evento.id}") }),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Nome: ${evento.nome}",
                fontSize = 20.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
            Text(text = "Descrição: ${evento.descricao}")
            Text(text = "Data: ${evento.data}")
            Text(text = "Local: ${evento.local}")
        }
    }
}
