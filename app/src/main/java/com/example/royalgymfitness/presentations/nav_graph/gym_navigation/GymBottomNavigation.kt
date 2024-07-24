package com.example.royalgymfitness.presentations.nav_graph.gym_navigation

import android.content.res.Configuration
import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.royalgymfitness.R
import com.example.royalgymfitness.ui.theme.DarkBlue
import com.example.royalgymfitness.ui.theme.MainColor
import com.example.royalgymfitness.ui.theme.RoyalGymFitnessTheme

@Composable
fun GymBottomNavigation(
    items: List<BottomNavigationItem>,
    selected: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = DarkBlue,
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selected,
                onClick = { onItemClick(index) },
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            modifier = Modifier
                                .size(25.dp),
                            tint = if (index == selected) colorResource(id = R.color.mainColor) else colorResource(
                                id = R.color.white
                            )
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = item.text,
                            style = MaterialTheme.typography.labelSmall,
                            color = if (index == selected) colorResource(id = R.color.mainColor) else colorResource(
                                id = R.color.white
                            )
                        )
                    }
                }, colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = colorResource(id = R.color.white),
                    unselectedIconColor = colorResource(id = R.color.mainColor),
                    unselectedTextColor = colorResource(id = R.color.mainColor)
                )
            )
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
fun GymBottomNavigationPreview() {
    RoyalGymFitnessTheme {
        GymBottomNavigation(
            items =
            listOf(
                BottomNavigationItem(icon = R.drawable.home, text = "Home"),
                BottomNavigationItem(icon = R.drawable.heart, text = "Favourite"),
                BottomNavigationItem(icon = R.drawable.user, text = "Profile")
            ), selected = 0
        ) {

        }
    }
}