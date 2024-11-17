package com.example.cookingapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeScreen(modifier: Modifier=Modifier,
                 navigateToDetail:(Category)->Unit,
                 viewstate : MainViewModel.RecipeState// (viewstate is of type MainViewModel of type RecipeState in it)to access the categoriesState from the MainViewModel in a variable
){//this composable takes the navController as the input which is passed in the navHost
    //  (This has now been declared in the RecipeApp file)  val recipeViewModel : MainViewModel= viewModel()//MainViewModel lets us get the data from the api
    //  (Now This has been moved to the arbitrary var in RecipeScreen fun)  val viewstate by recipeViewModel.categoriesState// to access the categoriesState from the MainViewModel in a variable
    Box(modifier = Modifier.fillMaxSize()){
        when{
            viewstate.loading ->{
                CircularProgressIndicator(modifier.align(Alignment.Center))// when loading is true this buffering circle is shown
            }
            viewstate.error != null ->{
                Text(text = "Error Occurred")
            }
            else->{
                //Display Categories
                CategoryScreen(categories = viewstate.list,navigateToDetail)//this composable is called in the previous composable so passed this lambda fun in it as well
            }
        }
    }

}

@Composable
fun CategoryScreen(categories : List<Category>,//this composable is called in the previous composable so passed this lambda fun in it as well
                   navigateToDetail:(Category)->Unit){ //categories is passed to this fun of type list of type Category data class, this is an arbitrary val created to navigate inside this fun
    LazyVerticalGrid(GridCells.Fixed(2),

        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .border(BorderStroke(2.dp, color = Color.Black))){
         items(categories){// passing the val "categories" at the data class "categoriesResponse" as items of lazygrids
                 category->//here we referring each item in these lazy grids to be "category" then each item is calling the fun CategoryItem in it each category is referred to as the "category" passed in the fun
             CategoryItem(category=category,navigateToDetail)// 1st category is of the fun and 2nd category is of the lazygrids
        }//this composable is called in the previous composable so passed this lambda fun in it as well
    }
}




//this composable will take care how each item looks like
@Composable
fun CategoryItem(category: Category,//this composable is called in the previous composable so passed this lambda fun in it as well
                 navigateToDetail:(Category)->Unit){//using the lambda fun as the navController in it passing the a var of type Category data class
    Column (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable{ navigateToDetail(category) //the passed category is the 1st category named as the arbitrary var of type Category data class
                 },//whenever the Column is clicked the navController fun is called and is moved to the particular detail screen of which the category is passed
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(painter = rememberAsyncImagePainter(category.strCategoryThumb) , contentDescription =null , modifier = Modifier.aspectRatio(1f))//aspectRatio 1f is 1:1 image size
//rememberAysncImagePainter lets us show the image provided in the api stored in the data class "Category". its the composable imported by adding a dependency

        Text(
            text =category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight= FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}