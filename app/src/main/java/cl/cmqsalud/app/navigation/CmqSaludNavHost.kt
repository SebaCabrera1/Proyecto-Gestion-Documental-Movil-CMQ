package cl.cmqsalud.app.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.cmqsalud.app.ui.MisDocumentosScreen
import cl.cmqsalud.app.ui.SubirDocumentoScreen
import cl.cmqsalud.app.viewmodel.DocumentosViewModel

@Composable
fun CmqSaludNavHost() {
    val navController = rememberNavController()
    // ViewModel compartido: lo que se sube en "subir_documento"
    // aparece de inmediato en "mis_documentos".
    val documentosViewModel: DocumentosViewModel = viewModel()

    NavHost(navController = navController, startDestination = "subir_documento") {
        composable("subir_documento") {
            SubirDocumentoScreen(
                viewModel = documentosViewModel,
                onDocumentoGuardado = { navController.navigate("mis_documentos") }
            )
        }
        composable("mis_documentos") {
            MisDocumentosScreen(viewModel = documentosViewModel)
        }
    }
}
