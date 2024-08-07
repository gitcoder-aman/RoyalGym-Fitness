package com.example.royalgymfitness.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.royalgymfitness.backend.domain.model.ExerciseModel

//@Dao
//interface ExerciseListDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertListOfExercise(exerciseList: List<ExerciseModel>)
//
//    @Query("SELECT * FROM exercise_list_table")
//    suspend fun getAllListOfExercises() : List<ExerciseModel>
//
////    @Query("SELECT * FROM exercise_list_table WHERE id = :id")
////    suspend fun deleteListOfExercises(id : Int)
//}