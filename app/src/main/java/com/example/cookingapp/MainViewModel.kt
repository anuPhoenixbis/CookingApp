package com.example.cookingapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

// this file takes care of fetching the data from the API and providing it to the UI file to show on the app
    private val _categoriesState = mutableStateOf(RecipeState())//it takes care of the state of each recipe stored in the RecipeState data class
    val categoriesState : State<RecipeState> =_categoriesState//copying the value in the _categoriesState in another variable as it was private val , now this global variable stores the State of each category using the State Composable and be used in the other classes file
//The state composable changes/updates the UI of the app on which each category is displayed once its loaded from the Api



    init{//initialization of the fun
        fetchCategories()
    }



    private fun fetchCategories(){
        // this fun allows us to fetch data from the api website
        viewModelScope.launch {//viewModelScope is a coroutine which is lauched using the 'launch' keyword . This coroutine allows us to use suspend fun without intfering the main thread.
        /*A coroutine is a computer program component that allows execution to be suspended and
        resumed, and is used for cooperative multitasking. Coroutines are lightweight constructs
        that work at the user level and are governed by a cooperative scheduler that starts them.
         They are not preempted like a process or a thread.*/
        /*Coroutines are well-suited for implementing familiar program components such as
         cooperative tasks, exceptions, event loops, iterators, infinite lists, and pipes.*/
            try {
                     val response = recipeService.getCategories()//in this variable we are calling recipeService and we are calling getCategories(it contains the categories and sub-categories)
                _categoriesState.value=_categoriesState.value.copy(// we are copying the response and editing the current categories and passing the response in it passing the categories
                    list=response.categories,
                    loading = false,
                    error = null
                )
        }catch (e:Exception){// if an error is encountered then we are passing these values to it
            _categoriesState.value = _categoriesState.value.copy(
                loading = false,
                error = "Error fetching Categories ${e.message}"
            )
        }
        }
    }


    data class RecipeState(
    //this class handles whether the url is loaded or not, whether we have the recipes or not, do we have an error or not
        // it basically tells us the state of the loading url
        val loading: Boolean= true,//if the recipe is loaded or not
        val list: List<Category> = emptyList(),//its the list containing the recipe details from the Category data class
        val error : String? = null//to check if there is an error in the input or not
    )
}