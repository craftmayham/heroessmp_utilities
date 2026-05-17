package com.craftmayham.hsmputil.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.RegistryObject;

public class LiquidHoneyBlock extends LiquidBlock {
    public LiquidHoneyBlock(RegistryObject<FlowingFluid> pFluid, Properties pProperties) {
        super(pFluid, pProperties);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity living) {
            Vec3 motion = living.getDeltaMovement();
            living.setDeltaMovement(motion.x * 0.6, motion.y * 0.6, motion.z * 0.6);
            living.fallDistance *= 0.5F;
            if (living.tickCount % 70 == 0) {
                living.playSound(SoundEvents.PLAYER_SWIM);
            }
        }
    }
}
