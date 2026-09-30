package cl.cmqsalud.app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cl.cmqsalud.app.model.Documento
import java.time.LocalDate

/**
 * Estado de la pantalla "Subir documento" + lista de "Mis documentos".
 * Se comparte entre ambas pantallas para que el dato ingresado se
 * refleje de inmediato en el listado (Meta 6 — interacción entre pantallas).
 */
data class DocumentosUiState(
    val tipo: String = "",
    val comentario: String = "",
    val tipoError: String? = null,
    val comentarioError: String? = null,
    val documentos: List<Documento> = emptyList()
)

class DocumentosViewModel : ViewModel() {

    var uiState by mutableStateOf(DocumentosUiState())
        private set

    fun onTipoChange(nuevoTipo: String) {
        uiState = uiState.copy(tipo = nuevoTipo, tipoError = null)
    }

    fun onComentarioChange(nuevoComentario: String) {
        uiState = uiState.copy(comentario = nuevoComentario, comentarioError = null)
    }

    /**
     * RF02 — Valida y guarda el documento.
     * Validación 1: tipo obligatorio.
     * Validación 2: comentario no puede superar 200 caracteres.
     */
    fun guardarDocumento(onExito: () -> Unit) {
        val tipoValido = uiState.tipo.isNotBlank()
        val comentarioValido = uiState.comentario.length <= 200

        uiState = uiState.copy(
            tipoError = if (!tipoValido) "Debes seleccionar un tipo de documento" else null,
            comentarioError = if (!comentarioValido) "El comentario no puede superar los 200 caracteres" else null
        )

        if (tipoValido && comentarioValido) {
            val nuevoDocumento = Documento(
                id = (uiState.documentos.maxOfOrNull { it.id } ?: 0) + 1,
                tipo = uiState.tipo,
                comentario = uiState.comentario,
                fecha = LocalDate.now().toString(),
                estado = "Pendiente"
            )
            uiState = uiState.copy(
                documentos = uiState.documentos + nuevoDocumento,
                tipo = "",
                comentario = ""
            )
            onExito()
        }
    }
}
