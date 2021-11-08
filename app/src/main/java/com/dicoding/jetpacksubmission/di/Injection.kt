package com.dicoding.jetpacksubmission.di

import android.content.Context
import com.dicoding.jetpacksubmission.data.ContentRepository
import com.dicoding.jetpacksubmission.data.db.LocalDataSource
import com.dicoding.jetpacksubmission.data.remote.RemoteDataSource
import com.dicoding.jetpacksubmission.data.db.ContentDatabase
import com.dicoding.jetpacksubmission.utils.AppExecutors
import com.dicoding.jetpacksubmission.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): ContentRepository {

        val database = ContentDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.contentDao())
        val appExecutors = AppExecutors()

        return ContentRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}