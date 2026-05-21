package com.craftmayham.hsmputil.entity.animations;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class ModAnimationDefinitions {
	public static final AnimationDefinition COCKROACH_WALK = AnimationDefinition.Builder.withLength(9.25F).looping()
			.addAnimation("cockroach", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("cockroach", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.75F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("front_right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -21.5F, -44.4097F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("front_left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 21.5F, 44.4097F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("middle_right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(9.0072F, -21.7048F, -47.239F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("middle_left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 23.5F, 45.5903F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("back_right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 23.0008F, -23.4059F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("back_left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -23.0008F, 23.4059F), AnimationChannel.Interpolations.LINEAR)
			))
			.build();

	public static final AnimationDefinition COCKROACH_IDLE = AnimationDefinition.Builder.withLength(9.25F).looping()
			.addAnimation("cockroach", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("cockroach", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.75F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("front_right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -22.5F, -45.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("front_left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 22.5F, 45.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("middle_right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(9.0072F, -20.7048F, -46.6487F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("middle_left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 22.5F, 45.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("back_right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 22.5F, -22.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("back_left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -22.5F, 22.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.build();
}
