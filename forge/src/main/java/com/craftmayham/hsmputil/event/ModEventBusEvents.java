package com.craftmayham.hsmputil.event;

import com.craftmayham.hsmputil.HsmpMod;
import com.craftmayham.hsmputil.entity.ModEntities;
import com.craftmayham.hsmputil.entity.custom.CockroachEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HsmpMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
  @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.COCKROACH.get(), CockroachEntity.createAttributes().build());
    }
}
