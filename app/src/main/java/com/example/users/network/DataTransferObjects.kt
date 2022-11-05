package com.example.users.network

import com.example.users.database.DatabasePosts
import com.example.users.database.DatabaseUsers
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkUsers(
    val id: Long,
    val name: String,
    val email: String,
    val gender: String,
    val status: String
)

@JsonClass(generateAdapter = true)
data class NetworkPosts(
    val id: Long,
    @Json(name = "user_id") val userId: Long,
    val title: String,
    val body: String
)

fun List<NetworkUsers>.asDatabaseModel(): Array<DatabaseUsers> {
    return map {
        DatabaseUsers(
            id = it.id,
            name = it.name,
            email = it.email,
            gender = it.gender,
            status = it.status
        )
    }.toTypedArray()
}

fun List<NetworkPosts>.asDatabaseModel(): Array<DatabasePosts> {
    return map {
        DatabasePosts(
            id = it.id,
            userId = it.userId,
            title = it.title,
            body = it.body
        )
    }.toTypedArray()
}