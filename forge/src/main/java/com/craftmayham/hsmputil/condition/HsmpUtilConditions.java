package com.craftmayham.hsmputil.condition;

import com.craftmayham.hsmputil.HsmpMod;
import net.threetag.palladium.condition.Condition;
import net.threetag.palladium.condition.ConditionSerializer;
import net.threetag.palladium.power.ability.Ability;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.RegistrySupplier;

public class HsmpUtilConditions {
    public static final DeferredRegister<ConditionSerializer> CONDITION_SERIALIZERS = DeferredRegister.create(HsmpMod.MODID, ConditionSerializer.REGISTRY);
    public static final RegistrySupplier<ConditionSerializer> HAS_TARGET = CONDITION_SERIALIZERS.register("has_target", HasTargetCondition.Serializer::new);
}
