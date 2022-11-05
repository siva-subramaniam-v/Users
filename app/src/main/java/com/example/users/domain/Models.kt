package com.example.users.domain

data class DomainUsers(
    val id: Long,
    val name: String,
    var email: String,
    val gender: String,
    val status: String
)

data class DomainPosts(
    val id: Long,
    val userId: Long,
    val title: String,
    val body: String
)