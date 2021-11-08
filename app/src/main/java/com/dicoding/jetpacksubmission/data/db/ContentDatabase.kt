package com.dicoding.jetpacksubmission.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.jetpacksubmission.data.db.model.MovieEntity
import com.dicoding.jetpacksubmission.data.db.model.TvShowEntity

@Database(
    entities = [MovieEntity::class, TvShowEntity::class],
    version = 2,
    exportSchema = false
)
abstract class ContentDatabase : RoomDatabase() {
    abstract fun contentDao(): ContentDao

    companion object {
        private var INSTANCE: ContentDatabase? = null

        private val sLock = Any()

        fun getInstance(context: Context): ContentDatabase {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ContentDatabase::class.java, "content.db"
                    )
                        .build()
                }
                return INSTANCE as ContentDatabase
            }
        }
    }
}