###COMP3350 - Group 13 - Iteration 1 Architecture

#Recimeal Architecture

The application was based upon the sample project provided [here](https://code.cs.umanitoba.ca/comp3350-winter2023/sample) (which is the sample project present in the Iteration 1 description).

###Packages

The packages containing the code for Recimeal are broken into the following sections:
- Application
- Business (comp3350.recimeal.business)
- Objects (comp3350.recimeal.objects)
- Persistance (comp3350.recimeal.persistance)
- Presentation (comp3350.recimeal.presentation)
- Tests (comp3350.recimeal.tests)

###Application Layer
The application layer contains classes needed to start running the app, including **main** and **RecipeServices**, which referances to retrieve persistant data (which currently instead fetches for the stub database).

###Business Layer (Logic Layer)
Contains the **AccessRecipes** class, which handles interaction with the Recipe class.  It has a list of recipes, which can be added to, 

###Objects (Domain Specific Objects)
**Recipe** class holds information about a recipe, including name, instruction and map of ingredients.  It has a method to add ingredients and retrieve its own attributes.
**Ingredient** is a class with name and amount for an ingredient on the recipe.

###Persistance (Data Layer)
**RecipePersistance** is a stub class that allows insertion, deletion and updating for a stub database.  It keeps this data in an ArrayList of *Recipe*.  This list serves as the list of recipes the user can browse through and view.

###Presentation (View/UI Layer)
Contains classes for the visual GUI of the activities.

##Diagram
Please view ARCHITECHTURE_iter1_diagram.png in the root directory.
