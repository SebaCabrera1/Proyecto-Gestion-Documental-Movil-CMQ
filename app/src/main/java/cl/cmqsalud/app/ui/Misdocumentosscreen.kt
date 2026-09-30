

package cl.cmqsalud.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.cmqsalud.app.viewmodel.DocumentosViewModel
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MisDocumentosScreen(viewModel: DocumentosViewModel) {
    val uiState = viewModel.uiState

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text("Mis documentos", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))

        if (uiState.documentos.isEmpty()) {
            Text("Aún no has cargado documentos.")
        } else {
            LazyColumn {
                items(uiState.documentos) { doc ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Column(Modifier.padding(12.dp)) {
                            Text(doc.tipo, style = MaterialTheme.typography.bodyLarge)
                            if (doc.comentario.isNotBlank()) {
                                Text(doc.comentario, style = MaterialTheme.typography.bodySmall)
                            }
                            Text(
                                "Cargado ${doc.fecha} · ${doc.estado}",
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun MisDocumentosScreenPreview() {
    val viewModel = DocumentosViewModel()
    viewModel.onTipoChange("Certificado")
    viewModel.guardarDocumento {}
    MisDocumentosScreen(viewModel = viewModel)
}