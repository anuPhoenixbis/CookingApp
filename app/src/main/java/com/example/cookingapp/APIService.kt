package com.example.cookingapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET


private val retrofit= Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
/*the retrofit is the object created. This obj first takes the 1st part of the url ,
* it then converts the json code extracted from the url when attached to the endpoint
* to the code understandable by the gradle in kotlin and stores in the create().
* It then finally builds the data recieved by the in the app */

    val recipeService = retrofit.create(APIService::class.java)//"Create" is an implementation of the API endpoints defined by the service interface.
//reciepeService is the obj that allows us to use the data provided in the url

interface APIService {// @GET is a composable supported by retrofit2 dependency
    @GET("categories.php")//it gets us an http request from categories.php (its a file under 'list all meal categories')
    suspend fun getCategories():CategoriesResponse // suspending fun name is "getCategories" which suspends the CategoriesResponse class till the data is fetched from the https request and then returns the main data class CategoriesResponse once the complete data is fetched
//Basically , this interface is just used to hold the endpoint of this http url to extract the json contents mentioned in this url
}