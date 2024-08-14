package leweee.pudding.datagen;

import leweee.pudding.block.ModBlocks;
import leweee.pudding.config.Config;
import leweee.pudding.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        if (Config.ENABLE_NET.get()) {
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.NET.get())
                    .pattern("XSX")
                    .pattern(" X ")
                    .pattern(" X ")
                    .define('X', Items.STICK)
                    .define('S', Items.STRING)
                    .unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                    .save(recipeOutput);

            ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.BAT_STEW.get())
                    .requires(ModItems.BAT.get())
                    .requires(Blocks.RED_MUSHROOM)
                    .requires(Blocks.BROWN_MUSHROOM)
                    .requires(Items.BOWL)
                    .unlockedBy(getHasName(ModItems.BAT.get()), has(ModItems.BAT.get()))
                    .save(recipeOutput);
        }
        if (Config.ENABLE_SPECTRE_WHEAT.get()) {
            ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.DIMENSIONAL_BREAD.get())
                    .pattern("XXX")
                    .define('X', ModItems.SPECTRE_WHEAT.get())
                    .unlockedBy(getHasName(ModItems.SPECTRE_WHEAT.get()), has(ModItems.SPECTRE_WHEAT.get()))
                    .save(recipeOutput);
        }

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ENDER_PEARL_BLOCK.get())
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', Items.ENDER_PEARL)
                .unlockedBy(getHasName(Items.ENDER_PEARL), has(Items.ENDER_PEARL))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.ENDER_PEARL, 9)
                .requires(ModBlocks.ENDER_PEARL_BLOCK.get())
                .unlockedBy(getHasName(Items.ENDER_PEARL), has(ModBlocks.ENDER_PEARL_BLOCK.get()))
                .save(recipeOutput);
    }
}
