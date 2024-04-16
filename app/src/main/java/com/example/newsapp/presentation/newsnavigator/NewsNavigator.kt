package com.example.newsapp.presentation.newsnavigator


import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.bookmark.BookmarkScreen
import com.example.newsapp.presentation.bookmark.BookmarkViewModel
import com.example.newsapp.presentation.details.DetailsEvent
import com.example.newsapp.presentation.details.DetailsScreen
import com.example.newsapp.presentation.details.DetailsViewModel
import com.example.newsapp.presentation.home.HomeScreen
import com.example.newsapp.presentation.home.HomeViewModel
import com.example.newsapp.presentation.navgraph.Routs
import com.example.newsapp.presentation.newsnavigator.components.BottomNavigationItem
import com.example.newsapp.presentation.newsnavigator.components.NewsBottomNavigation
import com.example.newsapp.presentation.search.SearchScreen
import com.example.newsapp.presentation.search.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsNavigator() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.baseline_home_24, text = "Home"),
            BottomNavigationItem(icon = R.drawable.baseline_bookmark_border_24, text = "Bookmark"),
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem = when (backStackState?.destination?.route) {
        Routs.HomeScreen.route -> 0
        Routs.BookmarkScreen.route -> 1
        else -> 0
    }

    //Hide the bottom navigation when the user is in the details screen
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Routs.HomeScreen.route ||
                backStackState?.destination?.route == Routs.BookmarkScreen.route
    }


    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (isBottomBarVisible) {
            NewsBottomNavigation(
                items = bottomNavigationItems,
                selectedItem = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTab(
                            navController = navController,
                            route = Routs.HomeScreen.route
                        )



                        1 -> navigateToTab(
                            navController = navController,
                            route = Routs.BookmarkScreen.route
                        )
                    }
                }
            )
        }
    }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Routs.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Routs.HomeScreen.route) { backStackEntry ->
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.getNews.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigateToSearch = {
                        navigateToTab(
                            navController = navController,
                            route = Routs.SearchScreen.route
                        )
                    },
                    navigateToDetail = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }
            composable(route = Routs.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController)
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetail = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }
            composable(route = Routs.DetailsScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()
                if(viewModel.sideEffect != null){
                    Toast.makeText(LocalContext.current,viewModel.sideEffect.toString(),Toast.LENGTH_SHORT).show()
                    viewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->
                        DetailsScreen(
                            article = article,
                            event = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() },
//                            sideEffect = viewModel.sideEffect
                        )
                    }

            }
            composable(route = Routs.BookmarkScreen.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController)
                BookmarkScreen(
                    state = state,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigateToTab(
            navController = navController,
            route = Routs.HomeScreen.route
        )
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetails(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Routs.DetailsScreen.route
    )
}