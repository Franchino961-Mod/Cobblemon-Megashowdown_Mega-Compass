package com.megacompass.client;

import com.megacompass.MegaCompass;
import com.megacompass.client.render.CompassHudRenderer;
import com.megacompass.client.render.MegaCompassModel;
import com.megacompass.gui.MegaCompassScreen;
import com.megacompass.item.MegaCompassItem;
import com.megacompass.util.CompassState;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import java.util.List;

@Environment(EnvType.CLIENT)
public class MegaCompassClient implements ClientModInitializer {

    private static final List<String> COMPASS_NAMES = List.of(
        "mega_compass",
        "mega_site_compass",
        "megaroid_compass",
        "wishing_weald_compass",
        "observatory_compass",
        "archaeological_compass"
    );

    @Override
    public void onInitializeClient() {
        // Register HUD overlay (top-left status: Searching / Found / Not Found)
        HudRenderCallback.EVENT.register(new CompassHudRenderer());

        MegaCompassItem.screenOpener = stack -> {
            MinecraftClient.getInstance().setScreen(new MegaCompassScreen(stack));
        };

        // Register the custom 3D model for each compass item.
        // Without this, Minecraft falls back to the item/generated JSON models,
        // which reference textures that don't exist (causing purple/black squares).
        ModelLoadingPlugin.register(ctx -> {
            for (String compassName : COMPASS_NAMES) {
                final String name = compassName;
                Identifier modelId = Identifier.of(MegaCompass.MODID, "item/" + name);
                ctx.resolveModel().register(resolveCtx -> {
                    if (resolveCtx.id().equals(modelId)) {
                        return new MegaCompassModel(name);
                    }
                    return null;
                });
            }
        });
    }

    public static float getCompassAngle(ItemStack stack, ClientWorld world, ClientPlayerEntity player, int seed) {
        if (world == null || player == null || world.getRegistryKey() != net.minecraft.world.World.OVERWORLD) {
            return 0.0f;
        }

        if (!stack.contains(MegaCompass.COMPASS_STATE_COMPONENT)) {
            return 0.0f;
        }

        int state = stack.get(MegaCompass.COMPASS_STATE_COMPONENT);
        if (state != CompassState.FOUND.getID()) {
            return 0.0f;
        }

        if (!stack.contains(MegaCompass.FOUND_X_COMPONENT) || !stack.contains(MegaCompass.FOUND_Z_COMPONENT)) {
            return 0.0f;
        }

        int targetX = stack.get(MegaCompass.FOUND_X_COMPONENT);
        int targetZ = stack.get(MegaCompass.FOUND_Z_COMPONENT);

        BlockPos targetPos = new BlockPos(targetX, 0, targetZ);
        double angle = getAngleTo(player, targetPos);
        return (float) MathHelper.floorMod(angle, 1.0);
    }

    private static double getAngleTo(ClientPlayerEntity player, BlockPos targetPos) {
        double dx = targetPos.getX() + 0.5 - player.getX();
        double dz = targetPos.getZ() + 0.5 - player.getZ();
        double yawRads = Math.toRadians(player.getYaw());
        double angleToTarget = Math.atan2(dz, dx);
        double relativeAngle = angleToTarget - yawRads - (Math.PI / 2.0);
        return relativeAngle / (Math.PI * 2.0);
    }
}
