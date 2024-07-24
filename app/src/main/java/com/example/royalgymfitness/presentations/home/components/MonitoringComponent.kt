package com.example.royalgymfitness.presentations.home.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.royalgymfitness.R

@Composable
fun MonitoringComponent(
    @DrawableRes icon: Int,
    monitoringName: String,
    monitoringValue: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "icon",
            tint = Color.Unspecified,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        TextComponent(text = monitoringValue, fontSize = 14.sp, font = R.font.sans_bold)
        Spacer(modifier = Modifier.height(4.dp))
        TextComponent(text = monitoringName, fontSize = 12.sp, font = R.font.sans_medium)
    }
}