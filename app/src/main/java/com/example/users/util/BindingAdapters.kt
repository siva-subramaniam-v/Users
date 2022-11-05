package com.example.users.util

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.users.adapter.PostsAdapter
import com.example.users.adapter.UsersAdapter
import com.example.users.domain.DomainPosts
import com.example.users.domain.DomainUsers


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<DomainUsers>?) {
    val adapter = recyclerView.adapter as UsersAdapter
    adapter.submitList(data)
    Log.i("BindingAdapter", "inside bindRecyclerView")
}

@BindingAdapter("listDataPost")
fun bindRecyclerViewPosts(recyclerView: RecyclerView, data: List<DomainPosts>?) {
    val adapter = recyclerView.adapter as PostsAdapter
    adapter.submitList(data)
    val title = data?.get(0)?.body
    Log.i("BindingAdapter", title?:"null data")
}