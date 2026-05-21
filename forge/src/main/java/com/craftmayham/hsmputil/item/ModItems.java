package com.craftmayham.hsmputil.item;

import com.craftmayham.hsmputil.HsmpMod;
import com.craftmayham.hsmputil.fluid.ModFluids;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HsmpMod.MODID);
    public static final RegistryObject<Item> LIQUID_CEMENT_BUCKET = ITEMS.register("liquid_cement_bucket",
            () -> new BucketItem(ModFluids.SOURCE_LIQUID_CEMENT,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> ACETONE_BUCKET = ITEMS.register("acetone_bucket",
            () -> new BucketItem(ModFluids.SOURCE_ACETONE,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> HONEY_BUCKET = ITEMS.register("honey_bucket",
            () -> new BucketItem(ModFluids.SOURCE_HONEY,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
