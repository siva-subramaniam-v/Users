package com.example.users.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.users.database.DatabasePosts
import com.example.users.database.UsersDatabase
import com.example.users.database.asDomainModel
import com.example.users.domain.DomainUsers
import com.example.users.network.Network
import com.example.users.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val database: UsersDatabase) {
    val users: LiveData<List<DomainUsers>> = Transformations.map(database.usersDao.getAll()) {
        it.asDomainModel()
    }

    suspend fun fetchUsers() {
        withContext(Dispatchers.IO) {
            val users = Network.retrofitService.getUsersAsync()
            database.usersDao.insertAll(*users.asDatabaseModel())
        }
    }

    suspend fun fetchPosts() {
        withContext(Dispatchers.IO) {
            val allPosts = Network.retrofitService.getPostsAsync()
            database.postsDao.insertAll(*allPosts.asDatabaseModel())
        }
    }

    fun getPost(id:Long):LiveData<List<DatabasePosts>> = database.postsDao.getPosts(id)
}