package com.example.users.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import com.example.users.database.DatabasePosts
import com.example.users.database.UsersDatabase
import com.example.users.database.asDomainModel
import com.example.users.domain.DomainPosts
import com.example.users.domain.DomainUsers
import com.example.users.network.Network
import com.example.users.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val database: UsersDatabase) {
    val users: LiveData<List<DomainUsers>> = Transformations.map(database.usersDao.getAll()) {
        it.asDomainModel()
    }

    var filteredPosts = MutableLiveData<List<DomainPosts>>()

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

//    suspend fun filterPosts(userId: Long): LiveData<List<DomainPosts>> {
//        val posts: LiveData<List<DomainPosts>>
//        withContext(Dispatchers.IO) {
//            posts = Transformations.map(database.postsDao.getPosts(userId)) {
//                it.asDomainModel()
//            }
//        }
//        return posts
//    }

    suspend fun filterPosts(userId: Long) {
        withContext(Dispatchers.IO) {
            filteredPosts.value = Transformations.map(database.postsDao.getPosts(userId)) {
                it.asDomainModel()
            }.value
        }
    }

//    fun getPost(id:Long):LiveData<List<DatabasePosts>>{
//        return database.postsDao.getPosts(id)
//    }

    fun getPost(id:Long):LiveData<List<DatabasePosts>> = database.postsDao.getPosts(id)
}