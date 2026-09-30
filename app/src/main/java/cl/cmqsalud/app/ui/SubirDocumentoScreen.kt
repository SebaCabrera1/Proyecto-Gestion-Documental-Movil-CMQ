package cl.cmqsalud.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.cmqsalud.app.viewmodel.DocumentosViewModel
import androidx.compose.ui.tooling.preview.Preview

private val tiposDocumento = listOf("Certificado", "Contrato", "Capacitación")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubirDocumentoScreen(
    viewModel: DocumentosViewModel,
    onDocumentoGuardado: () -> Unit
) {
    val uiState = viewModel.uiState
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text("Subir documento", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))

        // Selector de tipo de documento (dropdown)
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = uiState.tipo,
                onValueChange = {},
                readOnly = true,
                label = { Text("Tipo de documento") },
                isError = uiState.tipoError != null,
                supportingText = { uiState.tipoError?.let { Text(it) } },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                tiposDocumento.forEach { tipo ->
                    DropdownMenuItem(
                        text = { Text(tipo) },
                        onClick = {
                            viewModel.onTipoChange(tipo)
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = uiState.comentario,
            onValueChange = viewModel::onComentarioChange,
            label = { Text("Comentario (opcional)") },
            isError = uiState.comentarioError != null,
            supportingText = { uiState.comentarioError?.let { Text(it) } },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = { viewModel.guardarDocumento(onExito = onDocumentoGuardado) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar documento")
        }
    }
}
@Preview(showBackground = true) @Composable fun SubirDocumentoScreenPreview() { SubirDocumentoScreen( viewModel = DocumentosViewModel(), onDocumentoGuardado = {} ) }