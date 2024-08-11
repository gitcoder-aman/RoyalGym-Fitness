package com.example.royalgymfitness.db.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.royalgymfitness.backend.domain.model.ExerciseModel
import com.example.royalgymfitness.db.domain.model.ExerciseEntity
import com.example.royalgymfitness.db.domain.model.ExerciseListEntity
import com.example.royalgymfitness.db.repository.ExerciseRepository
import com.example.royalgymfitness.presentations.otherscreen.ExerciseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseDBViewModel @Inject constructor(
    private val exerciseRepository: ExerciseRepository
) : ViewModel() {

    private val _exerciseFavList = MutableLiveData<ExerciseState<List<ExerciseEntity>>>()
    val exerciseFavList : LiveData<ExerciseState<List<ExerciseEntity>>> get() = _exerciseFavList

    private val _isFavorite = MutableLiveData<ExerciseState<Boolean>>(ExerciseState.Loading)
    val isFavorite: LiveData<ExerciseState<Boolean>> get() = _isFavorite

    private val _listExerciseBundle = MutableLiveData<ExerciseState<List<ExerciseListEntity>>>(null)
    val listExerciseBundle : LiveData<ExerciseState<List<ExerciseListEntity>>> get() = _listExerciseBundle

    init {
        getAllExercises()
        getListFavBundleOfExercises()
    }

    private fun getListFavBundleOfExercises() {
        viewModelScope.launch {
            _listExerciseBundle.value = ExerciseState.Loading
            try {
                val listOfExercisesBundle = exerciseRepository.getListFavBundleOfExercises()
                _listExerciseBundle.value = ExerciseState.Success(listOfExercisesBundle)
            } catch (e: Exception) {
                e.printStackTrace()
                _listExerciseBundle.value = ExerciseState.Error(e.message.toString())
            }
        }
    }
    fun insertBundleFavListOfExercises(exerciseList: ExerciseListEntity) {
        viewModelScope.launch {
            _isFavorite.value = ExerciseState.Loading
            try {
                _isFavorite.value = ExerciseState.Success(true)
                exerciseRepository.insertListOfExercises(exerciseList)
                getListFavBundleOfExercises()
            } catch (e: Exception) {
                e.printStackTrace()
                _isFavorite.value = ExerciseState.Success(false)
            }
        }
    }

    fun favRemoveListOfBundleExercises(id : String) {
        viewModelScope.launch {
            _isFavorite.value = ExerciseState.Loading
            try {
                exerciseRepository.deleteListOfBundleExercises(id)
                _isFavorite.value = ExerciseState.Success(false)
            } catch (e: Exception) {
                e.printStackTrace()
                _isFavorite.value = ExerciseState.Error(e.message.toString())
            }
        }
    }

    fun favExercise(exercise: ExerciseEntity) {

        viewModelScope.launch {
            _isFavorite.value = ExerciseState.Loading
            try {
                exerciseRepository.insertExercise(exercise)
                _isFavorite.value = ExerciseState.Success(true)
            }catch (e : Exception){
                e.printStackTrace()
                _isFavorite.value = ExerciseState.Error(e.message.toString())
            }
        }
    }
    fun favRemoveExercise(exerciseId : String){
        viewModelScope.launch {
            _isFavorite.value = ExerciseState.Loading
            try {
                exerciseRepository.deleteExercise(exerciseId)
                _isFavorite.value = ExerciseState.Success(false)
            }catch (e : Exception){
                e.printStackTrace()
                _isFavorite.value = ExerciseState.Error(e.message.toString())
            }
        }
    }
    private fun getAllExercises(){
        viewModelScope.launch {
            _exerciseFavList.value = ExerciseState.Loading
            try {
                val exercisesFavList = exerciseRepository.getAllExercises()
                _exerciseFavList.value = ExerciseState.Success(exercisesFavList)
            } catch (e: Exception) {
                e.printStackTrace()
                _exerciseFavList.value = ExerciseState.Error(e.message.toString())
            }
        }
    }

}