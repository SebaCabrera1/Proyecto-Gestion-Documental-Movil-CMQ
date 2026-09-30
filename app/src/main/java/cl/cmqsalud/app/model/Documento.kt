package cl.cmqsalud.app.model

/**
 * RF02 — Modelo de datos para un documento subido por un funcionario.
 * Corresponde a la entidad DOCUMENTO del modelo relacional preliminar.
 */
data class Documento(
    val id: Int,
    val tipo: String,
    val comentario: String,
    val fecha: String,
    val estado: String
)
