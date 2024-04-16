package com.example.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.newsapp.R

data class OnBoardingPages(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val onBoardingPagesList = listOf(
    OnBoardingPages(
        "Stay Informed, Anywhere, Anytime!",
        "Welcome to our news app, your ultimate source for staying updated with the latest news and trends. Whether you're on the go or relaxing at home, our app provides real-time news updates tailored to your interests. Get ready to explore a world of information at your fingertips!",
        R.drawable.onboarding1
    ),
    OnBoardingPages(
        "Your Personalized News Hub Awaits!",
        "Step into a world of curated news content designed just for you. Our app offers a personalized experience, delivering news articles, videos, and updates based on your preferences. From politics to technology, sports to entertainment, our app ensures you're always in the know about what matters most to you."
        ,R.drawable.onboarding2
    ),
    OnBoardingPages(
        "Discover, Engage, and Stay Connected!",
        "Welcome aboard! Our news app isn't just about reading headlines; it's about engaging with the stories that shape our world. Dive into in-depth articles, explore multimedia content, and interact with a community of fellow news enthusiasts. With our app, you'll not only stay informed but also become an active participant in the news cycle.",
        R.drawable.onboarding3
    )
)