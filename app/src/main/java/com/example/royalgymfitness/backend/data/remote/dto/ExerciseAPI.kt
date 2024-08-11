package com.example.royalgymfitness.backend.data.remote.dto

import com.example.royalgymfitness.backend.domain.model.ExerciseModel
import com.example.royalgymfitness.backend.util.Constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ExerciseAPI {

    @GET("/exercises?limit=100")
    @Headers(
        "x-rapidapi-key: ${Constants.API_KEY}",
        "x-rapidapi-host: ${Constants.API_HOST}"
    )
    suspend fun getExercise() : List<ExerciseModel>

    @GET("/exercises/target/{target}?limit=2")
    @Headers(
        "x-rapidapi-key: ${Constants.API_KEY}",
        "x-rapidapi-host: ${Constants.API_HOST}"
    )
    suspend fun getTarget(@Path("target") target: String) : List<ExerciseModel>

    @GET("/exercises/bodyPart/{bodyPart}?limit=2")
    @Headers(
        "x-rapidapi-key: ${Constants.API_KEY}",
        "x-rapidapi-host: ${Constants.API_HOST}"
    )
    suspend fun getBodyPart(@Path("bodyPart") bodyPart: String) : List<ExerciseModel>

    @GET("/exercises/equipment/{equipment}?limit=2")
    @Headers(
        "x-rapidapi-key: ${Constants.API_KEY}",
        "x-rapidapi-host: ${Constants.API_HOST}"
    )
    suspend fun getEquipmentExercise(@Path("equipment") equipmentName: String) : List<ExerciseModel>

    @GET("/exercises/exercise/{id}")
    @Headers(
        "x-rapidapi-key: ${Constants.API_KEY}",
        "x-rapidapi-host: ${Constants.API_HOST}"
    )
    suspend fun getParticularExercise(@Path("id") id :  String) : ExerciseModel


}