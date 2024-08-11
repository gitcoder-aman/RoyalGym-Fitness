package com.example.royalgymfitness.db.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.royalgymfitness.db.utils.Constants

@Entity(tableName = Constants.EXERCISE_TABLE_NAME)
data class ExerciseEntity(
    @PrimaryKey val id: String = "0",
    val bodyPart: String,
    val equipment: String,
    val gifUrl: String,
    val instructions: List<String>,
    val name: String,
    val secondaryMuscles: List<String>,
    val target: String
)
