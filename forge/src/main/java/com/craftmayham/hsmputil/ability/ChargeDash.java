package com.craftmayham.hsmputil.ability;

import com.google.j2objc.annotations.Property;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.threetag.palladium.power.IPowerHolder;
import net.threetag.palladium.power.ability.Ability;
import net.threetag.palladium.power.ability.AbilityInstance;
import net.threetag.palladium.util.property.IntegerProperty;
import net.threetag.palladium.util.property.PalladiumProperty;
import net.threetag.palladium.util.property.PropertyManager;
import net.threetag.palladium.util.property.SyncType;

public class ChargeDash extends Ability {
    public static final PalladiumProperty<Integer> CHARGE = new IntegerProperty("charge").sync(SyncType.NONE);
    public static final PalladiumProperty<Integer> TIMER = new IntegerProperty("timer").sync(SyncType.NONE);
    public static final PalladiumProperty<Integer> CHARGEDAMAGE = new IntegerProperty("chargedamage").sync(SyncType.NONE);

    @Override
    public void registerUniqueProperties(PropertyManager manager) {
        manager.register(CHARGE, 0);
        manager.register(TIMER, 0);
        manager.register(CHARGEDAMAGE, 0);
    }

    @Override
    public void tick(LivingEntity entity, AbilityInstance instance, IPowerHolder holder, boolean enabled) {
        if (entity == null) return;
        int charge = instance.getProperty(CHARGE);
        int timer = instance.getProperty(TIMER);
        int chargedamage = instance.getProperty(CHARGEDAMAGE);
        if (enabled && charge < 100) {
            charge = charge + 1;
            instance.setUniqueProperty(CHARGE, charge);
            if (!entity.level().isClientSide) {
                if (entity instanceof Player player) {
                    player.displayClientMessage(Component.literal("Charge: " + charge + "%").withStyle(ChatFormatting.YELLOW), true);
                }
            }
            chargedamage = charge;
            instance.setUniqueProperty(CHARGEDAMAGE, chargedamage);
        } else if (charge > 0) {
            Vec3 lookVec = entity.getLookAngle();
            entity.setDeltaMovement(lookVec.scale(charge * 0.05));
            if (timer == 0) {
                timer = 50;
            }

            charge = 0;
            instance.setUniqueProperty(CHARGE, charge);
        }
        if (timer > 0 && !enabled) {

            timer = timer - 1;

            instance.setUniqueProperty(TIMER, timer);
            if (entity instanceof Player player) {
                AABB box = entity.getBoundingBox().inflate(5);
                for (Entity target : entity.level().getEntities(entity, box)) {
                    if (target instanceof LivingEntity living && target != entity) {
                        living.hurt(entity.damageSources().playerAttack(player), chargedamage * 0.1f);
                        living.knockback(chargedamage * 0.1, entity.getX() - target.getX(), entity.getZ() - target.getZ());
                    }
                }
            }
        }
    }
}
