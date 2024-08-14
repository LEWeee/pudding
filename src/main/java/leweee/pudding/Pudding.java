package leweee.pudding;

import com.mojang.logging.LogUtils;
import leweee.pudding.block.ModBlocks;
import leweee.pudding.config.Config;
import leweee.pudding.item.ModItems;
import leweee.pudding.item.ModFoods;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Pudding.MODID)
public class Pudding
{
    public static final String MODID = "pudding";
    private static final Logger LOGGER = LogUtils.getLogger();
    public Pudding()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC, "pudding.toml");

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        registerCompostableItems();
        updateFoodProperties();
    }

    private void updateFoodProperties() {
        // Now that the config is loaded, update the DIMENSIONAL_BREAD food property
        ModFoods.DIMENSIONAL_BREAD = new FoodProperties.Builder().nutrition(5)
                .saturationMod(0.6f)
                .effect(() -> new MobEffectInstance(MobEffects.INVISIBILITY, 1200), (float) (double) Config.DIMENSIONAL_BREAD_INVISIBILITY.get())
                .build();
    }

    private void registerCompostableItems() {
        if (Config.ENABLE_SPECTRE_WHEAT.get()) {
            ComposterBlock.COMPOSTABLES.put(ModItems.SPECTRE_SEEDS.get(), 0.3f);
            ComposterBlock.COMPOSTABLES.put(ModItems.SPECTRE_WHEAT.get(), 0.65f);
            ComposterBlock.COMPOSTABLES.put(ModItems.DIMENSIONAL_BREAD.get(), 0.85f);
        }
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (Config.ENABLE_NET.get()) {
            if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
                event.accept(ModItems.NET);
            }
            if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
                event.accept(ModItems.BAT_STEW);
            }
        }
        if (Config.ENABLE_SPECTRE_WHEAT.get()) {
            if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
                event.accept(ModItems.SPECTRE_SEEDS);
            }
            if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
                event.accept(ModItems.DIMENSIONAL_BREAD);
            }
            if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
                event.accept(ModItems.SPECTRE_WHEAT);
            }
        }
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.ENDER_PEARL_BLOCK);
        }
        if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
            event.accept(ModBlocks.ENDER_PEARL_BLOCK);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}
