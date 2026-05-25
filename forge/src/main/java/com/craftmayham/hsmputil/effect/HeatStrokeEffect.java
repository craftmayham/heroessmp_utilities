package com.craftmayham.hsmputil.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class HeatStrokeEffect extends MobEffect {
    public int TimeElapsed = 0;
    public HeatStrokeEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.getArmorCoverPercentage() != 0) {
            entity.hurt(entity.damageSources().dryOut(), 1.0F);
        }
        if (entity.isInWaterOrRain()) {
            entity.removeEffect(ModEffects.HEAT_STROKE.get());
        }
    }
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
