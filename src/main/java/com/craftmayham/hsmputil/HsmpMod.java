package com.craftmayham.hsmputil;

import com.craftmayham.hsmputil.block.LiquidHoneyBlock;
import com.craftmayham.hsmputil.block.ModBlocks;
import com.craftmayham.hsmputil.fluid.ModFluidTypes;
import com.craftmayham.hsmputil.fluid.ModFluids;
import com.craftmayham.hsmputil.item.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HoneyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HsmpMod.MODID)
public class HsmpMod
{
    public static final String MODID = "hsmputil";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public HsmpMod(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        ModFluids.register(modEventBus);
        ModFluidTypes.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }


    @SubscribeEvent
    public void onEntityJoinWorld(final EntityJoinLevelEvent event) {
        if (!event.getEntity().level().isClientSide && event.getEntity() instanceof Mob) {

        }
    }


    @SubscribeEvent
    public void onRightClick(PlayerInteractEvent.RightClickItem event) {

        Player player = event.getEntity();
        Level level = event.getLevel();
        ItemStack stack = event.getItemStack();
        if (stack.is(Items.GLASS_BOTTLE)) {
            BlockHitResult hit = (BlockHitResult) player.pick(5.0D, 0.0F, true);

            if (hit.getType() == HitResult.Type.BLOCK) {
                BlockState state = level.getBlockState(hit.getBlockPos());
                Block block = state.getBlock();
                if (block instanceof LiquidHoneyBlock) {
                    player.playSound(SoundEvents.BOTTLE_FILL, 2.0F, 1.0F);
                    player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
                    player.addItem(new ItemStack(Items.HONEY_BOTTLE));
                    stack.shrink(1);
                }
            }
        }
        /*
        NonNullList<ItemStack> items = player.getInventory().items;
        List<ItemStack> hotbar = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            hotbar.add(items.get(i));
        }
        Collections.shuffle(hotbar);
        for (int i = 0; i < 9; i++) {
            items.set(i, hotbar.get(i));
        }

        player.getInventory().setChanged();
        player.containerMenu.broadcastChanges();


    //   Player player = event.getEntity();
        BlockPos pos = event.getPos();
        event.getLevel().setBlock(pos,Blocks.LAVA.defaultBlockState(), 1);

*/
    }

  /*  @SubscribeEvent
    public void onRightClick(PlayerInteractEvent.EntityInteract event) {
        // Only run on server
        if (event.getLevel().isClientSide) return;
        Entity player = event.getEntity();
        // Only main hand
        if (event.getHand() != InteractionHand.MAIN_HAND) return;

        // Make sure it’s a mob then we basically duplicate it with the owner attached and delete the old
        if (!(event.getTarget() instanceof Mob mob)) return;

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
    }*/


}
