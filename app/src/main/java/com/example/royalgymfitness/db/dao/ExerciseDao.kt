package com.example.royalgymfitness.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.royalgymfitness.backend.domain.model.ExerciseModel
import com.example.royalgymfitness.db.domain.model.ExerciseEntity

@Dao
interface ExerciseDao{
    @Query("SELECT * FROM exercise_table")
    suspend fun getAllExercises(): List<ExerciseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exercise: ExerciseEntity)

    @Query("DELETE FROM exercise_table WHERE id = :exerciseId")
    suspend fun deleteExercise(exerciseId : String)

    @Query("SELECT * FROM exercise_table WHERE name = :name")
    suspend fun getExerciseByName(name: String): ExerciseEntity?

}