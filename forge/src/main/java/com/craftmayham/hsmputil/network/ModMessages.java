package com.craftmayham.hsmputil.network;

import com.craftmayham.hsmputil.HsmpMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {

    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel INSTANCE =
            NetworkRegistry.newSimpleChannel(
                    new ResourceLocation(HsmpMod.MODID, "messages"),
                    () -> PROTOCOL_VERSION,
                    PROTOCOL_VERSION::equals,
                    PROTOCOL_VERSION::equals
            );

    private static int packetId = 0;

    public static void register() {

        INSTANCE.registerMessage(
                packetId++,
                TeamSelectPacket.class,
                TeamSelectPacket::encode,
                TeamSelectPacket::decode,
                TeamSelectPacket::handle
        );

        INSTANCE.registerMessage(
                packetId++,
                TeamOpenPacket.class,
                TeamOpenPacket::encode,
                TeamOpenPacket::decode,
                TeamOpenPacket::handle
        );
    }
}
