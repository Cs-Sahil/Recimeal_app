CREATE TABLE Recipes( 
	RecipeID INTEGER IDENTITY PRIMARY KEY NOT NULL,
	Title VARCHAR(128),
	Description VARCHAR(256),
	Instructions VARCHAR(4096),
	Notes VARCHAR(1024),
	Style VARCHAR(128),
	Type VARCHAR(128),
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
	PRIMARY KEY( RecipeID, IngredientID),
	FOREIGN KEY (RecipeID) REFERENCES Recipes(RecipeID) ON DELETE CASCADE,
	FOREIGN KEY (IngredientID) REFERENCES Ingredients(IngredientID) ON DELETE CASCADE
);

CREATE TABLE Grocery_List(
	RecipeID INTEGER NOT NULL,
	PRIMARY KEY(RecipeID),
	FOREIGN KEY (RecipeID) REFERENCES Recipes(RecipeID) ON DELETE CASCADE
);


-- Spanish Rice and Beans Recipe Id(0)
-- credit https://dishingouthealth.com/spanish-rice-and-beans-one-pot/

INSERT INTO Recipes(Title,Description,Instructions,Style,Type,UserCreated,Favorited) VALUES ('Spanish Rice and Beans','A flavorful vegetarian meal that can be made in one pot', 'Heat oil in a large skillet with a fitted lid over medium. Add onion; cook 5 minutes, until softened. Add garlic, paprika, salt, chili powder, oregano, black pepper, and cayenne; cook 2 minutes, stirring often, until aromatic. Stir in rice; cook 2 minutes, until slightly translucent. Stir in tomatoes, beans, and broth (or water). Bring mixture to a boil, reduce to medium-low, and simmer, covered, until liquid is absorbed and rice is tender, about 25 minutes.
Meanwhile, prepare parsley oil (if using) by combining parsley, lemon zest and juice, and olive oil in a small bowl; stir well.
Scatter olives over Spanish Beans and Rice and drizzle with parsley oil.','hispanic','Main Course',0,0);

INSERT INTO Ingredients(Title) VALUES ('extra-virgin olive oil');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (0, 0, 2, 'tablespoons');

INSERT INTO Ingredients(Title) VALUES ('yellow onion');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (0, 1, 1, 'finely chopped');

INSERT INTO Ingredients(Title) VALUES ('garlic cloves');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (0, 2, 3, 'minced');

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
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (0, 11, 2, '15.5-oz. cans drained and rinsed');

INSERT INTO Ingredients(Title) VALUES ('chicken broth');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (0, 12, 3, 'cups');

INSERT INTO Ingredients(Title) VALUES ('green olives');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (0, 13, .33, 'cups sliced');

-- Chicken Enchiladas Recipe Id (1)
-- credit: https://www.foodnetwork.com/recipes/ree-drummond/simple-perfect-enchiladas-recipe0-2043114

INSERT INTO Recipes(Title,Description,Instructions,Style,Type,UserCreated,Favorited) VALUES ('Chicken Enchiladas','Easy to make recipe that is good for meal prep and freezing too!','Prep oven and enchilada sauce. Preheat oven to 350°F.  Prepare your enchilada sauce: Cook the roux and spices: Heat oil in a small saucepan over medium-high heat. Add flour and cook for 1 minute, whisking constantly.  Add in the chili powder, garlic powder, cumin and oregano and cook for 1 more minute, whisking constantly. Simmer: Gradually pour in the stock, whisking constantly to combine until no lumps remain. Continue cooking until the sauce reaches a simmer.  Then reduce heat to medium-low to maintain the simmer (the sauce should continue lightly bubbling) for about 10-15 minutes, uncovered, until the sauce has slightly thickened. Season: Give the sauce a taste and season with salt, as needed. (I typically add 1/2 teaspoon fine sea salt, but the saltiness of the sauce can vary depending on the brand of stock that you use.) Serve: Use immediately in your favorite recipe and enjoy! back to the enchilada: Sauté the filling mixture. In large sauté pan, heat oil over medium-high heat. Add onion and sauté for 3 minutes, stirring occasionally.  Add diced chicken and green chiles, and season with a generous pinch of salt and pepper.  Sauté the mixture for 6-8 minutes, stirring occasionally, or until the chicken is cooked through. Add in the beans and stir until evenly combined. Remove pan from heat and set aside. Assemble the enchiladas. To assemble the enchiladas, set up an assembly line including: tortillas, enchilada sauce, chicken mixture, and cheese. Lay out a tortilla, and spread two tablespoons of sauce over the surface of the tortilla.  Add a generous spoonful of the chicken mixture in a line down the center of the tortilla, then sprinkle with 1/3 cup cheese. Roll up tortilla and place in a greased 9 x 13-inch baking dish. Assemble the remaining enchiladas.  Then spread any remaining sauce evenly over the top of the enchiladas, followed by any extra cheese. Bake uncovered for 20 minutes, until the enchiladas are cooked through and the tortillas are slightly crispy on the outside.  Transfer the baking dish to a wire baking rack. Serve the enchiladas immediately while they’re nice and hot and melty, garnished with lots of fresh toppings. Enjoy!','hispanic','Main Course',0,0);

-- olive oil
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (1, 0, 4, 'tablespoons');

INSERT INTO Ingredients(Title) VALUES ('white onion');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (1, 14, 1, 'peeled and diced');

INSERT INTO Ingredients(Title) VALUES ('chicken breast');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (1, 15, 1, 'pound diced into small 1/2-inch pieces');

INSERT INTO Ingredients(Title) VALUES ('green chiles');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (1, 16, 1, '4-ounce can diced');

INSERT INTO Ingredients(Title) VALUES ('sea salt');
INSERT INTO Contains( RecipeID, IngredientID) VALUES (1, 17);

--black pepper
INSERT INTO Contains( RecipeID, IngredientID) VALUES (1, 7);

INSERT INTO Ingredients(Title) VALUES ('black beans');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (1, 18, 1, '15-ounce can drained and rinsed');

INSERT INTO Ingredients(Title) VALUES ('flour tortillas');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (1, 19, 8, 'large');

INSERT INTO Ingredients(Title) VALUES ('all-purpose flour');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (1, 20, 2, 'tablespoons');

--chili powder
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (1,5, .25, 'cup');

INSERT INTO Ingredients(Title) VALUES ('garlic powder');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (1, 21, .5, 'teaspoons');

INSERT INTO Ingredients(Title) VALUES ('cumin');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (1, 22, .5, 'teaspoons ground');

-- dried oregano
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (1, 6, .25, 'teaspoons');

-- chicken broth
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (1, 12, 2, 'cups');


-- Pasta Puttanesca (recipe ID 2)

INSERT INTO Recipes(Title,Description,Instructions,Style,Type,UserCreated,Favorited) VALUES ('Pasta Puttanesca','Pasta puttanesca is an Italian pasta dish that typically includes spaghetti, tomatoes, garlic, anchovies, capers, olives, and red pepper flakes.','Bring a large pot of salted water to a boil. Add the spaghetti and cook until al dente, about 8 to 10 minutes. While the pasta is cooking, heat the olive oil in a large skillet over medium heat. Add the garlic, anchovies, and red pepper flakes and cook, stirring occasionally, until the garlic is fragrant, and the anchovies have melted into the oil, about 2 to 3 minutes. Add the crushed tomatoes, olives, and capers to the skillet and bring the sauce to a simmer. Cook, stirring occasionally, until the sauce has thickened slightly, about 10 to 15 minutes. Season the sauce with salt and freshly ground black pepper to taste. Drain the cooked pasta and add it to the skillet with the sauce. Toss the pasta with the sauce until it is evenly coated. Serve the pasta hot, garnished with fresh parsley if desired. Enjoy!','straightforward and user-friendly','Main Course',0,0 );

-- olive oil
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (2, 0, 4, 'tablespoons');

INSERT INTO Ingredients(Title) VALUES ('butter');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (2, 23, 1/8, 'cup');

-- garlic cloves
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (2, 2, 2, 'minced');


INSERT INTO Ingredients(Title) VALUES ('anchovy fillets');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (2, 24, 2, 'Sliced');


INSERT INTO Ingredients(Title) VALUES ('tomatoes puree');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (2, 25, 1 , 'can');


INSERT INTO Ingredients(Title) VALUES ('black olives');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (2, 26, 1/2 , 'cup');

INSERT INTO Ingredients(Title) VALUES ('capers');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (2, 27, 1/2 , 'cup');

INSERT INTO Ingredients(Title) VALUES ('spaghetti');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (2, 28, 1 , 'lb');

INSERT INTO Ingredients(Title) VALUES ('parmesan cheese');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (2, 29, 1 , 'packet');


---Desert Cake recipe id 3

INSERT INTO Recipes(Title,Description,Instructions,Style,Type,UserCreated,Favorited) VALUES ('Blueberry Cheesecake', 'The combination of the smooth, creamy cheesecake filling with the burst of juicy blueberries is what makes this dessert so irresistible','Preheat your oven to 350°F (175°C) and lightly grease a 9-inch springform pan. In a medium bowl, combine the graham cracker crumbs, 1/4 cup of sugar, and melted butter. Mix well and press the mixture onto the bottom of the prepared pan. In a large bowl, beat the cream cheese, 1 cup of sugar, and vanilla extract until smooth and creamy. Add the eggs, one at a time, and beat well after each addition. Pour the cream cheese mixture over the prepared crust and smooth the top with a spatula. In a small saucepan, combine the blueberries, 1/2 cup of sugar, cornstarch, and lemon juice. Cook over medium heat, stirring constantly, until the mixture thickens and becomes a glossy sauce. Pour the blueberry topping over the cheesecake and spread it evenly. Bake the cheesecake in the preheated oven for 45 to 50 minutes, or until the center is almost set. Remove the cheesecake from the oven and let it cool to room temperature. Refrigerate the cheesecake for at least 4 hours, or overnight, before serving. Once the cheesecake is chilled and set, run a knife around the edge of the pan to loosen the cheesecake.','clear','Dessert',0,0);

INSERT INTO Ingredients(Title) VALUES ('digestive biscuit');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (3, 30, 8, 'crumbs');

---butter
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (3, 23, 6, 'tablespoon');


INSERT INTO Ingredients(Title) VALUES ('cream cheese');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (3, 31, 600, 'grams');

INSERT INTO Ingredients(Title) VALUES ('sugar');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (3, 32, 5/2, 'cups');

INSERT INTO Ingredients(Title) VALUES ('eggs');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (3, 33, 2, ' white whole eggs');


INSERT INTO Ingredients(Title) VALUES ('sour cream');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (3, 34, 1, 'small pot');

INSERT INTO Ingredients(Title) VALUES ('vanilla extract');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (3, 35, 1, 'tablespoon');

INSERT INTO Ingredients(Title) VALUES ('blueberries');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (3, 36, 300, 'grams');

INSERT INTO Ingredients(Title) VALUES ('corn starch');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (3, 37, 1, 'tablespoon');


INSERT INTO Ingredients(Title) VALUES ('lemon juice');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (3, 38, 1, 'tablespoon');

---Recipe Id 4 Grilled salmon

INSERT INTO Recipes(Title,Description,Instructions,Style,Type,UserCreated,Favorited) VALUES ('Grilled salmon','Grilled salmon with a citrus glaze, served with quinoa and roasted asparagus','Preheat your grill to medium-high heat. In a small bowl, whisk together the lemon juice, orange juice, honey, olive oil, garlic, salt, and pepper.  Brush the salmon fillets with the citrus glaze, making sure they are well coated. Place the salmon fillets on the grill, skin-side down, and cook for 5-6 minutes. Flip the salmon fillets and brush with more of the citrus glaze. Cook for an additional 4-5 minutes, or until the salmon is cooked through and flaky. While the salmon is cooking, rinse the quinoa in a fine mesh strainer under running water. Add the quinoa and water or chicken broth to a medium saucepan, and bring to a boil over high heat. Reduce the heat to low, cover, and simmer for 15-20 minutes, or until the quinoa is tender and the liquid is absorbed. Season with salt to taste. Preheat your oven to 425°F (218°C). Arrange the trimmed asparagus on a baking sheet, and drizzle with olive oil. Sprinkle with salt and pepper, and toss to coat. Roast the asparagus in the preheated oven for 12-15 minutes, or until tender and lightly browned. Serve the grilled salmon fillets with the quinoa and roasted asparagus. Drizzle with any remaining citrus glaze, and garnish with fresh herbs, if desired. Enjoy!','High-protein','Main Course',0,0);

INSERT INTO Ingredients(Title) VALUES ('salmon fillets');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (4, 39, 4, 'pieces');

--lemon juice
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (4, 38, 1/4, 'cup');


INSERT INTO Ingredients(Title) VALUES ('orange juice');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (4, 40, 1/4, 'cup');


INSERT INTO Ingredients(Title) VALUES ('honey');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (4, 41, 2, 'tablespoons');

--olive oil
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (4, 0, 2, 'tablespoons');

--garlic cloves
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (4, 2, 3, 'minced');

--sea salt
INSERT INTO Contains( RecipeID, IngredientID) VALUES (4, 17);

--black pepper
INSERT INTO Contains( RecipeID, IngredientID) VALUES (4, 7);


INSERT INTO Ingredients(Title) VALUES ('quinoa');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (4, 42, 1, 'cup');

--chicken broth
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (4, 12, 2, 'cups');


INSERT INTO Ingredients(Title) VALUES ('asparagus');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (4, 43, 1, 'pound trimmed');


--- recipe Id 5
--Spinach and ricotta stuffed shells with a side salad and garlic bread

INSERT INTO Recipes(Title,Description,Instructions,Style,Type,UserCreated, Favorited) VALUES('Spinach and ricotta stuffed shells with a side salad and garlic bread','This meal is perfect for a cozy night in or a casual dinner party, and is sure to satisfy your cravings for comfort food','Preheat your oven to 375°F (190°C). Cook the pasta shells according to the package instructions until they are al dente. Drain the shells and rinse them with cold water to cool them down. Heat the olive oil in a large skillet over medium heat. Add the minced garlic and cook for 1-2 minutes, or until fragrant. Add the spinach to the skillet, and cook for 3-4 minutes, or until heated through. In a large mixing bowl, combine the ricotta cheese, 1/2 cup of the Parmesan cheese, chopped basil, salt, pepper, and egg. Mix well. Stir the spinach mixture into the cheese mixture until fully combined. Spoon the cheese and spinach mixture into the cooked pasta shells, and arrange them in a 9x13-inch baking dish. Pour the marinara sauce over the stuffed shells, and sprinkle the remaining 1/2 cup of Parmesan cheese on top. Bake the stuffed shells in the preheated oven for 25-30 minutes, or until the cheese is melted and bubbly. While the stuffed shells are baking, make the side salad by combining the salad greens, cherry tomatoes, sliced cucumber, red onion, and feta cheese in a large mixing bowl. Drizzle with balsamic vinaigrette, and toss to combine. For the garlic bread, slice the French bread into thick slices, and spread each slice with softened butter. Sprinkle the minced garlic, grated Parmesan cheese, and chopped parsley on top. Broil the garlic bread in the oven for 2-3 minutes, or until the cheese is melted and bubbly. Serve the spinach and ricotta stuffed shells hot, with a side salad and garlic bread. Enjoy!','healthy','Main course',0,0);

INSERT INTO Ingredients(Title) VALUES ('jumbo pasta shells');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (5, 44, 20, 'shells');


--olive oil
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (5, 0, 1, 'tablespoons');


--garlic cloves
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (5, 2, 3, 'minced');

INSERT INTO Ingredients(Title) VALUES ('frozen spinach');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (5, 45, 10, 'ounce');


INSERT INTO Ingredients(Title) VALUES ('ricotta cheese');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (5, 46, 15, 'ounce');


INSERT INTO Ingredients(Title) VALUES ('ricotta cheese');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (5, 47, 15, 'ounce');

INSERT INTO Ingredients(Title) VALUES ('grated Parmesan cheese');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (5, 48, 1, 'cup');

INSERT INTO Ingredients(Title) VALUES ('chopped fresh basil');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (5, 49, 1/4, 'cup');

--sea salt
INSERT INTO Contains( RecipeID, IngredientID) VALUES (5, 17);

--black pepper
INSERT INTO Contains( RecipeID, IngredientID) VALUES (5, 7);


--egg
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (5, 33, 1, 'white whole eggs');

INSERT INTO Ingredients(Title) VALUES ('marinara sauce');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (5, 50, 2, 'cup');

INSERT INTO Ingredients(Title) VALUES ('mixed salad greens');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (5, 51, 4, 'cup');


INSERT INTO Ingredients(Title) VALUES ('cherry tomatoes');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (5, 52, 2, 'cup');


INSERT INTO Ingredients(Title) VALUES ('cucumber');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (5, 53, 1, 'sliced');

INSERT INTO Ingredients(Title) VALUES ('red onion');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (5, 54, 1/4, 'cup sliced');


INSERT INTO Ingredients(Title) VALUES ('crumbled feta cheese ');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (5, 55, 1/4, 'cup');


INSERT INTO Ingredients(Title) VALUES ('balsamic vinaigrette ');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (5, 56, 2, 'tablespoons');


INSERT INTO Ingredients(Title) VALUES ('french bread ');
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (5, 57, 1, 'loaf');

--butter
INSERT INTO Contains( RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (5, 23, 1/2, 'cup');

