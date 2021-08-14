package com.example.scavengerar.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver

val grey = Color(0xFF747575)

val lightBlue = Color(0xFF7FF1F8)
val blue = Color(0xFF00D2E0)
val darkBlue = Color(0xFF2596BE)
val teal = Color(0xFF007E86)

/**
 * Return the fully opaque color that results from compositing [onSurface] atop [surface] with the
 * given [alpha]. Useful for situations where semi-transparent colors are undesirable.
 */
@Composable
fun Colors.compositedOnSurface(alpha: Float): Color {
    return onSurface.copy(alpha = alpha).compositeOver(surface)
}
