package com.craftmayham.hsmputil.condition;

import com.google.gson.JsonObject;
import net.minecraft.world.entity.Mob;
import net.threetag.palladium.condition.Condition;
import net.threetag.palladium.condition.ConditionSerializer;
import net.threetag.palladium.util.context.DataContext;

public class HasTargetCondition extends Condition {
    @Override
    public boolean active(DataContext context) {
        var entity = context.getLivingEntity();
        if (entity instanceof Mob mob) {
            return mob.getTarget() != null;
        }
        return false;
    }

    @Override
    public ConditionSerializer getSerializer() {
        return HsmpUtilConditions.HAS_TARGET.get();
    }
    public static class Serializer extends ConditionSerializer {

        @Override
        public Condition make(JsonObject json) {
            return new HasTargetCondition();
        }

        @Override
        public String getDocumentationDescription() {
            return "Checks if the entity has a target";
        }
    }
}
