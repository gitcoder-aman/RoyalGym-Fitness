package com.example.royalgymfitness.backend.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class ExerciseModel(
    @PrimaryKey val id: String = "0",
    val bodyPart: String,
    val equipment: String,
    val gifUrl: String,
    val instructions: List<String> ,
    val name: String,
    val secondaryMuscles: List<String>,
    val target: String
) : Parcelable