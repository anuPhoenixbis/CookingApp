package com.example.cookingapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// we are arranging all the items in form of the items stored in the json file
// we write the variables' names exactly as mentioned in the json file for easier access

@Parcelize
data class Category(// we are accepting the item details of each item in this data class
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription:String
):Parcelable

// we are storing the all the items whose details were stored in the previous data class into this data class
data class CategoriesResponse(val categories : List<Category>)


//Category was transferred from one screen to another so, in order to keep the data safe . This data
//class is parcelized and whenever the data was required it was deseiralized to get the data back in
//its original form.In order to transfer objects from one screen to another we need to serialize them,
//to make them like parcels.It converts an object into a string which can be carried pretty easily.