package com.example.newsapp.presentation.navgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.presentation.bookmark.BookmarkScreen
import com.example.newsapp.presentation.bookmark.BookmarkViewModel
import com.example.newsapp.presentation.home.HomeScreen
import com.example.newsapp.presentation.home.HomeViewModel
import com.example.newsapp.presentation.home.HomeViewModel_Factory
import com.example.newsapp.presentation.newsnavigator.NewsNavigator
import com.example.newsapp.presentation.onboarding.OnBoardingScreen
import com.example.newsapp.presentation.onboarding.OnBoardingViewModel
import com.example.newsapp.presentation.search.SearchScreen
import com.example.newsapp.presentation.search.SearchViewModel

@Composable
fun NavGraph(
    startDestination : String
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination ){

        navigation(
            route = Routs.AppStartNavigation.route,
            startDestination = Routs.OnBoardingScreen.route
        ){
            composable(
                route = Routs.OnBoardingScreen.route
            ){
                val viewModel : OnBoardingViewModel = hiltViewModel()
                /*
                event = {viewModel.onEvent(it)}
                 */
                OnBoardingScreen(viewModel::onEvent)
            }
        }

        navigation(
            route = Routs.NewsNavigation.route,
            startDestination = Routs.NewsNavigatorScreen.route
        ){
            composable(
                route = Routs.NewsNavigatorScreen.route
            ){
                val viewModel : BookmarkViewModel = hiltViewModel()
                NewsNavigator()
//                BookmarkScreen(state = viewModel.state.value, navigateToDetails = {})

//                SearchScreen(state = viewModel.state.value, event = viewModel::onEvent)
//                val articles = viewModel.getNews.collectAsLazyPagingItems()
//                HomeScreen(articles = articles, navigate ={} )
            }
        }
    }

}
