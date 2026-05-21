package com.craftmayham.hsmputil.block;

import com.craftmayham.hsmputil.HsmpMod;
import com.craftmayham.hsmputil.fluid.ModFluids;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, HsmpMod.MODID);


    public static final RegistryObject<Block> LAVA_TRANSMUTATION_BLOCK = BLOCKS.register("lava_transmutation_block",
            () -> new TransmutationBlock(BlockBehaviour.Properties.copy(Blocks.BEDROCK),Blocks.LAVA));

    public static final RegistryObject<Block> WATER_TRANSMUTATION_BLOCK = BLOCKS.register("water_transmutation_block",
            () -> new TransmutationBlock(BlockBehaviour.Properties.copy(Blocks.BEDROCK),Blocks.WATER));

    public static final RegistryObject<LiquidBlock> LIQUID_CEMENT_BLOCK = BLOCKS.register("liquid_cement_block",
            () -> new LiquidCementBlock(ModFluids.SOURCE_LIQUID_CEMENT, BlockBehaviour.Properties.copy(Blocks.WATER),Blocks.GRAY_CONCRETE));

    public static final RegistryObject<LiquidBlock> ACETONE_BLOCK = BLOCKS.register("acetone_block",
            () -> new LiquidCementBlock(ModFluids.SOURCE_ACETONE, BlockBehaviour.Properties.copy(Blocks.WATER),Blocks.AIR));

    public static final RegistryObject<LiquidBlock> LIQUID_HONEY_BLOCK = BLOCKS.register("liquid_honey_block",
            () -> new LiquidHoneyBlock(ModFluids.SOURCE_HONEY, BlockBehaviour.Properties.copy(Blocks.WATER)));

    public static final RegistryObject<Block> CEMENT_TRANSMUTATION_BLOCK = BLOCKS.register("cement_transmutation_block",
            () -> new TransmutationBlock(BlockBehaviour.Properties.copy(Blocks.BEDROCK),ModBlocks.LIQUID_CEMENT_BLOCK.get()));

    public static final RegistryObject<Block> ACETONE_TRANSMUTATION_BLOCK = BLOCKS.register("acetone_transmutation_block",
            () -> new TransmutationBlock(BlockBehaviour.Properties.copy(Blocks.BEDROCK),ModBlocks.ACETONE_BLOCK.get()));

    public static final RegistryObject<Block> HONEY_TRANSMUTATION_BLOCK = BLOCKS.register("honey_transmutation_block",
            () -> new TransmutationBlock(BlockBehaviour.Properties.copy(Blocks.BEDROCK),ModBlocks.LIQUID_HONEY_BLOCK.get()));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);

        return toReturn;
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
