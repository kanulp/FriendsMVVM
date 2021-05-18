package com.kanulp.friendsmvvm.ui.detail

import androidx.lifecycle.ViewModel
import com.kanulp.friendsmvvm.data.repository.FriendsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
public class DetailViewModel @Inject constructor(private val repository: FriendsRepository) : ViewModel() {

    fun getFriend(id: Int) {
        return repository.getFriend(id)
    }
}