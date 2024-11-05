package com.weissoft.lab13zarate // Asegúrate de que este sea el nombre de tu paquete real

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CombinedAnimationScreen()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CombinedAnimationScreen() {
    // Estado para el cambio de tamaño y color
    var isExpanded by remember { mutableStateOf(false) }

    // Estado para el botón con AnimatedVisibility
    var isButtonVisible by remember { mutableStateOf(true) }

    // Estado para el modo claro/oscuro
    var isDarkMode by remember { mutableStateOf(false) }

    // Animación de tamaño del elemento
    val boxSize by animateDpAsState(
        targetValue = if (isExpanded) 150.dp else 100.dp,
        animationSpec = tween(durationMillis = 500)
    )

    // Animación de color del elemento
    val boxColor by animateColorAsState(
        targetValue = if (isExpanded) Color.Blue else Color.Green,
        animationSpec = tween(durationMillis = 500)
    )

    // Fondo de pantalla basado en el modo claro/oscuro
    val backgroundColor by animateColorAsState(
        targetValue = if (isDarkMode) Color.DarkGray else Color.White,
        animationSpec = tween(durationMillis = 1000)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Elemento que cambia de tamaño y color
            Box(
                modifier = Modifier
                    .size(boxSize)
                    .background(boxColor)
                    .padding(16.dp)
            ) {
                Text(
                    text = if (isExpanded) "Grande" else "Pequeño",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Botón que alterna el tamaño y color del elemento
            Button(onClick = { isExpanded = !isExpanded }) {
                Text(text = "Cambiar Tamaño y Color")
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Botón que controla la visibilidad del otro botón
            AnimatedVisibility(
                visible = isButtonVisible,
                enter = fadeIn(animationSpec = tween(500)),
                exit = fadeOut(animationSpec = tween(500))
            ) {
                Button(onClick = { isButtonVisible = false }) {
                    Text(text = "Desaparecer")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Transición de contenido para modo claro/oscuro
            AnimatedContent(
                targetState = isDarkMode,
                transitionSpec = {
                    fadeIn(animationSpec = tween(500)) with
                            fadeOut(animationSpec = tween(500))
                }
            ) { darkMode ->
                Text(
                    text = if (darkMode) "Modo Oscuro" else "Modo Claro",
                    color = if (darkMode) Color.White else Color.Black
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Botón para alternar entre modo claro y oscuro
            Button(onClick = { isDarkMode = !isDarkMode }) {
                Text(text = "Cambiar Modo")
            }
        }
    }
}
