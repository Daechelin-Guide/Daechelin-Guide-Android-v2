package com.dgsw.guidedaechelin.presentation.di.module

import android.content.Context
import androidx.room.Room
import com.dgsw.guidedaechelin.data.database.review.ReviewDao
import com.dgsw.guidedaechelin.data.database.review.ReviewDatabase
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
    fun provideReviewDatabase(@ApplicationContext context: Context) : ReviewDatabase =
        Room.databaseBuilder(context,ReviewDatabase::class.java,"review_database")
            .fallbackToDestructiveMigration()
            .build()


    @Provides
    @Singleton
    fun provideTodoDao(database: ReviewDatabase) : ReviewDao =
        database.reviewDao()

}