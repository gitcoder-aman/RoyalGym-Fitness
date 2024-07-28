package com.example.royalgymfitness.backend.data.repository

import com.example.royalgymfitness.backend.data.remote.dto.ExerciseAPI
import com.example.royalgymfitness.backend.domain.model.ExerciseModel
import com.example.royalgymfitness.backend.domain.repository.ExerciseRepository
import javax.inject.Inject

class ExercisesRepositoryImpl @Inject constructor(private val api: ExerciseAPI) : ExerciseRepository {
    override suspend fun getExercises(): List<ExerciseModel> {
        return api.getExercise()
    }

    override suspend fun getTarget(target: String): List<ExerciseModel> {
        return api.getTarget(target)
    }

    override suspend fun getBodyPart(bodyPart: String): List<ExerciseModel> {
        return api.getBodyPart(bodyPart)
    }

    override suspend fun getEquipmentExercises(equipmentName: String): List<ExerciseModel> {
        return api.getEquipmentExercise(equipmentName)
    }

    override suspend fun getParticularExercise(id: String): ExerciseModel {
        return api.getParticularExercise(id)
    }


}