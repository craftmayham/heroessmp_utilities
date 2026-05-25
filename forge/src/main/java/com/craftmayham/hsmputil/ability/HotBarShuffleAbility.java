package com.craftmayham.hsmputil.ability;

import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.threetag.palladium.power.IPowerHolder;
import net.threetag.palladium.power.ability.*;
import net.threetag.palladium.util.property.IntegerProperty;
import net.threetag.palladium.util.property.PalladiumProperty;
import net.threetag.palladium.util.property.PropertyManager;
import net.threetag.palladium.util.property.SyncType;
import net.threetag.palladiumcore.PalladiumCoreClient;
import net.threetag.palladiumcore.event.EventResult;
import net.threetag.palladiumcore.event.LivingEntityEvents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HotBarShuffleAbility extends Ability {
    public static final PalladiumProperty<Integer> RANGE = new IntegerProperty("range").configurable("Range of shuffle");
    public HotBarShuffleAbility() {
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
            if (entityHit.getEntity() instanceof Player player) {
                NonNullList<ItemStack> items = player.getInventory().items;
                List<ItemStack> hotbar = new ArrayList<>();
                for (int i = 0; i < 36; i++) {
                    hotbar.add(items.get(i));
                }

                Collections.shuffle(hotbar);
                for (int i = 0; i < 36; i++) {
                    items.set(i, hotbar.get(i));
                }

                player.getInventory().setChanged();
                player.containerMenu.broadcastChanges();
            }

        }
    }

}
