package com.example.royalgymfitness.presentations.home.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.example.royalgymfitness.R

@Composable
fun TextComponent(
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    color: Color = Color.White,
    font: Int = R.font.sans_regular,
    fontSize: TextUnit,
    fontStyle: FontStyle = FontStyle.Normal
) {
    Text(
        text = text, style = TextStyle(
            textAlign = textAlign,
            color = color,
            fontFamily = FontFamily(
                Font(font)
            ),
            fontSize = fontSize,
            fontStyle = fontStyle
        )
    )
}