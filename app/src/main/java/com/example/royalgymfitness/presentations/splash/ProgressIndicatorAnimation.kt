package com.example.royalgymfitness.presentations.splash

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp

private const val DefaultAnimationDuration = 600
private const val DefaultAnimationDelay = 400
private const val DefaultStartDelay = 0
private const val DefaultLineCount = 5

private val DefaultMaxLineHeight = 32.dp
private val DefaultMinLineHeight = 16.dp
private val DefaultLineWidth = 3.dp
private val DefaultLineSpacing = 4.dp
private val DefaultLineCornerRadius = 3.dp

@Composable
fun ProgressIndicatorAnimation(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    animationDuration: Int = DefaultAnimationDuration,
    animationDelay: Int = DefaultAnimationDelay,
    startDelay: Int = DefaultStartDelay,
    lineCount: Int = DefaultLineCount,
    maxLineHeight: Dp = DefaultMaxLineHeight,
    minLineHeight: Dp = DefaultMinLineHeight,
    lineWidth: Dp = DefaultLineWidth,
    lineSpacing: Dp = DefaultLineSpacing,
    lineCornerRadius: Dp = DefaultLineCornerRadius
) {
    val transition = rememberInfiniteTransition(label = "")

    val duration = startDelay + animationDuration + animationDelay

    //Fractional height
    val height = arrayListOf<Float>().apply {
        for (i in 0 until lineCount) {
            val delay = startDelay + animationDelay / (lineCount - 1) * i
            val height by transition.animateFloat(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = duration
                        0f at delay with LinearEasing
                        1f at delay + (animationDuration / 2) with LinearEasing
                        0f at delay + animationDuration
                        0f at duration
                    }
                ), label = ""
            )
            add(height)
        }
    }

    val width = (lineWidth + lineSpacing) * lineCount - lineSpacing

    ProgressIndicator(modifier, width, maxLineHeight) {
        drawIndeterminateLineScaleIndicator(
            maxHeight = maxLineHeight.toPx(),
            height = height.map { lerp(minLineHeight, maxLineHeight, it).toPx() },
            width = lineWidth.toPx(),
            cornerRadius = lineCornerRadius.toPx(),
            spacing = lineSpacing.toPx(),
            color = color
        )
    }
}

private fun DrawScope.drawIndeterminateLineScaleIndicator(
    maxHeight: Float,
    height: List<Float>,
    width: Float,
    cornerRadius: Float,
    spacing: Float,
    color: Color
) {

    for (i in height.indices) {
        val x = i * (width + spacing)
        val y = (maxHeight - height[i]) / 2
        drawRoundRect(
            color = color,
            topLeft = Offset(x, y),
            size = Size(width, height[i]),
            cornerRadius = CornerRadius(cornerRadius)
        )
    }
}
@Composable
internal fun ProgressIndicator(
    modifier: Modifier,
    width: Dp,
    height: Dp,
    onDraw: DrawScope.() -> Unit
) {
    Canvas(
        modifier = modifier
            .progressSemantics()
            .size(width, height)
            .focusable(),
        onDraw = onDraw
    )
}