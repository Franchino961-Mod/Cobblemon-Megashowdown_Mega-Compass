package com.megacompass.client;

import com.megacompass.MegaCompass;
import com.megacompass.gui.MegaCompassScreen;
import com.megacompass.item.MegaCompassItem;
import com.megacompass.util.CompassState;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class MegaCompassClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MegaCompassItem.screenOpener = stack -> {
            MinecraftClient.getInstance().setScreen(new MegaCompassScreen(stack));
        };
    }

    public static float getCompassAngle(ItemStack stack, ClientWorld world, ClientPlayerEntity player, int seed) {
        if (world == null || player == null) {
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
