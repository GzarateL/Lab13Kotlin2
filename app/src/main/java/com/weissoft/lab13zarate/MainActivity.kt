package com.weissoft.lab13zarate // Asegúrate de que este sea el nombre de tu paquete real

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Definición del enum fuera de la función composable
enum class ContentState { CARGANDO, CONTENIDO, ERROR }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContentTransitionExample()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ContentTransitionExample() {
    // Variable de estado para controlar el contenido actual
    var currentState by remember { mutableStateOf(com.weissoft.lab13zarate.ContentState.CARGANDO) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Botón que alterna entre los estados
        Button(
            onClick = {
                currentState = when (currentState) {
                    com.weissoft.lab13zarate.ContentState.CARGANDO -> com.weissoft.lab13zarate.ContentState.CONTENIDO
                    com.weissoft.lab13zarate.ContentState.CONTENIDO -> com.weissoft.lab13zarate.ContentState.ERROR
                    com.weissoft.lab13zarate.ContentState.ERROR -> com.weissoft.lab13zarate.ContentState.CARGANDO
                }
            },
            modifier = Modifier.align(Alignment.TopCenter).padding(16.dp)
        ) {
            Text(text = "Cambiar Estado")
        }

        // AnimatedContent para hacer la transición entre los diferentes estados
        AnimatedContent(
            targetState = currentState,
            transitionSpec = {
                fadeIn(animationSpec = androidx.compose.animation.core.tween(500)) with
                        fadeOut(animationSpec = androidx.compose.animation.core.tween(500))
            }
        ) { state ->
            // Contenido que cambia según el estado
            when (state) {
                com.weissoft.lab13zarate.ContentState.CARGANDO -> Text(text = "Cargando...", Modifier.padding(16.dp))
                com.weissoft.lab13zarate.ContentState.CONTENIDO -> Text(text = "¡Contenido Cargado!", Modifier.padding(16.dp))
                com.weissoft.lab13zarate.ContentState.ERROR -> Text(text = "Error al cargar el contenido", Modifier.padding(16.dp))
            }
        }
    }
}
