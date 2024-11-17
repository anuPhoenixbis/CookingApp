package com.example.cookingapp
//This class holds the route name for the 2 UI Screens
sealed class NavigatorRoute (val route : String// to take inputs of the routes' names
 ){/*Sealed classes and interfaces provide controlled inheritance of
 your class hierarchies. All direct subclasses of a sealed class are known at compile time.
 No other subclasses may appear outside the module and package within which the sealed class is
  defined.
  Direct subclasses are classes that immediately inherit from their superclass.

Indirect subclasses are classes that inherit from more than one level down from their superclass.

Sealed classes are best used for scenarios when:

1.Limited class inheritance is desired: You have a predefined, finite set of subclasses that extend a class, all of which are known at compile time.

2.Type-safe design is required: Safety and pattern matching are crucial in your project. Particularly for state management or handling complex conditional logic. For an example, check out Use sealed classes with when expressions.

3.Working with closed APIs: You want robust and maintainable public APIs for libraries that ensure that third-party clients use the APIs as intended.*/
    object RecipeScreen : NavigatorRoute("recipeScreen")//The object keyword in Kotlin is used to declare singleton classes. A singleton class is a class that can only have one instance. This is useful for classes that represent a single entity, such as a database connection or a logger.
    object DetailScreen: NavigatorRoute("detailScreen")//2 different objects(rather than vars to pass in the ,actual starting point, RecipeScreen Composable passed in the MainActivity. ) : one for Category selecting screen and other for the details of the Recipe Screen
    //Storing these objects in their respective route names to use them whenever required.
}