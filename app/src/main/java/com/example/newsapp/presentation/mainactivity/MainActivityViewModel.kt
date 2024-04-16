package com.example.newsapp.presentation.mainactivity

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.usecases.appentry.AppEntryUseCases
import com.example.newsapp.presentation.navgraph.Routs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
): ViewModel() {

     private val _splashCondition = mutableStateOf(true)
     val splashCondition: MutableState<Boolean> = _splashCondition

    /*
    above and below are same kind of declarations
     */

    var startDestination by mutableStateOf(Routs.AppStartNavigation.route)
        private set


    init {
        appEntryUseCases.readUserEntry().onEach {startHomeScreen->
            startDestination = if(startHomeScreen){
                Routs.NewsNavigation.route
            } else{
                Routs.AppStartNavigation.route
            }

            delay(300)
            _splashCondition.value = false
        }.launchIn(viewModelScope)
    }


}