package com.kanulp.friendsmvvm.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kanulp.friendsmvvm.data.entities.Friend
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Friend::class], version = 1, exportSchema = false)
public abstract class FriendsRoomDatabase : RoomDatabase() {

    abstract fun friendDao(): FriendsDao


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        var TEST_MODE = false

        @Volatile
        private var INSTANCE: FriendsRoomDatabase? = null

        fun getDatabase(context: Context
        ): FriendsRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context,
                        FriendsRoomDatabase::class.java,
                        "friends_db"
                )
                        .fallbackToDestructiveMigration()
                        .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}