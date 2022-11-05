package com.example.users.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.users.database.asDomainModel
import com.example.users.database.getDatabase
import com.example.users.repository.Repository
import kotlinx.coroutines.launch

class PostsViewModel(val id:Long, val _application: Application) : AndroidViewModel(_application) {
    private val database = getDatabase(_application)
    private val repository = Repository(database)
    private val userPosts = repository.getPost(id)
    val userPostsDomain = Transformations.map(userPosts){
        it.asDomainModel()
    }

    init {
        userPosts?.let { domainPostsList ->
            domainPostsList.value?.forEach {
                Log.i("PostViewModel -> ", it.title)
            }
        }
    }
}