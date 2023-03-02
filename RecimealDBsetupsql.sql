CREATE TABLE Recipes( 
	RecipeID INTEGER IDENTITY PRIMARY KEY NOT NULL,
	Title VARCHAR(127),
	Description VARCHAR(255),
	Instructions VARCHAR(4096),
	Notes VARCHAR(1023),
	Style VARCHAR(127),
	Type VARCHAR(127),
	UserCreated INTEGER,
	Favorited INTEGER,
	CONSTRAINT usr_crtd_bool CHECK (UserCreated IN (1,0)),
	CONSTRAINT Fvrtd CHECK (Favorited IN (1,0))
);

CREATE TABLE Ingredients(
	IngredientID INTEGER IDENTITY PRIMARY KEY NOT NULL,
	Title VARCHAR(127)
);

CREATE TABLE Contains(
	RecipeID INTEGER NOT NULL,
	IngredientID INTEGER NOT NULL,
	Amount FLOAT ,
	UnitOfMeasure VARCHAR(127),
	Preparation VARCHAR(127),
	PRIMARY KEY( RecipeID, IngredientID),
	FOREIGN KEY (RecipeID) REFERENCES Recipes(RecipeID) ON DELETE CASCADE,
	FOREIGN KEY (IngredientID) REFERENCES Ingredients(IngredientID) ON DELETE CASCADE
);


-- Spanish Rice and Beans
-- credit https://dishingouthealth.com/spanish-rice-and-beans-one-pot/

INSERT INTO Recipes(Title,Description,Instructions,Style,Type,UserCreated,Favorited) VALUES ('Spanish Rice and Beans','A flavorful vegetarian meal that can be made in one pot', 'Heat oil in a large skillet with a fitted lid over medium. Add onion; cook 5 minutes, until softened. Add garlic, paprika, salt, chili powder, oregano, black pepper, and cayenne; cook 2 minutes, stirring often, until aromatic. Stir in rice; cook 2 minutes, until slightly translucent. Stir in tomatoes, beans, and broth (or water). Bring mixture to a boil, reduce to medium-low, and simmer, covered, until liquid is absorbed and rice is tender, about 25 minutes.
Meanwhile, prepare parsley oil (if using) by combining parsley, lemon zest and juice, and olive oil in a small bowl; stir well.
Scatter olives over Spanish Beans and Rice and drizzle with parsley oil.','hispanic','main course',0,0);

INSERT INTO Ingredients(Title) VALUES ('extra-virgin olive oil');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (0, 0, 2, 'tablespoons');

INSERT INTO Ingredients(Title) VALUES ('yellow onion');
INSERT INTO Contains( RecipeID, IngredientID, Amount, Preparation) VALUES (0, 1, 1, 'finely chopped');

INSERT INTO Ingredients(Title) VALUES ('garlic cloves');
INSERT INTO Contains( RecipeID, IngredientID, Amount, Preparation) VALUES (0, 2, 3, 'minced');

INSERT INTO Ingredients(Title) VALUES ('paprika');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (0, 3, 1.5, 'teaspoons');

INSERT INTO Ingredients(Title) VALUES ('kosher salt');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (0, 4, 1.25, 'teaspoons');

INSERT INTO Ingredients(Title) VALUES ('chill powder');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (0, 5, 1, 'teaspoons');

INSERT INTO Ingredients(Title) VALUES ('dried oregano');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (0, 6, 1.25, 'teaspoons');

INSERT INTO Ingredients(Title) VALUES ('black pepper');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (0, 7, .5, 'teaspoons');

INSERT INTO Ingredients(Title) VALUES ('caynne pepper');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (0, 8, .25, 'teaspoons');

INSERT INTO Ingredients(Title) VALUES ('long-grain white rice');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (0, 9, 2, 'cups');

INSERT INTO Ingredients(Title) VALUES ('fire-roasted diced tomatoes');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (0, 10, 1, '14.5-oz. can');

INSERT INTO Ingredients(Title) VALUES ('kidney beans');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure, Preparation) VALUES (0, 11, 2, '15.5-oz. cans', 'drained and rinsed');

INSERT INTO Ingredients(Title) VALUES ('chicken broth');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (0, 12, 3, 'cups');

INSERT INTO Ingredients(Title) VALUES ('green olives');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure, Preparation) VALUES (0, 13, .33, 'cups','sliced');

-- Chicken Enchiladas
-- credit: https://www.foodnetwork.com/recipes/ree-drummond/simple-perfect-enchiladas-recipe0-2043114

INSERT INTO Recipes(Title,Description,Instructions,Style,Type,UserCreated,Favorited) VALUES ('Chicken Enchiladas','Easy to make recipe that is good for meal prep and freezing too!','Prep oven and enchilada sauce. Preheat oven to 350°F.  Prepare your enchilada sauce: Cook the roux and spices: Heat oil in a small saucepan over medium-high heat. Add flour and cook for 1 minute, whisking constantly.  Add in the chili powder, garlic powder, cumin and oregano and cook for 1 more minute, whisking constantly. Simmer: Gradually pour in the stock, whisking constantly to combine until no lumps remain. Continue cooking until the sauce reaches a simmer.  Then reduce heat to medium-low to maintain the simmer (the sauce should continue lightly bubbling) for about 10-15 minutes, uncovered, until the sauce has slightly thickened. Season: Give the sauce a taste and season with salt, as needed. (I typically add 1/2 teaspoon fine sea salt, but the saltiness of the sauce can vary depending on the brand of stock that you use.) Serve: Use immediately in your favorite recipe and enjoy! back to the enchilada: Sauté the filling mixture. In large sauté pan, heat oil over medium-high heat. Add onion and sauté for 3 minutes, stirring occasionally.  Add diced chicken and green chiles, and season with a generous pinch of salt and pepper.  Sauté the mixture for 6-8 minutes, stirring occasionally, or until the chicken is cooked through. Add in the beans and stir until evenly combined. Remove pan from heat and set aside. Assemble the enchiladas. To assemble the enchiladas, set up an assembly line including: tortillas, enchilada sauce, chicken mixture, and cheese. Lay out a tortilla, and spread two tablespoons of sauce over the surface of the tortilla.  Add a generous spoonful of the chicken mixture in a line down the center of the tortilla, then sprinkle with 1/3 cup cheese. Roll up tortilla and place in a greased 9 x 13-inch baking dish. Assemble the remaining enchiladas.  Then spread any remaining sauce evenly over the top of the enchiladas, followed by any extra cheese. Bake uncovered for 20 minutes, until the enchiladas are cooked through and the tortillas are slightly crispy on the outside.  Transfer the baking dish to a wire baking rack. Serve the enchiladas immediately while they’re nice and hot and melty, garnished with lots of fresh toppings. Enjoy!','hispanic','main course',0,0);

-- olive oil
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (1, 0, 4, 'tablespoons');

INSERT INTO Ingredients(Title) VALUES ('white onion');
INSERT INTO Contains( RecipeID, IngredientID, Amount, Preparation) VALUES (1, 14, 1, 'peeled and diced');

INSERT INTO Ingredients(Title) VALUES ('chicken breast');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure, Preparation) VALUES (1, 15, 1, 'pound','diced into small 1/2-inch pieces');

INSERT INTO Ingredients(Title) VALUES ('green chiles');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure, Preparation) VALUES (1, 16, 1, '4-ounce can','diced');

INSERT INTO Ingredients(Title) VALUES ('sea salt');
INSERT INTO Contains( RecipeID, IngredientID) VALUES (1, 17);

--black pepper
INSERT INTO Contains( RecipeID, IngredientID) VALUES (1, 7);

INSERT INTO Ingredients(Title) VALUES ('black beans');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure, Preparation) VALUES (1, 18, 1, '15-ounce can','drained and rinsed');

INSERT INTO Ingredients(Title) VALUES ('flour tortillas');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (1, 19, 8, 'large');

INSERT INTO Ingredients(Title) VALUES ('all-purpose flour');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (1, 20, 2, 'tablespoons');

--chili powder
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (1,5, .25, 'cup');

INSERT INTO Ingredients(Title) VALUES ('garlic powder');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (1, 21, .5, 'teaspoons');

INSERT INTO Ingredients(Title) VALUES ('cumin');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure,Preparation) VALUES (1, 22, .5, 'teaspoons','ground');

-- dried oregano
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (1, 6, .25, 'teaspoons');

-- chicken broth
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (1, 12, 2, 'cups');
