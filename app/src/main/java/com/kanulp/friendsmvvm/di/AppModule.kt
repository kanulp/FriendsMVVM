package com.kanulp.friendsmvvm.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kanulp.friendsmvvm.data.local.FriendsDao
import com.kanulp.friendsmvvm.data.local.FriendsRoomDatabase
import com.kanulp.friendsmvvm.data.remote.FriendsApiService
import com.kanulp.friendsmvvm.data.remote.FriendsRemoteDataSource
import com.kanulp.friendsmvvm.data.repository.FriendsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideFriendApiService(retrofit: Retrofit): FriendsApiService = retrofit.create(FriendsApiService::class.java)

    @Singleton
    @Provides
    fun provideFriendsRemoteDataSource(apiservice: FriendsApiService) = FriendsRemoteDataSource(apiservice)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = FriendsRoomDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun providFriendsDao(db: FriendsRoomDatabase) = db.friendDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: FriendsRemoteDataSource,
                          localDataSource: FriendsDao) =
            FriendsRepository(remoteDataSource, localDataSource)
}