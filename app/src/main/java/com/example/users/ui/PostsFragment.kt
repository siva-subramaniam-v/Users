package com.example.users.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.users.R
import com.example.users.adapter.PostsAdapter
import com.example.users.databinding.FragmentPostsBinding
import com.example.users.viewmodels.PostsViewModel
import com.example.users.viewmodels.PostsViewModelFactory

class PostsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPostsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_posts, container, false)
        binding.lifecycleOwner = this

        val args: PostsFragmentArgs by navArgs()

        val factory = PostsViewModelFactory(args.userId, requireActivity().application)

        val postsViewModel = ViewModelProvider(this, factory)[PostsViewModel::class.java]

        binding.postsViewModel = postsViewModel

        val adapter = PostsAdapter()
        binding.postsList.adapter = adapter

        postsViewModel.userPostsDomain.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.noPosts.visibility = View.INVISIBLE
                adapter.submitList(it)
            } else {
                binding.noPosts.visibility = View.VISIBLE
            }
        }

        return binding.root
    }
}