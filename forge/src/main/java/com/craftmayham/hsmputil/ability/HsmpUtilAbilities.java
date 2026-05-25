package com.craftmayham.hsmputil.ability;

import com.craftmayham.hsmputil.HsmpMod;

import net.threetag.palladium.power.ability.Ability;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.RegistrySupplier;

public class HsmpUtilAbilities {
    public static final DeferredRegister<Ability> ABILITIES = DeferredRegister.create(HsmpMod.MODID, Ability.REGISTRY);

    public static final RegistrySupplier<Ability> HOTBAR_SHUFFLE = ABILITIES.register("hotbar_shuffle", HotBarShuffleAbility::new);
    public static final RegistrySupplier<Ability> POTION_TRANSFER = ABILITIES.register("potion_transfer", PotionTransferAbility::new);
    public static final RegistrySupplier<Ability> TarotFullMoonReset = ABILITIES.register("tarot_full_moon", TarotFullMoonReset::new);



}
