package com.example.newsapp.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.newsapp.presentation.Dimens
import com.example.newsapp.presentation.Dimens.PagerIndicatorWidth
import com.example.newsapp.presentation.common.NewsButton
import com.example.newsapp.presentation.common.NewsTextButton
import com.example.newsapp.presentation.onboarding.component.OnBoardingPage
import com.example.newsapp.presentation.onboarding.component.PageIndicator
import kotlinx.coroutines.launch

//
//@Preview
//@Composable
//fun Prev() {
//    NewsAppTheme {
//        OnBoardingScreen()
//    }
//}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    event: (OnBoardingEvent) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        val pagerState = rememberPagerState(initialPage = 0) {
            onBoardingPagesList.size
        }

        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get Stared")
                    else -> listOf("", "")
                }
            }
        }

        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(onBoardingPages = onBoardingPagesList[index])
        }


        Spacer(modifier = Modifier.weight(1f))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.MediumPadding)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(
                modifier = Modifier.width(PagerIndicatorWidth),
                pageSize = onBoardingPagesList.size,
                selectedPage = pagerState.currentPage
            )

            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                val scope = rememberCoroutineScope()
                if(buttonState.value[0].isNotEmpty()){
                    NewsTextButton(text = "Back") {
                        scope.launch {pagerState.animateScrollToPage(pagerState.currentPage - 1)  }
                    }
                }

                NewsButton(text = buttonState.value[1]) {
                    scope.launch {
                        if(pagerState.currentPage == onBoardingPagesList.size - 1){
                            event(OnBoardingEvent.SaveAppEntry)
                        }
                        else{
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                }

            }

        }
        
        Spacer(modifier = Modifier.weight(0.4f))
    }
}


