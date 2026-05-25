package com.craftmayham.hsmputil.ability;

import net.minecraft.world.entity.LivingEntity;
import net.threetag.palladium.power.IPowerHolder;
import net.threetag.palladium.power.ability.Ability;
import net.threetag.palladium.power.ability.AbilityInstance;

import java.util.ArrayList;

public class TarotFullMoonReset extends Ability {
    public boolean updateTags = true;
    @Override
    public void tick(LivingEntity entity, AbilityInstance instance, IPowerHolder holder, boolean enabled) {

        if (entity.level().getMoonPhase() == 0) {
            if (updateTags) {
                entity.addTag("fool");
                entity.addTag("magician");
                entity.addTag("high_priestess");
                entity.addTag("empress");
                entity.addTag("emperor");
                entity.addTag("hierophant");
                entity.addTag("lovers");
                entity.addTag("chariot");
                entity.addTag("strength");
                entity.addTag("hermit");
                entity.addTag("wheel");
                entity.addTag("justice");
                entity.addTag("hanged_man");
                entity.addTag("death");
                entity.addTag("temperance");
                entity.addTag("devil");
                entity.addTag("tower");
                entity.addTag("star");
                entity.addTag("moon");
                entity.addTag("sun");
                entity.addTag("judgement");
                entity.addTag("world");
                updateTags = false;
            }
        } else {
            updateTags = true;
        }
    }
}
