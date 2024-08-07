package com.example.royalgymfitness.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.royalgymfitness.backend.domain.model.ExerciseModel
import com.example.royalgymfitness.db.dao.ExerciseDao
import com.example.royalgymfitness.db.domain.model.ExerciseEntity
import com.example.royalgymfitness.db.utils.Convertors

@Database(entities = [ExerciseEntity::class], version = 1, exportSchema = false)
@TypeConverters(Convertors::class)
abstract class ExerciseDatabase : RoomDatabase(){
    abstract val exerciseDao : ExerciseDao
//    abstract val exerciseListDao : ExerciseListDao
}