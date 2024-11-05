package com.weissoft.lab13zarate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
            ColorChangeExample()
        }
    }
}

@Composable
fun ColorChangeExample() {
    // Estado que controla si el color es azul o verde
    var isBlue by remember { mutableStateOf(true) }

    // Cambia el color de fondo usando animateColorAsState
    val backgroundColor by animateColorAsState(
        targetValue = if (isBlue) Color.Blue else Color.Green,
        animationSpec = tween(durationMillis = 1000)  // Cambia a spring() para experimentar con otra animación
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        // Botón que alterna el color de fondo
        Button(
            onClick = { isBlue = !isBlue },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Cambiar Color")
        }
    }
}