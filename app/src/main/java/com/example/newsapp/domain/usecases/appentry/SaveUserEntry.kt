package com.example.newsapp.domain.usecases.appentry
import com.example.newsapp.domain.manager.LocalUserManager
class SaveUserEntry(
    private val localUserManager: LocalUserManager
){
    suspend operator fun invoke(){
        localUserManager.saveUserEntry()
    }
}