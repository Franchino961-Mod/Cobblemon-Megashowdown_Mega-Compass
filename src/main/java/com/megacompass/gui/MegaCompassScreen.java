package com.megacompass.gui;

import com.megacompass.util.StructureUtils;
import com.megacompass.network.SearchPacket;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class MegaCompassScreen extends Screen {

    private static final int PANEL_WIDTH = 260;
    private static final int PANEL_HEIGHT = 170;
    private static final int BUTTON_WIDTH = 220;
    private static final int BUTTON_HEIGHT = 20;
    private static final int BUTTON_SPACING = 24;

    private final ItemStack compassStack;

    public MegaCompassScreen(ItemStack compassStack) {
        super(Text.translatable("screen.mega_compass.title"));
        this.compassStack = compassStack;
    }

    @Override
    protected void init() {
        int panelX = (this.width - PANEL_WIDTH) / 2;
        int panelY = (this.height - PANEL_HEIGHT) / 2;
        int firstButtonX = panelX + (PANEL_WIDTH - BUTTON_WIDTH) / 2;
        
        // Point 15: Relative coordinates instead of magic numbers
        int startButtonY = panelY + 70;

        // Megaroid button
        this.addDrawableChild(ButtonWidget.builder(
                Text.translatable("string.mega_compass.megaroid"),
                button -> onStructureSelected(StructureUtils.MEGAROID))
            .dimensions(firstButtonX, startButtonY, BUTTON_WIDTH, BUTTON_HEIGHT).build());

        // Mega Site button
        this.addDrawableChild(ButtonWidget.builder(
                Text.translatable("string.mega_compass.mega_site"),
                button -> onStructureSelected(StructureUtils.MEGA_SITE))
            .dimensions(firstButtonX, startButtonY + BUTTON_SPACING, BUTTON_WIDTH, BUTTON_HEIGHT).build());

        // Wishing Weald button
        this.addDrawableChild(ButtonWidget.builder(
                Text.translatable("string.mega_compass.wishing_weald"),
                button -> onStructureSelected(StructureUtils.WISHING_WEALD))
            .dimensions(firstButtonX, startButtonY + (BUTTON_SPACING * 2), BUTTON_WIDTH, BUTTON_HEIGHT).build());
    }

    private void onStructureSelected(Identifier structureId) {
        ClientPlayNetworking.send(new SearchPacket(structureId.toString()));
        
        // Point 16: Visual feedback in chat
        if (MinecraftClient.getInstance().player != null) {
            Text structureName = Text.translatable("string.mega_compass." + structureId.getPath());
            MinecraftClient.getInstance().player.sendMessage(
                Text.translatable("string.mega_compass.status.searching", structureName, 0, 0), 
                true // Overlay message (above hotbar)
            );
        }
        
        this.close();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);

        int panelX = (this.width - PANEL_WIDTH) / 2;
        int panelY = (this.height - PANEL_HEIGHT) / 2;

        // Panel background
        context.fill(panelX, panelY, panelX + PANEL_WIDTH, panelY + PANEL_HEIGHT, 0xCC111111);
        context.fill(panelX, panelY, panelX + PANEL_WIDTH, panelY + 1, 0xFFAAAAAA);
        context.fill(panelX, panelY + PANEL_HEIGHT - 1, panelX + PANEL_WIDTH, panelY + PANEL_HEIGHT, 0xFFAAAAAA);
        context.fill(panelX, panelY, panelX + 1, panelY + PANEL_HEIGHT, 0xFFAAAAAA);
        context.fill(panelX + PANEL_WIDTH - 1, panelY, panelX + PANEL_WIDTH, panelY + PANEL_HEIGHT, 0xFFAAAAAA);

        // Separator
        context.fill(panelX + 15, panelY + 28, panelX + PANEL_WIDTH - 15, panelY + 29, 0xFF555555);

        super.render(context, mouseX, mouseY, delta);

        // Compass item icon
        context.drawItem(compassStack, this.width / 2 - 8, panelY + 34);

        context.getMatrices().push();
        context.getMatrices().translate(0, 0, 50);

        // Title
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, panelY + 12, 0xFFD700);

        // Subtitle
        context.drawCenteredTextWithShadow(
                this.textRenderer,
                Text.translatable("screen.mega_compass.choose"),
                this.width / 2, panelY + 55, 0xAAAAAA);

        // Reset info
        context.drawCenteredTextWithShadow(
                this.textRenderer,
                Text.translatable("string.mega_compass.use.reset"),
                this.width / 2, panelY + PANEL_HEIGHT - 25, 0x888888);

        context.getMatrices().pop();
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
