package com.example.royalgymfitness.backend.domain.repository

import com.example.royalgymfitness.backend.domain.model.ExerciseModel

interface ExerciseRepository {
    suspend fun getExercises(): List<ExerciseModel>
    suspend fun getTarget(target: String): List<ExerciseModel>
    suspend fun getBodyPart(bodyPart: String): List<ExerciseModel>
    suspend fun getEquipmentExercises(equipmentName: String): List<ExerciseModel>
    suspend fun getParticularExercise(id: String): ExerciseModel

}