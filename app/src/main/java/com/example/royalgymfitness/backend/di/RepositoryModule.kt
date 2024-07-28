package com.example.royalgymfitness.backend.di

import com.example.royalgymfitness.backend.data.repository.ExercisesRepositoryImpl
import com.example.royalgymfitness.backend.domain.repository.ExerciseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindExerciseRepository(
        exercisesRepositoryImpl: ExercisesRepositoryImpl
    ): ExerciseRepository
}
