
// Retrofit interface
package com.ayush.retrofitexample

import model.response.Category
import model.response.MealApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApiCategroy {
    @GET("categories.php")
    suspend fun getMealCategories() : Response<MealApi>
}