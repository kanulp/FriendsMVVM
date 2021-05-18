package com.kanulp.friendsmvvm.data.remote

import com.kanulp.friendsmvvm.data.entities.FriendsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FriendsApiService {

    @GET("users")
    suspend fun getFriends(
    ): Response<FriendsModel>
}