package igentuman.ncsteamadditions.crafttweaker;

import com.google.common.collect.Lists;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import igentuman.ncsteamadditions.recipes.NCSteamAdditionsRecipes;
import nc.integration.crafttweaker.CTAddRecipe;
import nc.integration.crafttweaker.CTClearRecipes;
import nc.integration.crafttweaker.CTRemoveRecipe;
import nc.recipe.IngredientSorption;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

public class SteamAdditionsCraftTweaker
{
	@ZenClass("mods.ncsteamadditions.steam_transformer")
	@ZenRegister
	public static class SteamTransformerHandler 
	{
		
		@ZenMethod
		public static void addRecipe(IIngredient input1,IIngredient input2,IIngredient input3,IIngredient input4, IIngredient output1, @Optional(valueDouble = 1D) double timeMultiplier)
		{
			CraftTweakerAPI.apply(new CTAddRecipe(NCSteamAdditionsRecipes.steam_transformer, Lists.newArrayList(input1, input2, input3, input4, output1, timeMultiplier)));
		}
		
		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input1,IIngredient input2,IIngredient input3,IIngredient input4) 
		{
			CraftTweakerAPI.apply(new CTRemoveRecipe(NCSteamAdditionsRecipes.steam_transformer, IngredientSorption.INPUT, Lists.newArrayList(input1,input2,input3,input4)));
		}
		
		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output1)
		{
			CraftTweakerAPI.apply(new CTRemoveRecipe(NCSteamAdditionsRecipes.steam_transformer, IngredientSorption.OUTPUT, Lists.newArrayList(output1)));
		}
		
		@ZenMethod
		public static void removeAllRecipes() 
		{
			CraftTweakerAPI.apply(new CTClearRecipes(NCSteamAdditionsRecipes.steam_transformer));
		}
	}

	@ZenClass("mods.ncsteamadditions.steam_crusher")
	@ZenRegister
	public static class SteamCrusherHandler
	{

		@ZenMethod
		public static void addRecipe(IIngredient input1,IIngredient input2, IIngredient output1, @Optional(valueDouble = 1D) double timeMultiplier)
		{
			CraftTweakerAPI.apply(new CTAddRecipe(NCSteamAdditionsRecipes.steam_crusher, Lists.newArrayList(input1, input2, output1, timeMultiplier)));
		}

		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input1,IIngredient input2)
		{
			CraftTweakerAPI.apply(new CTRemoveRecipe(NCSteamAdditionsRecipes.steam_crusher, IngredientSorption.INPUT, Lists.newArrayList(input1,input2)));
		}

		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output1)
		{
			CraftTweakerAPI.apply(new CTRemoveRecipe(NCSteamAdditionsRecipes.steam_crusher, IngredientSorption.OUTPUT, Lists.newArrayList(output1)));
		}

		@ZenMethod
		public static void removeAllRecipes()
		{
			CraftTweakerAPI.apply(new CTClearRecipes(NCSteamAdditionsRecipes.steam_crusher));
		}
	}

	@ZenClass("mods.ncsteamadditions.steam_boiler")
	@ZenRegister
	public static class SteamBoilerHandler
	{

		@ZenMethod
		public static void addRecipe(IIngredient input1,IIngredient input2, IIngredient output1, @Optional(valueDouble = 1D) double timeMultiplier)
		{
			CraftTweakerAPI.apply(new CTAddRecipe(NCSteamAdditionsRecipes.steam_boiler, Lists.newArrayList(input1, input2, output1, timeMultiplier)));
		}

		@ZenMethod
		public static void removeRecipeWithInput(IIngredient input1,IIngredient input2)
		{
			CraftTweakerAPI.apply(new CTRemoveRecipe(NCSteamAdditionsRecipes.steam_boiler, IngredientSorption.INPUT, Lists.newArrayList(input1,input2)));
		}

		@ZenMethod
		public static void removeRecipeWithOutput(IIngredient output1)
		{
			CraftTweakerAPI.apply(new CTRemoveRecipe(NCSteamAdditionsRecipes.steam_boiler, IngredientSorption.OUTPUT, Lists.newArrayList(output1)));
		}

		@ZenMethod
		public static void removeAllRecipes()
		{
			CraftTweakerAPI.apply(new CTClearRecipes(NCSteamAdditionsRecipes.steam_boiler));
		}
	}
}
