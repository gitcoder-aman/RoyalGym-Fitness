package com.example.royalgymfitness.backend.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class ExerciseModel(
    val bodyPart: String,
    val equipment: String,
    val gifUrl: String,
    val id: String,
    val instructions: List<String>,
    val name: String,
    val secondaryMuscles: List<String>,
    val target: String
) : Parcelable