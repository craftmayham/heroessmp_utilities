package com.craftmayham.hsmputil.event;

import com.craftmayham.hsmputil.HsmpMod;
import com.craftmayham.hsmputil.effect.ModEffects;
import com.craftmayham.hsmputil.entity.client.CockroachModel;
import com.craftmayham.hsmputil.entity.client.ModModelLayers;
import com.craftmayham.hsmputil.gui.HeroOrVillainScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ModEventBusClientEvents {
    @Mod.EventBusSubscriber(modid = HsmpMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ModBusEvents {
        @SubscribeEvent
        public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(ModModelLayers.COCKROACH_LAYER, CockroachModel::createBodyLayer);
        }
    }



}
