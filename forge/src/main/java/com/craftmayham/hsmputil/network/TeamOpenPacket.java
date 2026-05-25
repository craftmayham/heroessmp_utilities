package com.craftmayham.hsmputil.network;

import com.craftmayham.hsmputil.gui.HeroOrVillainScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TeamOpenPacket {

    public static void encode(TeamOpenPacket msg, FriendlyByteBuf buf) {

    }

    public static TeamOpenPacket decode(FriendlyByteBuf buf) {
        return new TeamOpenPacket();
    }

    public static void handle(TeamOpenPacket msg, Supplier<NetworkEvent.Context> ctx) {


        ctx.get().enqueueWork(() -> {

            Minecraft.getInstance().setScreen(
                    new HeroOrVillainScreen(Component.literal("Hero or Villain?"))
            );
        });

        ctx.get().setPacketHandled(true);
    }
}