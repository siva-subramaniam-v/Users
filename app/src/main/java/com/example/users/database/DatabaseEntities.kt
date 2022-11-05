package com.example.users.database

import androidx.room.*
import com.example.users.domain.DomainPosts
import com.example.users.domain.DomainUsers

@Entity(tableName = "users_table")
data class DatabaseUsers(
    @PrimaryKey val id: Long,
    val name: String,
    val email: String,
    val gender: String,
    val status: String
)

//@Entity(
//    tableName = "posts_table", foreignKeys = [ForeignKey(
//        entity = DatabaseUsers::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("userId"),
//        onDelete = ForeignKey.CASCADE
//    )]
//)
@Entity(tableName = "posts_table")
data class DatabasePosts(
    @PrimaryKey
    val id: Long,
    val userId: Long,
    val title: String,
    val body: String
)

fun List<DatabaseUsers>.asDomainModel(): List<DomainUsers> {
    return map {
        DomainUsers(
            id = it.id,
            name = it.name,
            email = it.email,
            gender = it.gender,
            status = it.status
        )
    }
}

@JvmName("asDomainModelDatabasePosts")
fun List<DatabasePosts>.asDomainModel(): List<DomainPosts> {
    return map {
        DomainPosts(
            id = it.id,
            userId = it.userId,
            title = it.title,
            body = it.body
        )
    }
}