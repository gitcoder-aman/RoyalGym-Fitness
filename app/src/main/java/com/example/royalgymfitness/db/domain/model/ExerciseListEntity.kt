package com.example.royalgymfitness.db.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.royalgymfitness.backend.domain.model.ExerciseModel
import com.example.royalgymfitness.db.utils.Constants

@Entity(tableName = Constants.EXERCISE_LIST_TABLE_NAME)
data class ExerciseListEntity(
    @PrimaryKey val id: String = "0",
    val bundleThumbnailImage : String?,
    val workoutType : String?,
    val exerciseName : String?,
    val exerciseList: List<ExerciseModel> ?= null
){
    constructor() : this("0", "","","",emptyList())

}
