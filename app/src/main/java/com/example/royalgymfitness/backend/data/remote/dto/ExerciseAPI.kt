package com.example.royalgymfitness.backend.data.remote.dto

import com.example.royalgymfitness.backend.domain.model.ExerciseModel
import com.example.royalgymfitness.backend.util.Constants
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ExerciseAPI {

    @GET("/exercises")
    @Headers(
        "x-rapidapi-key: ${Constants.API_KEY}",
        "x-rapidapi-host: ${Constants.API_HOST}"
    )
    suspend fun getExercise() : List<ExerciseModel>

    @GET("/exercises/targetList")
    @Headers(
        "x-rapidapi-key: ${Constants.API_KEY}",
        "x-rapidapi-host: ${Constants.API_HOST}"
    )
    suspend fun getTargetList() : List<String>
}