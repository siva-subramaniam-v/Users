package com.example.users.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.users.R
import com.example.users.adapter.UsersAdapter
import com.example.users.databinding.FragmentUsersBinding
import com.example.users.viewmodels.UsersViewModel

class UsersFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentUsersBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_users, container, false)
        binding.lifecycleOwner = this

        val usersViewModel = ViewModelProvider(this)[UsersViewModel::class.java]
        binding.usersViewModel = usersViewModel

        val adapter = UsersAdapter().also {
            it.setOnclickListener { userId ->
                findNavController().navigate(
                    UsersFragmentDirections.actionUsersFragmentToPostsFragment(userId)
                )
            }
        }

        binding.usersList.adapter = adapter

        return binding.root
    }
}