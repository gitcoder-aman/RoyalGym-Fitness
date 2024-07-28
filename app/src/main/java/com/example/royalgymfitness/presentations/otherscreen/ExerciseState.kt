package com.example.royalgymfitness.presentations.otherscreen

sealed class ExerciseState<out T> {
    object Loading : ExerciseState<Nothing>()
    data class Success<out T>(val data: T) : ExerciseState<T>()
    data class Error(val message: String) : ExerciseState<Nothing>()
}