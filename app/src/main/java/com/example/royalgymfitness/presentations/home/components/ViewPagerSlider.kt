package com.example.royalgymfitness.presentations.home.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.royalgymfitness.R
import com.example.royalgymfitness.ui.theme.DarkBlue
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
@Preview
fun PagerSlider() {
    val pagerState = rememberPagerState(initialPage = 0)

    val sliderList = listOf(
        R.drawable.pic_2,
        R.drawable.pic_1_2,
        R.drawable.pic_2_2,
        R.drawable.pic_3_3,
        R.drawable.pic_3_4,
    )
    LaunchedEffect(key1 = Unit) {
        while (true) {
            yield()
            delay(2000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600)
            )
        }
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

            HorizontalPager(
                count = sliderList.size,
                state = pagerState,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()

            ) { page ->
                Card(
                    modifier = Modifier
                        .graphicsLayer {
                            val pagerOffset = calculateCurrentOffsetForPage(page).absoluteValue
                            lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pagerOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }
                            alpha = lerp(
                                start = 0.50f,
                                stop = 1f,
                                fraction = 1f - pagerOffset.coerceIn(0f, 1f)
                            )
                        },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Image(
                        painter = painterResource(id = sliderList[page]),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
            activeColor = Color.White,
            inactiveColor = DarkBlue
        )
    }
}