package comp3350.recimeal.presentation;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import comp3350.recimeal.business.AccessRecipes;
import comp3350.recimeal.business.SearchObjects;
import comp3350.recimeal.objects.Recipe;

public class CLI  // command-line interface
{
	public static BufferedReader console;
	public static String inputLine;
	public static String[] inputTokens;

	public static String studentNumber;
	public static String courseNumber;
	
	public static String indent = "  ";
	
	public static void run()
	{
		try
		{
			console = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("See all recipes with 'search', or filter with 'search <search term>'");
			System.out.println("View a recipe with 'get <recipe name>'");
			process();
			console.close();
		}
		catch (IOException ioe)
		{
			System.out.println(ioe.getMessage());
			ioe.printStackTrace();
		}
	}

	public static void process()
	{
		readLine();
		while ((inputLine != null) && (!inputLine.equalsIgnoreCase("exit"))
				&& (!inputLine.equalsIgnoreCase("quit"))
				&& (!inputLine.equalsIgnoreCase("q"))
				&& (!inputLine.equalsIgnoreCase("bye")))
		{	// use cntl-c or exit to exit
			inputTokens = inputLine.split("\\s+");
			parse();
			readLine();
		}
	}

	public static void readLine()
	{
		try
		{
			System.out.print(">");
			inputLine = console.readLine();
		}
		catch (IOException ioe)
		{
			System.out.println(ioe.getMessage());
			ioe.printStackTrace();
		}
	}

	public static void parse()
	{
		if (inputTokens[0].equalsIgnoreCase("get"))
		{
			processGet();
		}
		else if (inputTokens[0].equalsIgnoreCase("search"))
		{
			processSearch();
		}
		else
		{
			System.out.println("Invalid command.");
		}
	}

	public static void processSearch()
	{
		AccessRecipes accessRecipes = new AccessRecipes();
		SearchObjects searchObjects = new SearchObjects(accessRecipes);
		List<Recipe> searchList;
		if(inputTokens.length>1)
			searchList = searchObjects.getSearchedRecipes( inputTokens[1],false,false,false);
		else
			searchList = searchObjects.getSearchedRecipes( "",false,false,false);

		//print all recipe names
		for(int i=0;i<searchList.size();i++)
		{
			System.out.println(searchList.get(i).getRecipeName());
			System.out.println(indent+searchList.get(i).getRecipeDescription());
		}
	}

	public static void processGet()
	{
		AccessRecipes accessRecipes = new AccessRecipes();
		SearchObjects searchObjects = new SearchObjects(accessRecipes);
		if(inputTokens.length>1)
		{
			List<Recipe> list = searchObjects.getSearchedRecipes(inputTokens[1],false,false,false);
			if(list.size()>0) {
				Recipe got = list.get(0);
				//show the recipe info
				System.out.println("==============================");
				System.out.println(got.getRecipeName());
				System.out.println("==============================");
				System.out.println(got.getRecipeDescription());
				System.out.println("------------------------------");
				System.out.println(got.getRecipeInstruction());
				System.out.println("==============================");
			}
			else
				System.out.println("No recipes contain search term "+inputTokens[1]);
		}
		else
		{
			System.out.println("Invalid input. Please use as 'get <Recipe Name>'");
		}
	}
}