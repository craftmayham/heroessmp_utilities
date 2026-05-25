package com.craftmayham.hsmputil.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TeamSelectPacket {

    private final String teamName;

    public TeamSelectPacket(String teamName) {
        this.teamName = teamName;
    }

    public static void encode(TeamSelectPacket packet, FriendlyByteBuf buf) {
        buf.writeUtf(packet.teamName);
    }

    public static TeamSelectPacket decode(FriendlyByteBuf buf) {
        return new TeamSelectPacket(buf.readUtf());
    }

    public static void handle(TeamSelectPacket packet, Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {

            ServerPlayer player = ctx.get().getSender();

            if (player == null) return;

            Scoreboard scoreboard = player.server.getScoreboard();

            PlayerTeam team = scoreboard.getPlayerTeam(packet.teamName);

            if (team != null) {

                scoreboard.addPlayerToTeam(
                        player.getScoreboardName(),
                        team
                );
            }
        });

        ctx.get().setPacketHandled(true);
    }
}