package com.example.newsapp.presentation.onboarding


/*
this class has only events that would be sent by the ui to the view model
 */
sealed class OnBoardingEvent {

    object SaveAppEntry : OnBoardingEvent()
}