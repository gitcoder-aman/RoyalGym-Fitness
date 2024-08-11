package com.example.royalgymfitness.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.royalgymfitness.backend.domain.model.ExerciseModel
import com.example.royalgymfitness.db.domain.model.ExerciseListEntity

@Dao
interface ExerciseListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListOfExercise(exerciseList: ExerciseListEntity)

    @Query("SELECT * FROM exercise_list_table")
    suspend fun getListFavBundleOfExercises() : List<ExerciseListEntity>

    @Query("DELETE FROM exercise_list_table WHERE id = :id")
    suspend fun deleteListOfBundleExercises(id : String)
}