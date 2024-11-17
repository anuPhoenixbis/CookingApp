package com.example.cookingapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


/*This file holds the main Control of the app . Previously we were controlling
our app from the MainActivity but now it will just launch the app and the Screen
control will be passed here.This file basically holds controls the navigation
and the ViewModel.*/
@Composable
fun RecipeApp(navController: NavHostController){
    val recipeViewModel: MainViewModel= viewModel()//taking care of the viewmodel
    val viewstate by recipeViewModel.categoriesState//holds the state of each Category

    NavHost(navController = navController,
        startDestination = NavigatorRoute.RecipeScreen.route //We use the Sealed class to store the route of Recipe and Detail Screens to avoid running into errors.Errors like not mistyping the route name again and again.
     )
    {
        composable(route= NavigatorRoute.RecipeScreen.route){
            RecipeScreen(viewstate = viewstate//viewstate declared in this file is passed here
                ,navigateToDetail = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("cat" , it)//This part is responsible for passing data from the current(RecipeScreen) to next(DetailScreen) screen.
                    //It utilizes the savedStateHandle, which is a component of the compose navigation framework.
                    //.set is a hash setting a key value pair , here key: "cat" and value: it
                    navController.navigate(NavigatorRoute.DetailScreen.route)//here navController moves to the respective detail screen of which the category is selected
                })
        }
        composable(route=NavigatorRoute.DetailScreen.route){
            val category= navController.previousBackStackEntry?.savedStateHandle?.
            get<Category>("cat")?:Category("","","","")
            /*
            * Here we setup a val category . It first navigates to the previous Screen to fetch data using the "navController.previousBackStackEntry".
            * Now, using the "savedStateHandle" we get the data stored with the key "cat".
            * We will now initialize this data of Category data class with empty Strings*/
            CategoryDetailsScreen(Category = category)    // passing in it the arbitrary var we just declared
        }
    }
}