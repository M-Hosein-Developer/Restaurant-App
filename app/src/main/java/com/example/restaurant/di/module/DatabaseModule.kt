package com.example.restaurant.di.module

import android.content.Context
import androidx.room.Room
import com.example.movies.model.room.Dao
import com.example.movies.model.room.MyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : MyDatabase{
        return Room.databaseBuilder(context , MyDatabase::class.java , "database.db")
            .allowMainThreadQueries()
            .build()
    }


    @Provides
    @Singleton
    fun provideDao(database: MyDatabase) : Dao{
        return database.doa()
    }

}