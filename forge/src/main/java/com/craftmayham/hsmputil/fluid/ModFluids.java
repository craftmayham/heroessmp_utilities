package com.craftmayham.hsmputil.fluid;

import com.craftmayham.hsmputil.HsmpMod;
import com.craftmayham.hsmputil.block.ModBlocks;
import com.craftmayham.hsmputil.item.ModItems;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, HsmpMod.MODID);

    public static final RegistryObject<FlowingFluid> SOURCE_LIQUID_CEMENT = FLUIDS.register("liquid_cement_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.SOURCE_LIQUID_CEMENT_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_LIQUID_CEMENT = FLUIDS.register("flowing_liquid_cement",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.SOURCE_LIQUID_CEMENT_PROPERTIES));

    public static final RegistryObject<FlowingFluid> SOURCE_ACETONE = FLUIDS.register("acetone_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.SOURCE_ACETONE_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_ACETONE = FLUIDS.register("flowing_acetone",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.SOURCE_ACETONE_PROPERTIES));

    public static final RegistryObject<FlowingFluid> SOURCE_HONEY = FLUIDS.register("honey_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.SOURCE_HONEY_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_HONEY = FLUIDS.register("flowing_honey",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.SOURCE_HONEY_PROPERTIES));


    public static final ForgeFlowingFluid.Properties SOURCE_LIQUID_CEMENT_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.LIQUID_CEMENT_TYPE, SOURCE_LIQUID_CEMENT, FLOWING_LIQUID_CEMENT)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.LIQUID_CEMENT_BLOCK)
    .bucket(ModItems.LIQUID_CEMENT_BUCKET).tickRate(15);

    public static final ForgeFlowingFluid.Properties SOURCE_ACETONE_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.ACETONE_TYPE, SOURCE_ACETONE, FLOWING_ACETONE)
            .slopeFindDistance(4).levelDecreasePerBlock(2).block(ModBlocks.ACETONE_BLOCK)
            .bucket(ModItems.ACETONE_BUCKET).tickRate(10);

    public static final ForgeFlowingFluid.Properties SOURCE_HONEY_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.HONEY_TYPE, SOURCE_HONEY, FLOWING_HONEY)
            .slopeFindDistance(4).levelDecreasePerBlock(2).block(ModBlocks.LIQUID_HONEY_BLOCK)
            .bucket(ModItems.HONEY_BUCKET).tickRate(20);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}