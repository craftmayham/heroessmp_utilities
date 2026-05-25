package com.craftmayham.hsmputil.gui;

import com.craftmayham.hsmputil.network.ModMessages;
import com.craftmayham.hsmputil.network.TeamSelectPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class HeroOrVillainScreen extends Screen {
    public HeroOrVillainScreen(Component title) {
        super(title);
    }
    @Override
    protected void init() {
        addRenderableWidget(
                Button.builder(
                        Component.literal("Hero"),
                        btn -> {
                            System.out.println("Hero clicked");
                            ModMessages.INSTANCE.sendToServer(new TeamSelectPacket("Hero"));
                            Minecraft.getInstance().setScreen(null);
                        }
                ).bounds(this.width / 2 - 120, this.height / 2, 80, 20).build()
        );

        addRenderableWidget(
                Button.builder(
                        Component.literal("Villain"),
                        btn -> {
                            System.out.println("Villain clicked");
                            ModMessages.INSTANCE.sendToServer(new TeamSelectPacket("Villain"));
                            Minecraft.getInstance().setScreen(null);
                        }
                ).bounds(this.width / 2 - -40, this.height / 2, 80, 20).build()
        );
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {

        this.renderBackground(graphics);

        graphics.drawCenteredString(
                this.font,
                "Choose a team",
                this.width / 2,
                40,
                0xFFFFFF
        );


        super.render(graphics, mouseX, mouseY, partialTick);
    }
}
