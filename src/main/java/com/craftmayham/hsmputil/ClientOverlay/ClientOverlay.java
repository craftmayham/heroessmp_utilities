package com.craftmayham.hsmputil.ClientOverlay;

import com.craftmayham.hsmputil.HsmpMod;
import com.craftmayham.hsmputil.effect.ModEffects;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.machinezoo.noexception.Exceptions.log;

@Mod.EventBusSubscriber(modid = HsmpMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientOverlay {
    public static float alphaBefore = 0f;
    public static float alpha = 0f;
    public static final ResourceLocation HEAT_OVERLAY = new ResourceLocation("hsmputil", "textures/overlay/heat_stroke.png");
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        LocalPlayer player = Minecraft.getInstance().player;

        if (player == null) return;

        boolean hasEffect = player.hasEffect(ModEffects.HEAT_STROKE.get());

        float hasEffectFloat = hasEffect ? 0.02F : 0.0F;

       alpha += (hasEffectFloat - alpha) * 0.03F;

    }


    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Post event) {
        LocalPlayer player = Minecraft.getInstance().player;

        if (player == null) return;

        if (player.hasEffect(ModEffects.HEAT_STROKE.get())) {
            renderHeatStrokeOverlay(event);
        }
    }

    @SubscribeEvent
    public static void renderHeatStrokeOverlay(RenderGuiOverlayEvent.Post event) {
        int w = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int h = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();


        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, alpha);
     //   event.getGuiGraphics().setColor(1f,1f,1f,alpha);

        event.getGuiGraphics().blit(HEAT_OVERLAY,0,0,0,0,w,h,512,256);
       // event.getGuiGraphics().setColor(1f,1f,1f,1f);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.0f);
        RenderSystem.disableBlend();

    }
}
