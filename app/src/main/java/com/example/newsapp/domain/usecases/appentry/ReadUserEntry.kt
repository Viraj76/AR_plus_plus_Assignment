package com.example.newsapp.domain.usecases.appentry

import com.example.newsapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadUserEntry (
    private val localUserManager: LocalUserManager
){
     operator fun invoke() : Flow<Boolean>{
        return localUserManager.readUserEntry()
    }
}