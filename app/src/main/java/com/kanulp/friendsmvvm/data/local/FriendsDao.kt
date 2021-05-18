package com.kanulp.friendsmvvm.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kanulp.friendsmvvm.data.entities.Friend


@Dao
interface FriendsDao {

    @Query("SELECT * FROM friends")
    fun getAllFriends(): LiveData<List<Friend>>

    @Query("SELECT * FROM friends where id=:ID")
    fun getFriend(ID:Int): Friend


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(friends: List<Friend>)


    @Query("DELETE FROM friends where id = :ID")
    suspend fun deleteFriend(ID: Int)


}