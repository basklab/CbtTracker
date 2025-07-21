package com.oleg.cbttracker.di

import android.app.Application
import androidx.room.Room
import com.oleg.cbttracker.AppDatabase
import com.oleg.cbttracker.ThoughtRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, "thought_entries.db")
            .addMigrations(AppDatabase.MIGRATION_1_2)
            .build()

    @Provides
    @Singleton
    fun provideThoughtRepository(db: AppDatabase): ThoughtRepository =
        ThoughtRepository(db.thoughtEntryDao())
}
