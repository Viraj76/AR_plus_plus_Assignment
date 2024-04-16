package com.example.newsapp.presentation.onboarding.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsapp.presentation.Dimens.MediumSpacerHeight
import com.example.newsapp.presentation.Dimens.MediumTextSize
import com.example.newsapp.presentation.Dimens.SmallPadding
import com.example.newsapp.presentation.Dimens.SmallSpacerHeight
import com.example.newsapp.presentation.Dimens.SmallTextSize
import com.example.newsapp.R
import com.example.newsapp.presentation.onboarding.OnBoardingPages
import com.example.newsapp.presentation.onboarding.onBoardingPagesList
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun OnBoardingPage(
    onBoardingPages: OnBoardingPages,
    modifier :Modifier =Modifier
) {
    Column(
        modifier = modifier
    ) {
        Image(painter = painterResource(id = onBoardingPages.image),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            contentScale = ContentScale.Inside ,
            contentDescription = "image")
        Spacer(modifier = Modifier.height(MediumSpacerHeight))
        Text(
            modifier = Modifier.padding(horizontal = SmallPadding),
            text = onBoardingPages.title,
            color = colorResource(id = R.color.black),
            fontSize = MediumTextSize,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(SmallSpacerHeight))
        Text(
            modifier = Modifier.padding(horizontal = SmallPadding),
            text = onBoardingPages.description,
            color = Color.Gray,
            fontSize = SmallTextSize,
            fontWeight = FontWeight.Normal
        )
    }
}


@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
fun OnPreview() {
    NewsAppTheme {
        OnBoardingPage(onBoardingPagesList[0])
    }
}