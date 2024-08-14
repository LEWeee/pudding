package leweee.pudding.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import javax.annotation.Nullable;

public class EnderPearlBlock extends Block {
    public static final BooleanProperty POWERED = BooleanProperty.create("powered");

    public EnderPearlBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(POWERED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(POWERED, pContext.getLevel().hasNeighborSignal(pContext.getClickedPos()));
    }

    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos fromPos, boolean isMoving) {
        boolean isPowered = pLevel.hasNeighborSignal(pPos);
        if (pState.getValue(POWERED) != isPowered) {
            if (isPowered) {
                pLevel.playSound(null, pPos, SoundEvents.ENDERMAN_TELEPORT, SoundSource.BLOCKS);
                spawnParticles(pLevel, pPos);
                pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 3);
            } else {
                pLevel.setBlock(pPos, this.defaultBlockState().setValue(POWERED, false), 3);
                pLevel.playSound(null, pPos, SoundEvents.ENDERMAN_TELEPORT, SoundSource.BLOCKS);
                spawnParticles(pLevel, pPos);
            }
        }
    }

    private static void spawnParticles(Level pLevel, BlockPos pPos) {
        double d0 = 0.5625;
        RandomSource randomsource = pLevel.random;
        Direction[] var5 = Direction.values();
        int var6 = var5.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            Direction direction = var5[var7];
            Direction.Axis direction$axis = direction.getAxis();
            double d1 = direction$axis == Direction.Axis.X ? 0.5 + 0.5625 * (double)direction.getStepX() : (double)randomsource.nextFloat();
            double d2 = direction$axis == Direction.Axis.Y ? 0.5 * (double)direction.getStepY() : (double)randomsource.nextFloat();
            double d3 = direction$axis == Direction.Axis.Z ? 0.5 + 0.5625 * (double)direction.getStepZ() : (double)randomsource.nextFloat();
            pLevel.addParticle(ParticleTypes.PORTAL, (double)pPos.getX() + d1, (double)pPos.getY() + d2, (double)pPos.getZ() + d3, 0.0, 0.0, 0.0);
        }
    }

}

