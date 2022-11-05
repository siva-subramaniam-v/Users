package com.example.users.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UsersDao {
    @Query("SELECT * FROM users_table")
    fun getAll(): LiveData<List<DatabaseUsers>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: DatabaseUsers)
}

@Dao
interface PostsDao {
    @Query("SELECT * FROM posts_table WHERE userId = :userId")
    fun getPosts(userId: Long): LiveData<List<DatabasePosts>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg posts: DatabasePosts)
}

@Database(
    entities = [DatabaseUsers::class, DatabasePosts::class],
    version = 1,
    exportSchema = false
)
abstract class UsersDatabase : RoomDatabase() {
    abstract val postsDao: PostsDao
    abstract val usersDao: UsersDao
}

private lateinit var INSTANCE: UsersDatabase

fun getDatabase(context: Context): UsersDatabase {
    synchronized(UsersDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                UsersDatabase::class.java,
                "users"
            ).build()
        }
    }

    return INSTANCE
}