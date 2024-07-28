package com.example.royalgymfitness.backend.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.royalgymfitness.backend.domain.model.ExerciseModel
import com.example.royalgymfitness.backend.domain.repository.ExerciseRepository
import com.example.royalgymfitness.presentations.otherscreen.ExerciseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(private val exercisesRepository: ExerciseRepository) :
    ViewModel() {

    private val _exerciseList = MutableStateFlow<ExerciseState<List<ExerciseModel>>>(ExerciseState.Loading)
    val exerciseList = _exerciseList.asStateFlow()

    private val _targetExerciseList = MutableStateFlow<ExerciseState<List<ExerciseModel>>>(
        ExerciseState.Loading)
    val targetExerciseList = _targetExerciseList.asStateFlow()

    private val _bodyPartExerciseList = MutableStateFlow<ExerciseState<List<ExerciseModel>>>(
        ExerciseState.Loading)
    val bodyPartExerciseList = _bodyPartExerciseList.asStateFlow()

    private val _equipmentExerciseList = MutableStateFlow<ExerciseState<List<ExerciseModel>>>(
        ExerciseState.Loading)
    val equipmentExerciseList = _equipmentExerciseList.asStateFlow()

    init {
        getExercises()
    }

    private fun getExercises() {
        viewModelScope.launch {
            try {
                val exercises = exercisesRepository.getExercises()
                _exerciseList.value = ExerciseState.Success(exercises)
            } catch (e: Exception) {
                _exerciseList.value = ExerciseState.Error(e.message.toString())
            }
        }
    }

    fun getTargetExercise(targetExerciseName: String) {
        viewModelScope.launch {
            _targetExerciseList.value = ExerciseState.Loading
            try {
                val targetExercisesList = exercisesRepository.getTarget(targetExerciseName)
                _targetExerciseList.value = ExerciseState.Success(targetExercisesList)
                Log.d("@@viewModel", "getTargetExercise: ${targetExerciseList.value}")
            } catch (e: Exception) {
                _targetExerciseList.value = ExerciseState.Error(e.message.toString())
            }
        }
    }
    fun getBodyPartExercise(bodyPartName: String) {
        viewModelScope.launch {
            _bodyPartExerciseList.value = ExerciseState.Loading
            try {
                val bodyPartExercisesList = exercisesRepository.getBodyPart(bodyPartName)
                _bodyPartExerciseList.value = ExerciseState.Success(bodyPartExercisesList)
            } catch (e: Exception) {
                _bodyPartExerciseList.value = ExerciseState.Error(e.message.toString())
            }
        }
    }
    fun getEquipmentExercise(equipmentName: String) {
        viewModelScope.launch {
            _bodyPartExerciseList.value = ExerciseState.Loading
            try {
                val equipmentExercisesList = exercisesRepository.getEquipmentExercises(equipmentName)
                _equipmentExerciseList.value = ExerciseState.Success(equipmentExercisesList)
            } catch (e: Exception) {
                _equipmentExerciseList.value = ExerciseState.Error(e.message.toString())
            }
        }
    }


}