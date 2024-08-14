package leweee.pudding.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MobItem extends Item {
    private final String tooltip;
    private final EntityType<? extends LivingEntity> entityType;

    public MobItem(Item.Properties pProperties, String tooltip, EntityType<? extends LivingEntity> entityType) {
        super(pProperties.stacksTo(1));
        this.tooltip = tooltip;
        this.entityType = entityType;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (tooltip != null && !tooltip.isEmpty()) {
            pTooltipComponents.add(Component.translatable(tooltip));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide) {
            Level level = pContext.getLevel();
            LivingEntity entity = entityType.create(level);
            if (entity != null) {
                entity.setPos(pContext.getClickedPos().getX(), pContext.getClickedPos().getY() + 1, pContext.getClickedPos().getZ());
                level.addFreshEntity(entity);
                pContext.getItemInHand().shrink(1);
            }
        }
        return InteractionResult.SUCCESS;
    }
}


