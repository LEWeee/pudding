package leweee.pudding.datagen;

import leweee.pudding.Pudding;
import leweee.pudding.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Pudding.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.PUDDING);

        toolItem(ModItems.NET);
        simpleItem(ModItems.BAT);
        simpleItem(ModItems.SQUID);
        simpleItem(ModItems.GLOW_SQUID);
        simpleItem(ModItems.BAT_STEW);

        simpleItem(ModItems.SPECTRE_SEEDS);
        simpleItem(ModItems.SPECTRE_WHEAT);
        simpleItem(ModItems.DIMENSIONAL_BREAD);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Pudding.MODID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder toolItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Pudding.MODID,"item/" + item.getId().getPath()));
    }
}
