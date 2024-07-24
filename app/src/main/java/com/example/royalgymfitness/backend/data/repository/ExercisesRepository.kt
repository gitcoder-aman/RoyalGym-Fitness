package com.example.royalgymfitness.backend.data.repository

import com.example.royalgymfitness.backend.data.remote.dto.ExerciseAPI
import javax.inject.Inject

class ExercisesRepository @Inject constructor(private val api: ExerciseAPI) {

    suspend fun getExercises() = api.getExercise()
    suspend fun getTargetList() = api.getTargetList()
}