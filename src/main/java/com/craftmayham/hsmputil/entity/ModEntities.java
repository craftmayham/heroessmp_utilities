package com.craftmayham.hsmputil.entity;

import com.craftmayham.hsmputil.HsmpMod;
import com.craftmayham.hsmputil.entity.custom.CockroachEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, HsmpMod.MODID);

    public static final RegistryObject<EntityType<CockroachEntity>> COCKROACH =
            ENTITY_TYPES.register("cockroach", ()-> EntityType.Builder.of(CockroachEntity::new, MobCategory.MONSTER)
                    .sized(0.8f,0.5f).build("cockroach"));

    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }
}
