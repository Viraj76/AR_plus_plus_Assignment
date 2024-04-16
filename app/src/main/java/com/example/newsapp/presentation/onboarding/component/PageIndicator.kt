package com.example.newsapp.presentation.onboarding.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.example.newsapp.presentation.Dimens.IndicatorSize



@Composable
fun PageIndicator(
    modifier : Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = Color.Blue,
    unselectedColor: Color = Color.Gray,
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(pageSize){page ->
            Box(
                modifier = Modifier
                    .size(IndicatorSize)
                    .clip(CircleShape)
                    .background(color = if (selectedPage == page) selectedColor else unselectedColor)
            )
        }

    }

}