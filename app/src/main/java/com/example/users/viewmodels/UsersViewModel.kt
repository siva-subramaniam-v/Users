package com.example.users.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.users.database.getDatabase
import com.example.users.repository.Repository
import kotlinx.coroutines.launch

class UsersViewModel(application: Application): AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val repository = Repository(database)

    init {
        viewModelScope.launch {
            try {
                repository.fetchUsers()
                repository.fetchPosts()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    val users = repository.users
}