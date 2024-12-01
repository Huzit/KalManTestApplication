package com.example.kalmansample.di

import android.app.Application
import android.content.Context
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kalmansample.repository.KalManDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Provides
    @Singleton
    fun provideRoomBuiler(@ApplicationContext mContext: Context) =
        Room.databaseBuilder(
            mContext,
            KalManDatabase::class.java,
            "KalMan"
        ).build()
    

    @Provides
    fun provideRowDao(database: KalManDatabase) =database.getKalManDao()
}