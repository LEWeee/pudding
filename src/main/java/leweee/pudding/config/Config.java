package leweee.pudding.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    public static final ForgeConfigSpec.BooleanValue ENABLE_NET;
    public static final ForgeConfigSpec.ConfigValue NETTABLES;

    public static final ForgeConfigSpec.BooleanValue ENABLE_SPECTRE_WHEAT;
    public static final ForgeConfigSpec.DoubleValue DIMENSIONAL_BREAD_INVISIBILITY;

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    static {
        BUILDER.push("config");

        ENABLE_NET = BUILDER
                .comment("Enable or disable Nets")
                .define("enable_net", true);

        NETTABLES = BUILDER
                .comment("Which mobs can be caught by the net")
                .define("nettables", "minecraft:bat - pudding:bat," +
                        "minecraft:squid - pudding:squid," +
                        "minecraft:glow_squid - pudding:glow_squid");

        ENABLE_SPECTRE_WHEAT = BUILDER
                .comment("Enable or disable Spectre Wheat and Dimensional Bread")
                .define("enable_spectre_wheat", true);

        DIMENSIONAL_BREAD_INVISIBILITY = BUILDER
                .comment("The likelihood of dimensional bread turning you invisible upon eating")
                .defineInRange("dimensional_bread_saturation", 0.2, 0.0, 1.0);

        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}
