package com.weissoft.lab13zarate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SizeAndPositionAnimationExample()
        }
    }
}

@Composable
fun SizeAndPositionAnimationExample() {
    // Estado para controlar si el cuadro está en su posición y tamaño original o en una posición y tamaño alterados
    var isExpanded by remember { mutableStateOf(false) }

    // Animación para el tamaño del cuadro
    val boxSize: Dp by animateDpAsState(targetValue = if (isExpanded) 150.dp else 100.dp)

    // Animación para la posición del cuadro
    val offsetX: Dp by animateDpAsState(targetValue = if (isExpanded) 100.dp else 0.dp)
    val offsetY: Dp by animateDpAsState(targetValue = if (isExpanded) 100.dp else 0.dp)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Botón que alterna el estado de expansión del cuadro
        Button(
            onClick = { isExpanded = !isExpanded },
            modifier = Modifier.align(Alignment.TopCenter).padding(16.dp)
        ) {
            Text(text = if (isExpanded) "Restablecer Cuadro" else "Mover y Agrandar Cuadro")
        }

        // Cuadro animado que cambia de tamaño y posición
        Box(
            modifier = Modifier
                .offset(x = offsetX, y = offsetY)  // Cambia la posición del cuadro
                .size(boxSize)  // Cambia el tamaño del cuadro
                .background(Color.Red)
        )
    }
}