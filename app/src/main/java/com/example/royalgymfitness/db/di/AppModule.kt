package com.example.royalgymfitness.db.di

import android.app.Application
import androidx.room.Room
import com.example.royalgymfitness.db.database.ExerciseDatabase
import com.example.royalgymfitness.db.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideExerciseDatabase(application: Application): ExerciseDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = ExerciseDatabase::class.java,
            name = Constants.EXERCISE_DATABASE_NAME
        ).build()
    }
    @Provides
    @Singleton
    fun provideExerciseDao(exerciseDatabase: ExerciseDatabase) = exerciseDatabase.exerciseDao

//    @Provides
//    @Singleton
//    fun provideListOfExerciseDao(exerciseDatabase: ExerciseDatabase) = exerciseDatabase.exerciseListDao

}