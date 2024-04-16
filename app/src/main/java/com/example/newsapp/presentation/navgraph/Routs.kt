package com.example.newsapp.presentation.navgraph

import okhttp3.Route

sealed class Routs(val route: String){
    object OnBoardingScreen : Routs(route = "on_boarding_screen")
    object HomeScreen : Routs(route = "home_screen")
    object BookmarkScreen : Routs(route = "bookmark_screen")
    object DetailsScreen : Routs(route = "details_screen")
    object SearchScreen : Routs(route = "search_screen")

    object AppStartNavigation : Routs(route =  "app_starts_navigation")
    object NewsNavigation : Routs(route =  "news_navigation")
    object NewsNavigatorScreen : Routs(route = "news_navigation_screen")
}
