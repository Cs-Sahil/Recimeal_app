package comp3350.recimeal.persistence.stubs;

import comp3350.recimeal.objects.Ingredient;
import comp3350.recimeal.objects.PermanentRecipe;
import comp3350.recimeal.objects.Recipe;
import comp3350.recimeal.persistence.RecipePersistence;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class RecipePersistenceStub implements RecipePersistence{
    private List<Recipe> recipes;

    private final String friedRiceInstructions = "Add the cooked rice to the pan and stir until well combined with the vegetables and eggs.\n"
            + "Cook for an additional 2-3 minutes, or until the rice is heated through.\n";
    private final String omeleteIntructions = "Whisk 2 eggs, add fillings, cook both sides in pan. Serve hot.";
    private final String brownieInstructions = "Mix melted chocolate and butter. Add sugar, eggs, and flour. Bake and serve.";
    private final String muffinInstructions =
                    "- Heat oven to 400 degrees fahrenheit.\n"+
                    "- Whisk flour, sugar, baking powder, and salt in a large bowl.\n"+
                    "- Add oil to a measuring jug that holds at least 1 cup.\n"+
                    "- Add the egg then fill the jug to the 1-cup line with milk (1/3 to 1/2 cup milk).\n"+
                    "- Add vanilla and whisk to combine.\n"+
                    "- Add milk mixture to the bowl with dry ingredients then use a fork to combine.\n"+
                    "- Fold in the blueberries.\n"+
                    "- Divide batter between muffin cups.\n"+
                    "- Bake 15-20 minutes";

    public RecipePersistenceStub() {
        this.recipes = new ArrayList<>();
        /*
        recipes.add(new Recipe(1, "Fried Rice",friedRiceInstructions,));
        recipes.add(new Recipe(2,"Omelette",omeleteIntructions));
        recipes.add(new Recipe(3, "Brownies", brownieInstructions));
        recipes.add(new Recipe(4, "Blueberry Muffins",muffinInstructions,"Simple, fluffy and tasty!"));
        recipes.get(0).addIngred(1, 3);
        recipes.get(0).addIngred(2, 12);
        recipes.get(1).addIngred(3, 4);
        recipes.get(1).addIngred(4, 7);
        recipes.get(1).addIngred(5, 16);
        recipes.get(2).addIngred(6, 100);
        recipes.get(3).addIngred(7, 12);
        recipes.get(3).addIngred(8, 24);
         */
        recipes.add(new PermanentRecipe(0, "Spanish Rice and Beans","A flavorful vegetarian meal that can be made in one pot", "Heat oil in a large skillet with a fitted lid over medium. Add onion; cook 5 minutes, until softened. Add garlic, paprika, salt, chili powder, oregano, black pepper, and cayenne; cook 2 minutes, stirring often, until aromatic. Stir in rice; cook 2 minutes, until slightly translucent. Stir in tomatoes, beans, and broth (or water). Bring mixture to a boil, reduce to medium-low, and simmer, covered, until liquid is absorbed and rice is tender, about 25 minutes. Meanwhile, prepare parsley oil (if using) by combining parsley, lemon zest and juice, and olive oil in a small bowl; stir well. Scatter olives over Spanish Beans and Rice and drizzle with parsley oil.","hispanic","Main Course",false,false,null));
        recipes.add( new PermanentRecipe(1,"Chicken Enchiladas","Easy to make recipe that is good for meal prep and freezing too!","Prep oven and enchilada sauce. Preheat oven to 350°F.  Prepare your enchilada sauce: Cook the roux and spices: Heat oil in a small saucepan over medium-high heat. Add flour and cook for 1 minute, whisking constantly.  Add in the chili powder, garlic powder, cumin and oregano and cook for 1 more minute, whisking constantly. Simmer: Gradually pour in the stock, whisking constantly to combine until no lumps remain. Continue cooking until the sauce reaches a simmer.  Then reduce heat to medium-low to maintain the simmer (the sauce should continue lightly bubbling) for about 10-15 minutes, uncovered, until the sauce has slightly thickened. Season: Give the sauce a taste and season with salt, as needed. (I typically add 1/2 teaspoon fine sea salt, but the saltiness of the sauce can vary depending on the brand of stock that you use.) Serve: Use immediately in your favorite recipe and enjoy! back to the enchilada: Sauté the filling mixture. In large sauté pan, heat oil over medium-high heat. Add onion and sauté for 3 minutes, stirring occasionally.  Add diced chicken and green chiles, and season with a generous pinch of salt and pepper.  Sauté the mixture for 6-8 minutes, stirring occasionally, or until the chicken is cooked through. Add in the beans and stir until evenly combined. Remove pan from heat and set aside. Assemble the enchiladas. To assemble the enchiladas, set up an assembly line including: tortillas, enchilada sauce, chicken mixture, and cheese. Lay out a tortilla, and spread two tablespoons of sauce over the surface of the tortilla.  Add a generous spoonful of the chicken mixture in a line down the center of the tortilla, then sprinkle with 1/3 cup cheese. Roll up tortilla and place in a greased 9 x 13-inch baking dish. Assemble the remaining enchiladas.  Then spread any remaining sauce evenly over the top of the enchiladas, followed by any extra cheese. Bake uncovered for 20 minutes, until the enchiladas are cooked through and the tortillas are slightly crispy on the outside.  Transfer the baking dish to a wire baking rack. Serve the enchiladas immediately while they’re nice and hot and melty, garnished with lots of fresh toppings. Enjoy!","hispanic","Main Course",false,false,null));
        recipes.add( new PermanentRecipe( 2 , "Pasta Puttanesca","Pasta puttanesca is an Italian pasta dish that typically includes spaghetti, tomatoes, garlic, anchovies, capers, olives, and red pepper flakes.","Bring a large pot of salted water to a boil. Add the spaghetti and cook until al dente, about 8 to 10 minutes. While the pasta is cooking, heat the olive oil in a large skillet over medium heat. Add the garlic, anchovies, and red pepper flakes and cook, stirring occasionally, until the garlic is fragrant, and the anchovies have melted into the oil, about 2 to 3 minutes. Add the crushed tomatoes, olives, and capers to the skillet and bring the sauce to a simmer. Cook, stirring occasionally, until the sauce has thickened slightly, about 10 to 15 minutes. Season the sauce with salt and freshly ground black pepper to taste. Drain the cooked pasta and add it to the skillet with the sauce. Toss the pasta with the sauce until it is evenly coated. Serve the pasta hot, garnished with fresh parsley if desired. Enjoy!","straightforward and user-friendly","Main Course",false,false,null) );
        recipes.add( new PermanentRecipe( 3 , "Blueberry Cheesecake", "The combination of the smooth, creamy cheesecake filling with the burst of juicy blueberries is what makes this dessert so irresistible","Preheat your oven to 350°F (175°C) and lightly grease a 9-inch springform pan. In a medium bowl, combine the graham cracker crumbs, 1/4 cup of sugar, and melted butter. Mix well and press the mixture onto the bottom of the prepared pan. In a large bowl, beat the cream cheese, 1 cup of sugar, and vanilla extract until smooth and creamy. Add the eggs, one at a time, and beat well after each addition. Pour the cream cheese mixture over the prepared crust and smooth the top with a spatula. In a small saucepan, combine the blueberries, 1/2 cup of sugar, cornstarch, and lemon juice. Cook over medium heat, stirring constantly, until the mixture thickens and becomes a glossy sauce. Pour the blueberry topping over the cheesecake and spread it evenly. Bake the cheesecake in the preheated oven for 45 to 50 minutes, or until the center is almost set. Remove the cheesecake from the oven and let it cool to room temperature. Refrigerate the cheesecake for at least 4 hours, or overnight, before serving. Once the cheesecake is chilled and set, run a knife around the edge of the pan to loosen the cheesecake.","clear","Dessert",false,false,null));
        recipes.add(new PermanentRecipe(4,"Grilled salmon","Grilled salmon with a citrus glaze, served with quinoa and roasted asparagus","Preheat your grill to medium-high heat. In a small bowl, whisk together the lemon juice, orange juice, honey, olive oil, garlic, salt, and pepper.  Brush the salmon fillets with the citrus glaze, making sure they are well coated. Place the salmon fillets on the grill, skin-side down, and cook for 5-6 minutes. Flip the salmon fillets and brush with more of the citrus glaze. Cook for an additional 4-5 minutes, or until the salmon is cooked through and flaky. While the salmon is cooking, rinse the quinoa in a fine mesh strainer under running water. Add the quinoa and water or chicken broth to a medium saucepan, and bring to a boil over high heat. Reduce the heat to low, cover, and simmer for 15-20 minutes, or until the quinoa is tender and the liquid is absorbed. Season with salt to taste. Preheat your oven to 425°F (218°C). Arrange the trimmed asparagus on a baking sheet, and drizzle with olive oil. Sprinkle with salt and pepper, and toss to coat. Roast the asparagus in the preheated oven for 12-15 minutes, or until tender and lightly browned. Serve the grilled salmon fillets with the quinoa and roasted asparagus. Drizzle with any remaining citrus glaze, and garnish with fresh herbs, if desired. Enjoy!","High-protein","Main Course",false,false, null));
        recipes.add(new PermanentRecipe(5, "Spinach and ricotta stuffed shells with a side salad and garlic bread","This meal is perfect for a cozy night in or a casual dinner party, and is sure to satisfy your cravings for comfort food","Preheat your oven to 375°F (190°C). Cook the pasta shells according to the package instructions until they are al dente. Drain the shells and rinse them with cold water to cool them down. Heat the olive oil in a large skillet over medium heat. Add the minced garlic and cook for 1-2 minutes, or until fragrant. Add the spinach to the skillet, and cook for 3-4 minutes, or until heated through. In a large mixing bowl, combine the ricotta cheese, 1/2 cup of the Parmesan cheese, chopped basil, salt, pepper, and egg. Mix well. Stir the spinach mixture into the cheese mixture until fully combined. Spoon the cheese and spinach mixture into the cooked pasta shells, and arrange them in a 9x13-inch baking dish. Pour the marinara sauce over the stuffed shells, and sprinkle the remaining 1/2 cup of Parmesan cheese on top. Bake the stuffed shells in the preheated oven for 25-30 minutes, or until the cheese is melted and bubbly. While the stuffed shells are baking, make the side salad by combining the salad greens, cherry tomatoes, sliced cucumber, red onion, and feta cheese in a large mixing bowl. Drizzle with balsamic vinaigrette, and toss to combine. For the garlic bread, slice the French bread into thick slices, and spread each slice with softened butter. Sprinkle the minced garlic, grated Parmesan cheese, and chopped parsley on top. Broil the garlic bread in the oven for 2-3 minutes, or until the cheese is melted and bubbly. Serve the spinach and ricotta stuffed shells hot, with a side salad and garlic bread. Enjoy!","healthy","Main course",false,false,null));
    }

    @Override
    //getRecipeSequential() return the all the recipes store in the list
    public List<Recipe> getRecipeSequential() {
        return Collections.unmodifiableList(recipes);
    }

    @Override
    public Recipe getRecipeById(int id) {
        for(Recipe recipe: recipes){
            if(recipe.getRecipeId() == id)
                return recipe;
        }
        return null;
    }

    @Override
    // insertRecipe() adds a new recipe in list
    public int insertRecipe(Recipe newRecipe) {
        recipes.add(newRecipe);
        return newRecipe.getRecipeId();
    }

    @Override
    // updateRecipe(Recipe currRecipe) -> updates a recipe using an ID of that recipe
    public Recipe updateRecipe(Recipe currRecipe) {
        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);
                 if (recipe.equals(currRecipe)) {
                recipes.set(i, currRecipe);
                return currRecipe;
            }
        }
        return null;
    }

    @Override
    //deleteRecipe(Recipe discardRecipe) -> delete recipe using ID of that recipe
    public void deleteRecipe(Recipe discardRecipe) {
        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);
            if (recipe.equals(discardRecipe)) {
                recipes.remove(i);
                break;
            }
        }
    }

    @Override
    public List<Integer> groceryRecipes() {
        return null;
    }

    @Override
    public void addToGrocery(int RecipeID) {

    }
}
