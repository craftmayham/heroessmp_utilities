package com.craftmayham.hsmputil.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.registries.RegistryObject;

public class LiquidCementBlock extends LiquidBlock {
    private final Block block;
    public LiquidCementBlock(RegistryObject<FlowingFluid> pFluid, Properties pProperties, Block block) {
        super(pFluid, pProperties);
        this.block = block;
    }
    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);

        if (!level.isClientSide) {
            level.scheduleTick(pos, this, 20 * 4);
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {

        // Make sure it's still your fluid block
        if (state.getBlock() instanceof LiquidCementBlock) {

            // Replace with cobblestone
            level.setBlock(pos, block.defaultBlockState(), 3);
        }
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity living) {
            if (living.tickCount % 70 == 0) {
                living.playSound(SoundEvents.PLAYER_SWIM);
            }
        }
    }
}
