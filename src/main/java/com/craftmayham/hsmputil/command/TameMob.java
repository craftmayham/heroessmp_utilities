package com.craftmayham.hsmputil.command;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TameMob {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("tame")
                .requires(s -> s.hasPermission(4))
                .then(Commands.argument("mob", EntityArgument.entity())
                        .then(Commands.argument("source", EntityArgument.player())
                                .executes(TameMob::execute)
                        )
                )
        );
    }
    private static int execute(CommandContext<CommandSourceStack> arguments) {
        try {
            Entity mob = EntityArgument.getEntity(arguments, "mob");
            Entity source = EntityArgument.getEntity(arguments, "source");
            attemptTame(mob, source);
            return 1;
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static void attemptTame(Entity entity, Entity source) {
        if (entity == null || source == null) return;
        if (entity.level().isClientSide()) return;
        if (!(source instanceof Player player)) return;
        if (!(entity instanceof Mob mob)) return;

        Entity newMob = mob.getType().create(mob.level());
        newMob.setPos(mob.getX(),mob.getY(),mob.getZ());
        newMob.setYRot(mob.getYRot());
        newMob.setXRot(mob.getXRot());
        newMob.setYBodyRot(mob.yBodyRot);
        newMob.setYHeadRot(mob.yHeadRot);

        CompoundTag newData = newMob.getPersistentData();

        String ownerName = player.getDisplayName().getString();
        String entityName = newMob.getType().getDescription().getString();
        newMob.setCustomName(Component.literal(ownerName + "'s " + entityName));
        newMob.setCustomNameVisible(false);

        newData.putString("ownerUUID", player.getStringUUID());
        if (mob.getType() == EntityType.SKELETON) {
            newMob.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
        }
        mob.level().addFreshEntity(newMob);
        mob.discard();
    }

}
