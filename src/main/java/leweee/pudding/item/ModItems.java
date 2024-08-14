package leweee.pudding.item;

import leweee.pudding.Pudding;
import leweee.pudding.block.ModBlocks;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Pudding.MODID);

    public static final RegistryObject<Item> PUDDING = ITEMS.register("pudding",
            () -> new Item(new Item.Properties().food(ModFoods.PUDDING)));

    public static final RegistryObject<Item> NET = ITEMS.register("net",
            () -> new NetItem(new Item.Properties()));
    public static final RegistryObject<Item> BAT = ITEMS.register("bat",
            () -> new MobItem(new Item.Properties(), "tooltip.pudding.bat.tooltip", EntityType.BAT));
    public static final RegistryObject<Item> SQUID = ITEMS.register("squid",
            () -> new MobItem(new Item.Properties(), "tooltip.pudding.squid.tooltip", EntityType.SQUID));
    public static final RegistryObject<Item> GLOW_SQUID = ITEMS.register("glow_squid",
            () -> new MobItem(new Item.Properties(), "tooltip.pudding.glow_squid.tooltip", EntityType.GLOW_SQUID));
    public static final RegistryObject<Item> BAT_STEW = ITEMS.register("bat_stew",
            () -> new BowlFoodItem(new Item.Properties().food(ModFoods.BAT_STEW).stacksTo(1)));

    public static final RegistryObject<Item> SPECTRE_SEEDS = ITEMS.register("spectre_wheat_seeds",
            () -> new ItemNameBlockItem(ModBlocks.SPECTRE_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> SPECTRE_WHEAT = ITEMS.register("spectre_wheat",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DIMENSIONAL_BREAD = ITEMS.register("dimensional_bread",
            () -> new Item(new Item.Properties().food(ModFoods.DIMENSIONAL_BREAD)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
