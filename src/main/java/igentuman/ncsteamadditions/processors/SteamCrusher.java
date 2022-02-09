package igentuman.ncsteamadditions.processors;

import com.google.common.collect.Sets;
import igentuman.ncsteamadditions.block.Blocks;
import igentuman.ncsteamadditions.config.NCSteamAdditionsConfig;
import igentuman.ncsteamadditions.item.Items;
import igentuman.ncsteamadditions.jei.JEIHandler;
import igentuman.ncsteamadditions.jei.catergory.SteamCrusherCategory;
import igentuman.ncsteamadditions.machine.container.ContainerSteamCrusher;
import igentuman.ncsteamadditions.machine.gui.GuiSteamCrusher;
import igentuman.ncsteamadditions.recipes.NCSteamAdditionsRecipes;
import igentuman.ncsteamadditions.tile.TileNCSProcessor;
import mezz.jei.api.IGuiHelper;
import nc.container.processor.ContainerMachineConfig;
import nc.integration.jei.JEIBasicCategory;
import nc.recipe.ingredient.FluidIngredient;
import nc.tile.processor.TileItemFluidProcessor;
import nc.util.OreDictHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Set;

public class SteamCrusher extends AbstractProcessor {

    public static String code = "steam_crusher";

    public static String particle1 = "splash";

    public static String particle2 = "reddust";

    public final static int GUID = 1;

    public final static int SIDEID = 1000 + GUID;

    public static int inputItems = 1;

    public static int inputFluids = 1;

    public static int outputFluids = 0;

    public static int outputItems = 1;

    public static RecipeHandler recipes;

    public Object[] craftingRecipe = new Object[] {"BRB", "CFC", "PRP", 'B', net.minecraft.init.Items.BUCKET, 'F', "chest", 'C', Items.items[0], 'R', net.minecraft.init.Blocks.PISTON, 'P', net.minecraft.init.Items.DIAMOND_PICKAXE};

    public int getInputItems() {
        return inputItems;
    }

    public int getInputFluids() {
        return inputFluids;
    }

    public int getOutputFluids() {
        return outputFluids;
    }

    public String getBlockType()
    {
        return "nc_processor";
    }

    public int getOutputItems() {
        return outputItems;
    }

    public Object[] getCraftingRecipe()
    {
       return this.craftingRecipe;
    }

    public JEIHandler recipeHandler;

    public int getGuid()
    {
        return GUID;
    }

    public int getSideid()
    {
        return SIDEID;
    }

    public String getCode()
    {
        return code;
    }

    GuiSteamCrusher guiSteamCrusher;
    GuiSteamCrusher.SideConfig sideConfig;
    Object containerMachineConfig;

    public Object getLocalGuiContainer(EntityPlayer player, TileEntity tile) {
        if(guiSteamCrusher == null) {
            guiSteamCrusher = new GuiSteamCrusher(player, (SteamCrusher.TileSteamCrusher) tile, this);
        }
        return guiSteamCrusher;
    }


    public Object getLocalGuiContainerConfig(EntityPlayer player, TileEntity tile) {
        if(sideConfig == null) {
            sideConfig = new GuiSteamCrusher.SideConfig(player, (SteamCrusher.TileSteamCrusher) tile, this);
        }
        return sideConfig;
    }

    public Object getGuiContainer(EntityPlayer player, TileEntity tile) {
        return new ContainerSteamCrusher(player, (SteamCrusher.TileSteamCrusher) tile);
    }


    public Object getGuiContainerConfig(EntityPlayer player, TileEntity tile) {
        if(containerMachineConfig == null) {
            containerMachineConfig = new ContainerMachineConfig(player, (SteamCrusher.TileSteamCrusher) tile);
        }
        return containerMachineConfig;
    }

    public JEIHandler getRecipeHandler()
    {
        return this.recipeHandler;
    }

    public JEIBasicCategory getRecipeCategory(IGuiHelper guiHelper)
    {
        recipeHandler = new JEIHandler(this, NCSteamAdditionsRecipes.processorRecipeHandlers[GUID], Blocks.blocks[SteamCrusher.GUID], SteamCrusher.code, SteamCrusherCategory.SteamCrusherWrapper.class);
        return new SteamCrusherCategory(guiHelper,recipeHandler, this);
    }

    public ProcessorType getType()
    {
        if(type == null) {
            type = new ProcessorType(code,GUID,particle1,particle2);
            type.setProcessor(this);
        }
        return type;
    }

    public Class getTileClass()
    {
        return TileSteamCrusher.class;
    }

    public static class TileSteamCrusher extends TileNCSProcessor
    {
        public TileSteamCrusher()
        {
            super(
                    code,
                    inputItems,
                    inputFluids,
                    outputItems,
                    outputFluids,
                    defaultItemSorptions(inputItems, outputItems, true),
                    defaultTankCapacities(5000, inputFluids, outputFluids),
                    defaultTankSorptions(inputFluids, outputFluids),
                    NCSteamAdditionsRecipes.validFluids[GUID],
                    NCSteamAdditionsConfig.processor_time[GUID],
                    0, true,
                    NCSteamAdditionsRecipes.processorRecipeHandlers[GUID],
                    GUID+1, 0,0,10
            );
        }
    }

    public SteamCrusher.RecipeHandler getRecipes()
    {
        return new SteamCrusher.RecipeHandler();
    }


    public class RecipeHandler extends AbstractProcessor.RecipeHandler {
        public RecipeHandler()
        {
            super(code, inputItems, inputFluids, outputItems, outputFluids);
        }

        @Override
        public void addRecipes()
        {
            addDustRecipes();
        }

        public void addDustRecipes() {
            String[] var1 = OreDictionary.getOreNames();
            int var2 = var1.length;
            FluidIngredient steam = fluidStack("low_pressure_steam", 250);
            Set<String> DUST_BL = Sets.newHashSet(new String[]{"Graphite"});
            for(int var3 = 0; var3 < var2; ++var3) {
                String oreEntry = var1[var3];
                String dust;
                if (oreEntry.startsWith("dust")) {
                    dust = oreEntry.substring(4);
                    if (DUST_BL.contains(dust)) {
                        continue;
                    }

                    String ingot = "ingot" + dust;
                    String gem = "gem" + dust;
                    String ore = "ore" + dust;
                    if (OreDictHelper.oreExists(ingot)) {
                        this.addRecipe(new Object[]{ingot, steam, oreEntry, 1.0D, 1.0D});
                    } else if (OreDictHelper.oreExists(gem)) {
                        this.addRecipe(new Object[]{gem,steam, oreEntry, 1.0D, 1.0D});
                    } else if (OreDictHelper.oreExists(ore)) {
                        this.addRecipe(new Object[]{ore,steam, oreEntry, 1.0D, 1.0D});
                    }
                }
            }

        }
    }
}
