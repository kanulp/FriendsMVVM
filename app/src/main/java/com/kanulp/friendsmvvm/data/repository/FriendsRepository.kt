package com.kanulp.friendsmvvm.data.repository

import com.kanulp.friendsmvvm.data.local.FriendsDao
import com.kanulp.friendsmvvm.data.remote.FriendsRemoteDataSource
import com.kanulp.friendsmvvm.util.performGetOperation
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class FriendsRepository @Inject constructor (private val remoteDatabase:FriendsRemoteDataSource, private val localDatabase:FriendsDao) {

    fun getFriends() = performGetOperation(
            databaseQuery = {localDatabase.getAllFriends()},
            networkCall = {remoteDatabase.getFriends() },
            saveCallResult = { localDatabase.insertAll(it.data) }
    )

    fun getFriend(id:Int){
        GlobalScope.launch {
            localDatabase.getFriend(id)
        }
    }

}