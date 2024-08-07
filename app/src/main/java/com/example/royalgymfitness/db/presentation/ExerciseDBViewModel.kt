package com.example.royalgymfitness.db.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.royalgymfitness.backend.domain.model.ExerciseModel
import com.example.royalgymfitness.db.domain.model.ExerciseEntity
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

    private val _listOfExercises = MutableLiveData<ExerciseState<List<ExerciseModel>>>()
    val listOfExercises : LiveData<ExerciseState<List<ExerciseModel>>> get() = _listOfExercises

    init {
        getAllExercises()
//        getAllListOfExercises()
    }

//    private fun getAllListOfExercises() {
//        viewModelScope.launch {
//            _listOfExercises.value = ExerciseState.Loading
//            try {
//                val listOfExercises = exerciseRepository.getAllListOfExercises()
//                _listOfExercises.value = ExerciseState.Success(listOfExercises)
//            } catch (e: Exception) {
//                e.printStackTrace()
//                _listOfExercises.value = ExerciseState.Error(e.message.toString())
//            }
//        }
//    }
//    fun favListOfExercises(exerciseList: List<ExerciseModel>) {
//        viewModelScope.launch {
//            _isFavorite.value = ExerciseState.Loading
//            try {
//                _isFavorite.value = ExerciseState.Success(true)
//                exerciseRepository.insertListOfExercises(exerciseList)
//            } catch (e: Exception) {
//                e.printStackTrace()
//                _listOfExercises.value = ExerciseState.Error(e.message.toString())
//            }
//        }
//    }

//    fun favRemoveListOfExercises(id : Int) {
//        viewModelScope.launch {
//            _isFavorite.value = ExerciseState.Loading
//            try {
//                exerciseRepository.deleteListOfExercises(id)
//                _isFavorite.value = ExerciseState.Success(false)
//            } catch (e: Exception) {
//                e.printStackTrace()
//                _isFavorite.value = ExerciseState.Error(e.message.toString())
//            }
//        }
//    }

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