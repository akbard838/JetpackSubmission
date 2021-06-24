package com.dicoding.jetpacksubmission.di

import android.content.Context
import com.dicoding.jetpacksubmission.data.ContentRepository2
import com.dicoding.jetpacksubmission.data.LocalDataSource2
import com.dicoding.jetpacksubmission.data.RemoteDataSource2
import com.dicoding.jetpacksubmission.data.db.ContentDatabase
import com.dicoding.jetpacksubmission.utils.AppExecutors
import com.dicoding.jetpacksubmission.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): ContentRepository2 {

        val database = ContentDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource2.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource2.getInstance(database.contentDao())
        val appExecutors = AppExecutors()

        return ContentRepository2.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}