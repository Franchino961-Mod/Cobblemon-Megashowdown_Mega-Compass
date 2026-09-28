package com.megacompass.client.render;

import com.megacompass.item.MegaCompassItem;
import com.megacompass.util.CompassState;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CompassHudRenderer implements HudRenderCallback {

    private static final int HUD_X         = 4;
    private static final int HUD_Y         = 4;
    private static final int LINE_HEIGHT   = 9;  // height of one text line
    private static final int SECTION_GAP   = 6;  // vertical gap between label-value pairs

    // Label color (bright white, like the screenshot)
    private static final int COLOR_LABEL    = 0xFFFFFF;
    // Value colors per state
    private static final int COLOR_VALUE    = 0xAAAAAA; // grey for neutral values
    private static final int COLOR_FOUND    = 0x55FF55; // green
    private static final int COLOR_SEARCH   = 0xFFFF55; // yellow
    private static final int COLOR_MISSING  = 0xFF5555; // red

    @Override
    public void onHudRender(DrawContext context, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null) return;
        if (client.options.hudHidden) return;
        if (client.currentScreen != null) return;

        ItemStack stack = findCompassStack(client);
        if (stack == null || !(stack.getItem() instanceof MegaCompassItem compassItem)) return;

        CompassState state = compassItem.getState(stack);
        if (state == CompassState.INACTIVE) return;

        int y = HUD_Y;

        // --- Status row ---
        y = drawRow(context, client,
                "string.mega_compass.hud.status",
                getStateText(state), getStateColor(state),
                y);

        switch (state) {

            case FOUND -> {
                Identifier structureId = compassItem.getStructureId(stack);
                Text structureName = structureId != null
                        ? Text.translatable("string.mega_compass." + structureId.getPath())
                        : Text.literal("?");
                int foundX   = compassItem.getFoundStructureX(stack);
                int foundZ   = compassItem.getFoundStructureZ(stack);
                int distance = compassItem.getDistanceToStructure(client.player, stack);

                // Structure
                y = drawRow(context, client,
                        "string.mega_compass.hud.structure",
                        structureName, COLOR_VALUE, y);

                // Coordinates  —  format: "2304, 320" (matching screenshot)
                y = drawRow(context, client,
                        "string.mega_compass.hud.coordinates",
                        Text.literal(foundX + ", " + foundZ), COLOR_VALUE, y);

                // Distance  —  format: "184" (matching screenshot)
                y = drawRow(context, client,
                        "string.mega_compass.hud.distance",
                        Text.literal(String.valueOf(distance)), COLOR_VALUE, y);
            }

            case SEARCHING -> {
                Identifier structureId = compassItem.getStructureId(stack);
                Text structureName = structureId != null
                        ? Text.translatable("string.mega_compass." + structureId.getPath())
                        : Text.literal("?");
                int samples = Math.max(0, compassItem.getSamples(stack));
                int radius  = Math.max(0, compassItem.getSearchRadius(stack));

                y = drawRow(context, client,
                        "string.mega_compass.hud.structure",
                        structureName, COLOR_VALUE, y);

                y = drawRow(context, client,
                        "string.mega_compass.hud.samples",
                        Text.literal(String.valueOf(samples)), COLOR_VALUE, y);

                y = drawRow(context, client,
                        "string.mega_compass.hud.radius",
                        Text.literal(radius + " blocks"), COLOR_VALUE, y);
            }

            case NOT_FOUND -> {
                int radius  = Math.max(0, compassItem.getSearchRadius(stack));
                int samples = Math.max(0, compassItem.getSamples(stack));

                y = drawRow(context, client,
                        "string.mega_compass.hud.radius",
                        Text.literal(radius + " blocks"), COLOR_VALUE, y);

                y = drawRow(context, client,
                        "string.mega_compass.hud.samples",
                        Text.literal(String.valueOf(samples)), COLOR_VALUE, y);
            }

            default -> { /* INACTIVE already filtered above */ }
        }
    }

    /**
     * Draws a label line (white) and a value line (valueColor) at the given y,
     * then returns the new y position for the next section.
     */
    private int drawRow(DrawContext context, MinecraftClient client,
                        String labelKey, Text value, int valueColor, int y) {
        context.drawTextWithShadow(client.textRenderer,
                Text.translatable(labelKey), HUD_X, y, COLOR_LABEL);
        y += LINE_HEIGHT;
        context.drawTextWithShadow(client.textRenderer,
                value, HUD_X, y, valueColor);
        y += LINE_HEIGHT + SECTION_GAP;
        return y;
    }

    private Text getStateText(CompassState state) {
        return switch (state) {
            case FOUND     -> Text.translatable("string.mega_compass.hud.found");
            case SEARCHING -> Text.translatable("string.mega_compass.hud.searching");
            case NOT_FOUND -> Text.translatable("string.mega_compass.hud.not_found");
            default        -> Text.translatable("string.mega_compass.status.inactive");
        };
    }

    private int getStateColor(CompassState state) {
        return switch (state) {
            case FOUND     -> COLOR_FOUND;
            case SEARCHING -> COLOR_SEARCH;
            case NOT_FOUND -> COLOR_MISSING;
            default        -> COLOR_VALUE;
        };
    }

    /**
     * Returns the first MegaCompassItem found in main hand or offhand, or null.
     */
    private ItemStack findCompassStack(MinecraftClient client) {
        ItemStack main = client.player.getMainHandStack();
        if (main.getItem() instanceof MegaCompassItem) return main;

        ItemStack off = client.player.getOffHandStack();
        if (off.getItem() instanceof MegaCompassItem) return off;

        return null;
    }
}
