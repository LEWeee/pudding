package leweee.pudding.item;

import leweee.pudding.config.Config;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static final FoodProperties BAT_STEW = new FoodProperties.Builder().nutrition(8).saturationMod(0.6f).build();
    public static FoodProperties DIMENSIONAL_BREAD = new FoodProperties.Builder().nutrition(5)
            .saturationMod(0.6f).effect(() -> new MobEffectInstance(MobEffects.INVISIBILITY, 1200), 0.2f).build();
    public static final FoodProperties PUDDING = new FoodProperties.Builder().nutrition(5).saturationMod(0.9f).build();
}
