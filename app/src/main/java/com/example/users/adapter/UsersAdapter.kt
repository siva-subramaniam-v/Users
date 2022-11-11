package com.example.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.users.R
import com.example.users.databinding.ListItemUsersBinding
import com.example.users.domain.DomainUsers
import com.example.users.util.trimEmail

class UsersAdapter :
    ListAdapter<DomainUsers, UsersAdapter.ViewHolder>(UsersDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    private var clickListener: ((userId: Long) -> Unit)? = null

    fun setOnclickListener(clickListener: ((id: Long) -> Unit)) {
        this.clickListener = clickListener
    }

    class ViewHolder private constructor(private val binding: ListItemUsersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: DomainUsers,
            clickListener: ((userId: Long) -> Unit)?
        ) {
            binding.genderImage.setImageResource(
                when (item.gender) {
                    "male" -> R.drawable.male
                    "female" -> R.drawable.female
                    else -> R.drawable.male
                }
            )

            binding.status.setImageResource(
                when (item.status) {
                    "active" -> R.drawable.online
                    "inactive" -> R.drawable.offline
                    else -> R.drawable.online
                }
            )

            item.email = item.email.trimEmail(25)

            binding.domainUsers = item
            clickListener?.let {
                binding.root.setOnClickListener {
                    clickListener.invoke(item.id)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemUsersBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class UsersDiffCallback : DiffUtil.ItemCallback<DomainUsers>() {
    override fun areItemsTheSame(oldItem: DomainUsers, newItem: DomainUsers): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DomainUsers, newItem: DomainUsers): Boolean {
        return oldItem == newItem
    }
}