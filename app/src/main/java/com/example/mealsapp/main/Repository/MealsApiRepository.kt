package com.example.mealsapp.main.Repository

import com.ayush.retrofitexample.MealsApiCategroy
import com.example.mealsapp.main.api.RetrofitHelper
import model.response.Category

class MealsApiRepository {

    suspend fun getCategories(): MealsApiCategroy {
        return RetrofitHelper.getInstance().create(MealsApiCategroy::class.java)
    }
}