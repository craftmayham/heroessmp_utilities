package com.craftmayham.hsmputil.effect;

import com.craftmayham.hsmputil.HsmpMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, HsmpMod.MODID);

    public static final RegistryObject<MobEffect> HEAT_STROKE = MOB_EFFECTS.register("heat_stroke",
            () -> new HeatStrokeEffect(MobEffectCategory.HARMFUL, 16746496));

    public static void register(IEventBus bus) {
        MOB_EFFECTS.register(bus);
    }
}
