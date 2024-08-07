package com.example.royalgymfitness.presentations.home.weekly_plan

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.royalgymfitness.R
import com.example.royalgymfitness.presentations.home.components.TextComponent
import com.example.royalgymfitness.ui.theme.DarkBlue
import com.example.royalgymfitness.ui.theme.MainColor

@Composable
fun MessageCardView(
    day: String, listOfExercises: List<String>, onClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        var isExpanded by remember { mutableStateOf(false) }
        // surfaceColor will be updated gradually from one color to the other
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MainColor,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextComponent(text = day, fontSize = 22.sp, font = R.font.sans_bold)

            Icon(
                painter = painterResource(if (isExpanded) R.drawable.baseline_arrow_drop_up_24 else R.drawable.baseline_arrow_drop_down_24),
                tint = Color.White,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
                    .clickable { isExpanded = !isExpanded }
            )


        }
        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(surfaceColor)
                .height(if (isExpanded) 250.dp else 0.dp)
                .animateContentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(day != "Sunday") {
                for (exerciseName in listOfExercises) {
                    WorkOutListView(exerciseName) {
                        onClick(exerciseName)
                    }
                }
            }else{
                WorkOutListView(exerciseName = "Rest!") {

                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Divider(modifier = Modifier.height(1.dp), color = DarkBlue)

    }

}

@Composable
fun WorkOutListView(exerciseName: String,onClick: () -> Unit) {
    Card(
        onClick = {onClick()},
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            DarkBlue
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        border = BorderStroke(1.dp, MainColor),
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextComponent(
                text = exerciseName,
                fontSize = 12.sp,
                font = R.font.sans_medium
            )
            if(exerciseName != "Rest!")
            TextComponent(
                text = "2x15",
                fontSize = 14.sp,
                font = R.font.sans_bold
            )
        }
    }
}

