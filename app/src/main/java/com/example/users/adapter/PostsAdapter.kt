package com.example.users.adapter

import com.example.users.databinding.ListItemPostsBinding
import com.example.users.domain.DomainPosts
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class PostsAdapter :
    ListAdapter<DomainPosts, PostsAdapter.ViewHolder>(PostsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(private val binding: ListItemPostsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: DomainPosts,
        ) {
            binding.domainPosts = item
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemPostsBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class PostsDiffCallback : DiffUtil.ItemCallback<DomainPosts>() {
    override fun areItemsTheSame(oldItem: DomainPosts, newItem: DomainPosts): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DomainPosts, newItem: DomainPosts): Boolean {
        return oldItem == newItem
    }
}