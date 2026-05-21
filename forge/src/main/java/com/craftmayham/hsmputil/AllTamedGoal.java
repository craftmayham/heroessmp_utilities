package com.craftmayham.hsmputil;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;

import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


import java.util.UUID;



@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AllTamedGoal {
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
        private static class TamedAttack {

            public static void FollowOwner(Mob mob, boolean hasTarget) {
                Player owner = getOwner(mob);
                if (owner == null) return;
                if (!hasTarget) return;
                mob.getLookControl().setLookAt(owner, 30.0F, 30.0F);
                if (!(mob.distanceToSqr(owner) <= (double) (10 * 10))) {
                    {
                        BlockPos $$0 = owner.blockPosition();

                        for (int $$1 = 0; $$1 < 10; ++$$1) {
                            int $$2 = mob.getRandom().nextInt(-3, 3);
                            int $$3 = mob.getRandom().nextInt(-1, 1);
                            int $$4 = mob.getRandom().nextInt(-3, 3);
                            if (canTeleportTo(new BlockPos($$0.getX() + $$2, $$0.getY() + $$3, $$0.getZ() + $$4), mob)) {
                                mob.moveTo($$0.getX() + $$2, $$0.getY() + $$3, $$0.getZ() + $$4);
                            }

                        }
                    }
                }
                if (owner.isSpectator()) {
                    return;
                } else if (mob.distanceToSqr(owner) < (double) (8 * 8)) {
                    return;
                }

                if (mob.tickCount % 10 == 0) {
                    mob.getNavigation().moveTo(owner, 1.0D);
                }


            }
            @SubscribeEvent
            public static void onTargetChange(LivingChangeTargetEvent event) {
                Entity entity = event.getEntity();
                if (!(entity instanceof Mob attacker)) return;
                Player attackerOwner = getOwner(attacker);
                LivingEntity newTarget = event.getNewTarget();

                if (newTarget == attackerOwner) {
                    event.setCanceled(true);
                    return;
                }
                if (isOwnerOf(newTarget, attackerOwner)) {
                    event.setCanceled(true);
                }
            }

            public static void onEntityJoin(net.minecraftforge.event.entity.EntityJoinLevelEvent event) {
                if (event.getLevel().isClientSide()) return;
                if (event.getEntity() instanceof Mob mob) {
                    mob.targetSelector.removeAllGoals(goal -> true);
                }
            }

            @SubscribeEvent
            public static void onEntityTick(LivingEvent.LivingTickEvent event) {
                Entity entity = event.getEntity();
                if (entity.level().isClientSide() || !(entity instanceof Mob mob)) return;
                Player owner = getOwner(mob);
                if (owner == null) return;
                LivingEntity forcedTarget = null;
                if (owner.getLastHurtMob() != null) {
                    forcedTarget = owner.getLastHurtMob();
                } else if (owner.getLastHurtByMob() != null) {
                    forcedTarget = owner.getLastHurtByMob();
                } else if (mob.getLastHurtByMob() != null) {

                    forcedTarget = mob.getLastHurtByMob();
                }
                if (forcedTarget != null) {
                    mob.setTarget(forcedTarget);
                }
                FollowOwner(mob,mob.getTarget() == null);
            }
        }

        private static Player getOwner(Mob tamed) {
            if (tamed instanceof TamableAnimal tamable) {
                LivingEntity owner = tamable.getOwner();
                if (owner instanceof Player p) {
                    return p;
                }
            }

            String ownerUUIDStr = tamed.getPersistentData().getString("ownerUUID");
            if (ownerUUIDStr.isEmpty()) return null;
            try {
                return tamed.level().getPlayerByUUID(UUID.fromString(ownerUUIDStr));
            } catch (Exception e) {
                return null;
            }
        }

        private static boolean isOwnerOf(Entity tamed, Entity potentialOwner) {
            if (!(potentialOwner instanceof Player player) || tamed == null) return false;

            if (tamed instanceof TamableAnimal tamable) {
                if (tamable.isOwnedBy(player)) {
                    return true;
                }
            }

            String ownerUUIDStr = tamed.getPersistentData().getString("ownerUUID");
            if (!ownerUUIDStr.isEmpty()) {
                try {
                    return UUID.fromString(ownerUUIDStr).equals(potentialOwner.getUUID());
                } catch (Exception e) {
                    return false;
                }
            }
            return false;
        }

        private static boolean canTeleportTo(BlockPos pPos, Mob mob) {
            BlockPathTypes $$1 = WalkNodeEvaluator.getBlockPathTypeStatic(mob.level(), pPos.mutable());
            if ($$1 != BlockPathTypes.WALKABLE) {
                return false;
            } else {
                BlockState $$2 = mob.level().getBlockState(pPos.below());

                BlockPos $$3 = pPos.subtract(mob.blockPosition());
                return mob.level().noCollision(mob, mob.getBoundingBox().move($$3));
            }
        }

}
