package com.kanulp.friendsmvvm.ui.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kanulp.friendsmvvm.data.entities.Friend
import com.kanulp.friendsmvvm.data.repository.FriendsRepository
import com.kanulp.friendsmvvm.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(private val repository: FriendsRepository) : ViewModel() {

    fun getPosts(id: Int) : LiveData<Resource<List<Friend>>> {
        return repository.getFriends()
    }
}