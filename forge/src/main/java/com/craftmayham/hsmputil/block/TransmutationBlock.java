package com.craftmayham.hsmputil.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class TransmutationBlock extends Block {
    private final Block block;
    public TransmutationBlock(Properties pProperties, Block block) {
        super(pProperties);
        this.block = block;
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);

        if (!level.isClientSide) {
            level.scheduleTick(pos, this, 20 * 5);
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {

        if (state.getBlock() instanceof TransmutationBlock) {

            level.setBlock(pos, block.defaultBlockState(), 3);
        }
    }
}
