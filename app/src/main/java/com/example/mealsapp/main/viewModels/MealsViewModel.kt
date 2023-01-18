package com.example.mealsapp.main.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.input.key.Key.Companion.D
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsapp.main.Repository.MealsApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.response.Category
import model.response.MealApi

class MealsViewModel(private val repository: MealsApiRepository = MealsApiRepository()): ViewModel() {
    val mealsCategory: MutableState<MealApi?> = mutableStateOf<MealApi?>(null)

    init {
        viewModelScope.launch(Dispatchers.IO) {
             mealsCategory.value = getCategories()
        }
    }

    private suspend fun getCategories(): MealApi? {
        return repository.getCategories().getMealCategories().body()
    }
}