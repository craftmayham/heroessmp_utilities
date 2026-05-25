package com.craftmayham.hsmputil.ability;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.threetag.palladium.power.IPowerHolder;
import net.threetag.palladium.power.ability.Ability;
import net.threetag.palladium.power.ability.AbilityInstance;
import net.threetag.palladium.util.property.IntegerProperty;
import net.threetag.palladium.util.property.PalladiumProperty;

public class PotionTransferAbility extends Ability {
    public static final PalladiumProperty<Integer> RANGE = new IntegerProperty("range").configurable("Range of potion transfer");
    public PotionTransferAbility() {
        this.withProperty(RANGE, 10);
    }

    @Override
    public void tick(LivingEntity entity, AbilityInstance instance, IPowerHolder holder, boolean enabled) {
        if (entity.level().isClientSide) return;
        if (enabled) {
            var range = instance.getProperty(RANGE);
            Vec3 startVec = entity.getEyePosition();
            Vec3 endVec = startVec.add(entity.getLookAngle().scale(5.0D));

            AABB boundingBox = entity.getBoundingBox().expandTowards(entity.getLookAngle().scale(5.0D)).inflate(1.0D);
            EntityHitResult entityHit = ProjectileUtil.getEntityHitResult(
                    entity,
                    startVec,
                    endVec,
                    boundingBox,
                    target -> !entity.isSpectator() && entity.isPickable(),
                    range * range
            );
            if (entityHit == null) return;
            if (entityHit.getEntity() instanceof LivingEntity target) {
                entity.getActiveEffects().forEach(effect -> {
                    target.addEffect(effect);
                });
                entity.removeAllEffects();
            }

        }
    }
}
