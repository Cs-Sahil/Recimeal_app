### COMP3350 - Group 13 - Iteration 2 Architecture

# Recimeal Architecture

The application was based upon the sample project provided [here](https://code.cs.umanitoba.ca/comp3350-winter2023/sample) (which is the sample project present in the Iteration 1 description).

### Packages

The packages containing the code for Recimeal are broken into the following sections:
- Application
- Business (comp3350.recimeal.business)
- Objects (comp3350.recimeal.objects)
- Persistance (comp3350.recimeal.persistance)
- Presentation (comp3350.recimeal.presentation)
- Tests (comp3350.recimeal.tests)

### Application Layer
The application layer contains classes needed to start running the app, including **main** and **Services**, which referances to retrieve persistant data.

### Business Layer (Logic Layer)
The **AccessRecipes** class handles interaction with the Recipe class like searching.
The **AccessIngredients** class is similar to AccessRecipes but for Ingredients.
The **CreateRecipes** class formats and sends to the Data Layer to add a recipe to the Database.
The **VerifyRecipes** class checks if input for creating a recipe is valid.
### Objects (Domain Specific Objects)
**Recipe** class holds information about a recipe, including name, instruction and map of ingredients.  It has a method to add ingredients and retrieve its own attributes.
**Ingredient** is a class with name and unit of measurement for an ingredient on the recipe.

### Persistance (Data Layer)
**RecipePersistance** is a stub class that allows insertion, deletion and updating for a stub database.  It keeps this data in an ArrayList of *Recipe*.  This list serves as the list of recipes the user can browse through and view.

### Presentation (View/UI Layer)
Contains classes for the visual GUI of the activities.
**MainActivity** is the main screen where the user can scroll through recipes in the database, and search for recipes.
**CreateActivity** is the activity for filling out a form to create a new recipe.
**RecipeActivity** is where a selected recipe is displayed, with all its details.

## Diagram
![Diagram for Iteration 2 Architechture](/ARCHITECHTURE_iter2.png "Architechture Diagram")
