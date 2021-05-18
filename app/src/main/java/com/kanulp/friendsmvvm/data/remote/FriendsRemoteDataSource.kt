package com.kanulp.friendsmvvm.data.remote

import javax.inject.Inject


class FriendsRemoteDataSource @Inject constructor(private val friendsApiService : FriendsApiService):BaseDataSource(){
    suspend fun getFriends() =  getResult { friendsApiService.getFriends() }

}
