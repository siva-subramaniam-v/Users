package com.example.users.util

fun String.trimEmail(length: Int) = if (this.length > length) "${this.substring(0, length+1)}..." else this