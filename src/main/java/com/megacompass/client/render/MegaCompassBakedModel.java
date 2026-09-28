package com.megacompass.client.render;

import java.util.List;
import java.util.function.Supplier;

import org.jetbrains.annotations.Nullable;

import com.megacompass.MegaCompass;
import com.megacompass.client.MegaCompassClient;
import com.megacompass.util.CompassState;

import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.json.ModelOverrideList;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.Sprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;

public class MegaCompassBakedModel implements BakedModel {

    private final BakedModel baseModel;
    private final BakedModel pointerModel;

    public MegaCompassBakedModel(BakedModel base, BakedModel pointer) {
        this.baseModel = base;
        this.pointerModel = pointer;
    }

    @Override
    public boolean isVanillaAdapter() {
        return false;
    }

    @Override
    public void emitItemQuads(ItemStack stack, Supplier<Random> randomSupplier, RenderContext context) {
        // 1. Render Base
        ((FabricBakedModel) baseModel).emitItemQuads(stack, randomSupplier, context);

        // 2. Calcola Rotazione
        float rotationRads = 0.0f;
        if (stack != null && stack.contains(MegaCompass.COMPASS_STATE_COMPONENT)) {
            int state = stack.getOrDefault(MegaCompass.COMPASS_STATE_COMPONENT, CompassState.INACTIVE.getID());
            if (state == CompassState.FOUND.getID()) {
                net.minecraft.client.MinecraftClient client = net.minecraft.client.MinecraftClient.getInstance();
                float angle = MegaCompassClient.getCompassAngle(stack, client.world, client.player, 0);
                rotationRads = angle * (float) Math.PI * 2.0f;
            } else if (state == CompassState.SEARCHING.getID()) {
                rotationRads = (float) (((System.currentTimeMillis() / 50) % 360) / 360.0 * Math.PI * 2);
            }
        }

        // 3. Render Pointer con trasformazione a zero-allocazioni heap
        final float cos = (float) Math.cos(rotationRads);
        final float sin = (float) Math.sin(rotationRads);
        context.pushTransform(quad -> {
            for (int i = 0; i < 4; i++) {
                float px = quad.x(i) - 0.5f;
                float py = quad.y(i);
                float pz = quad.z(i) - 0.5f;

                float rx = px * cos + pz * sin;
                float rz = -px * sin + pz * cos;

                quad.pos(i, rx + 0.5f, py, rz + 0.5f);
            }
            return true;
        });

        ((FabricBakedModel) pointerModel).emitItemQuads(stack, randomSupplier, context);
        context.popTransform();
    }

    @Override
    public void emitBlockQuads(net.minecraft.world.BlockRenderView blockView, BlockState state, BlockPos pos,
            Supplier<Random> randomSupplier, RenderContext context) {
        // Solo per item
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction face, Random random) {
        return baseModel.getQuads(state, face, random);
    }

    @Override
    public boolean useAmbientOcclusion() {
        return baseModel.useAmbientOcclusion();
    }

    @Override
    public boolean hasDepth() {
        return baseModel.hasDepth();
    }

    @Override
    public boolean isSideLit() {
        return baseModel.isSideLit();
    }

    @Override
    public boolean isBuiltin() {
        return false;
    }

    @Override
    public Sprite getParticleSprite() {
        return baseModel.getParticleSprite();
    }

    @Override
    public ModelTransformation getTransformation() {
        return baseModel.getTransformation();

    }

    @Override
    public ModelOverrideList getOverrides() {
        return ModelOverrideList.EMPTY;
    }
}
