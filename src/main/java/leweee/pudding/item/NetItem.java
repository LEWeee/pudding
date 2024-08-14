package leweee.pudding.item;

import leweee.pudding.config.Config;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class NetItem extends Item {
    public NetItem(Properties pProperties) {
        super(pProperties.stacksTo(1).durability(50));
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        String config = (String) Config.NETTABLES.get();
        String[] nettables = config.split(",");
        for (String i : nettables) {
            String[] able = i.split(" - ");
            EntityType<?> mob = ForgeRegistries.ENTITY_TYPES.getValue(ResourceLocation.tryParse(able[0]));
            Item item = ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(able[1]));

            if (pInteractionTarget.getType() == mob) {
                pInteractionTarget.remove(Entity.RemovalReason.DISCARDED);

                assert item != null;
                pPlayer.addItem(new ItemStack(item));
                pStack.hurtAndBreak(1, pPlayer, player -> player.broadcastBreakEvent(pUsedHand));

                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;

        /*if (pInteractionTarget instanceof Bat) {
            pInteractionTarget.remove(Entity.RemovalReason.DISCARDED);

            pPlayer.addItem(new ItemStack(ModItems.BAT.get()));

            pStack.hurtAndBreak(1, pPlayer, player -> player.broadcastBreakEvent(player.getUsedItemHand()));
        }*/
    }

}
