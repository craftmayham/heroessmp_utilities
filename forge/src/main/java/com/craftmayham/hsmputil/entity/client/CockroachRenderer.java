package com.craftmayham.hsmputil.entity.client;

import com.craftmayham.hsmputil.HsmpMod;
import com.craftmayham.hsmputil.entity.custom.CockroachEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CockroachRenderer extends MobRenderer<CockroachEntity, CockroachModel<CockroachEntity>> {
    public CockroachRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new CockroachModel<>(pContext.bakeLayer(ModModelLayers.COCKROACH_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(CockroachEntity pEntity) {
        return new ResourceLocation(HsmpMod.MODID, "textures/entity/cockroach.png");
    }
    @Override
    public void render(CockroachEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.5F,0.5F,0.5F);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
