package com.craftmayham.hsmputil.fluid;

import com.craftmayham.hsmputil.HsmpMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;

public class ModFluidTypes {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation LAVA_STILL_RL = new ResourceLocation("block/lava_still");
    public static final ResourceLocation LAVA_FLOWING_RL = new ResourceLocation("block/lava_flow");
    public static final ResourceLocation CEMENT_STILL_RL = new ResourceLocation("block/gray_concrete");
    public static final ResourceLocation CEMENT_FLOWING_RL = new ResourceLocation("block/gray_concrete");
    public static final ResourceLocation SOAP_OVERLAY_RL = null;
    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, HsmpMod.MODID);

    public static final RegistryObject<FluidType> LIQUID_CEMENT_TYPE = register("liquid_cement_fluid",
            FluidType.Properties.create().lightLevel(2).density(6).viscosity(5).sound(SoundActions.BUCKET_FILL, SoundEvents.HONEY_DRINK)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY),CEMENT_STILL_RL,CEMENT_FLOWING_RL,0xFFFFFFFF, new Vector3f(55f / 255f, 58f / 255f, 62f / 255f));

    public static final RegistryObject<FluidType> ACETONE_TYPE = register("acetane_fluid",
            FluidType.Properties.create().lightLevel(2).density(5).viscosity(2).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY),WATER_STILL_RL,WATER_FLOWING_RL,0x33EDCEEB, new Vector3f(237f / 255f, 206f / 255f, 235f / 255f));

    public static final RegistryObject<FluidType> HONEY_TYPE = register("honey_fluid",
            FluidType.Properties.create().lightLevel(2).density(10).viscosity(10).sound(SoundActions.BUCKET_FILL, SoundEvents.HONEY_DRINK)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY),WATER_STILL_RL,WATER_FLOWING_RL,0xFFF79909, new Vector3f(247f / 255f, 153f / 255f, 9f / 255f));


    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties, ResourceLocation still_texture, ResourceLocation flowing_texture, int tintColor, Vector3f fogColor) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(still_texture, flowing_texture, SOAP_OVERLAY_RL,
               tintColor, fogColor, properties));

    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
