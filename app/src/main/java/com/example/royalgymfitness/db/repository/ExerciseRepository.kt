package com.example.royalgymfitness.db.repository

import com.example.royalgymfitness.backend.domain.model.ExerciseModel
import com.example.royalgymfitness.db.dao.ExerciseDao
import com.example.royalgymfitness.db.domain.model.ExerciseEntity
import javax.inject.Inject

class ExerciseRepository @Inject constructor(
    private val exerciseDao: ExerciseDao,
//    private val exerciseListDao: ExerciseListDao
) {
    suspend fun getAllExercises(): List<ExerciseEntity> {
        return exerciseDao.getAllExercises()
    }
    suspend fun insertExercise(exercise: ExerciseEntity) {
        exerciseDao.insertExercise(exercise)
    }
    suspend fun getExerciseByName(name: String): ExerciseEntity? {
        return exerciseDao.getExerciseByName(name)
    }
    suspend fun deleteExercise(exerciseId: String) {
        return exerciseDao.deleteExercise(exerciseId)
    }
//    suspend fun insertListOfExercises(listOfExercises : List<ExerciseModel>){
//        return exerciseListDao.insertListOfExercise(listOfExercises)
//    }
//    suspend fun getAllListOfExercises() : List<ExerciseModel>{
//        return exerciseListDao.getAllListOfExercises()
//    }

//    suspend fun deleteListOfExercises(id : Int) {
//        return exerciseListDao.deleteListOfExercises(id)
//    }
}