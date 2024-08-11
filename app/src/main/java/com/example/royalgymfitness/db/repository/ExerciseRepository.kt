package com.example.royalgymfitness.db.repository

import com.example.royalgymfitness.backend.domain.model.ExerciseModel
import com.example.royalgymfitness.db.dao.ExerciseDao
import com.example.royalgymfitness.db.dao.ExerciseListDao
import com.example.royalgymfitness.db.domain.model.ExerciseEntity
import com.example.royalgymfitness.db.domain.model.ExerciseListEntity
import javax.inject.Inject

class ExerciseRepository @Inject constructor(
    private val exerciseDao: ExerciseDao,
    private val exerciseListDao: ExerciseListDao
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

    //here to list of bundle Exercises
    suspend fun insertListOfExercises(listOfExercises : ExerciseListEntity){
        return exerciseListDao.insertListOfExercise(listOfExercises)
    }
    suspend fun getListFavBundleOfExercises() : List<ExerciseListEntity>{
        return exerciseListDao.getListFavBundleOfExercises()
    }

    suspend fun deleteListOfBundleExercises(id : String) {
        return exerciseListDao.deleteListOfBundleExercises(id)
    }
}