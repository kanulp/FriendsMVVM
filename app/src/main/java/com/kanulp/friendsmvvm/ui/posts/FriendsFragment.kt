package com.kanulp.friendsmvvm.ui.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kanulp.friendsmvvm.R
import com.kanulp.friendsmvvm.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FriendsFragment : Fragment() {

    var recyclerView: RecyclerView? = null
    private var viewModel: FriendsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_friends, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(FriendsViewModel::class.java)

        setupObserver()

    }
    fun setupObserver(){

        viewModel?.getPosts(32192)?.observe(requireActivity(), {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Log.d("MAIN", "GOT SUCCESS with array size :  ${it.data?.size}")
                    val adapter = PostsGridAdapter(it.data, requireContext(), object : PostsGridAdapter.OnItemClickListener {
                        override fun onItemClick(position: Int) {
                            loadDetailPage(view, it.data!![position].id)
                        }
                    })
                    val layoutManager = LinearLayoutManager(requireContext())
                    recyclerView?.layoutManager = layoutManager
                    recyclerView?.adapter = adapter
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

            }
        })
    }

    private fun loadDetailPage(view:View?,id: Int) {

        val action = FriendsFragmentDirections.actionFriendsFragmentToDetailFragment(id=id.toString())
        view?.let { Navigation.findNavController(it).navigate(action) }
    }


}