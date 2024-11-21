package com.example.agendadeeventos.view

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun ConfirmDeleteDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss, // Fechar o diálogo ao clicar fora
        title = {
            Text(text = "Confirmar Exclusão")
        },
        text = {
            Text(
                text = "Tem certeza de que deseja excluir este item? Essa ação não poderá ser desfeita.",
                fontSize = 16.sp, // Ajuste o tamanho da fonte diretamente
                lineHeight = 20.sp // Define o espaçamento entre as linhas
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm() // Ação de exclusão
                    onDismiss() // Fecha o diálogo
                }
            ) {
                Text("Excluir", color = androidx.compose.ui.graphics.Color.Red)
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text("Cancelar")
            }
        }
    )
}
