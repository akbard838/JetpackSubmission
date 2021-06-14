package com.dicoding.jetpacksubmission.di

import com.dicoding.jetpacksubmission.data.ContentRepository
import com.dicoding.jetpacksubmission.data.RemoteDataSource

object Injection {

    fun provideContentRepository(): ContentRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return ContentRepository.getInstance(remoteDataSource)
    }

}