package com.example.royalgymfitness.db.utils

import androidx.room.TypeConverter
import com.example.royalgymfitness.backend.domain.model.ExerciseModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Convertors {

    @TypeConverter
    fun fromExerciseList(value: List<ExerciseModel>?): String {
        val gson = Gson()
        val type = object : TypeToken<List<ExerciseModel>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toExerciseList(value: String): List<ExerciseModel>? {
        val gson = Gson()
        val type = object : TypeToken<List<ExerciseModel>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromStringList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromListString(list: List<String>): String {
        return Gson().toJson(list)
    }
}