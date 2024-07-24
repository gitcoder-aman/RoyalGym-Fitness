package com.example.royalgymfitness.backend.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.royalgymfitness.backend.data.repository.ExercisesRepository
import com.example.royalgymfitness.backend.domain.model.ExerciseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val exercisesRepository: ExercisesRepository) :
    ViewModel() {

    private val _exerciseList = MutableStateFlow<List<ExerciseModel>>(emptyList())
    val exerciseList = _exerciseList.asStateFlow()

    private val _targetList = MutableStateFlow<List<String>>(emptyList())
    val targetList = _targetList.asStateFlow()

    init {
        getExercises()
        getTargetList()
    }

    private fun getTargetList() {
        viewModelScope.launch {
            try {
                _targetList.value = exercisesRepository.getTargetList()
            }catch (e : HttpException){
                Log.e("API_ERROR", "HTTP Error: ${e.code()}", e)
            }
        }
    }

    private fun getExercises() {
        viewModelScope.launch {
            try {
                _exerciseList.value = exercisesRepository.getExercises()
            }catch (e : HttpException){
                Log.e("API_ERROR", "HTTP Error: ${e.code()}", e)
            }
        }
    }
}