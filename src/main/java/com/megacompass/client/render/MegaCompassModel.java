package com.megacompass.client.render;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import org.jetbrains.annotations.Nullable;

import com.megacompass.MegaCompass;

import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.Baker;
import net.minecraft.client.render.model.ModelBakeSettings;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;

public class MegaCompassModel implements UnbakedModel {

    private final Identifier baseId;
    private final Identifier pointerId;

    public MegaCompassModel(String itemName) {
        this.baseId = Identifier.of(MegaCompass.MODID, "item/" + itemName + "_base");
        this.pointerId = Identifier.of(MegaCompass.MODID, "item/compass_pointer_preset");
    }

    @Override
    public Collection<Identifier> getModelDependencies() {
        return List.of(baseId, pointerId);
    }

    @Override
    public void setParents(Function<Identifier, UnbakedModel> modelLoader) {
        modelLoader.apply(baseId).setParents(modelLoader);
        modelLoader.apply(pointerId).setParents(modelLoader);
    }

    @Nullable
    @Override
    public BakedModel bake(Baker baker, Function<SpriteIdentifier, Sprite> textureGetter, ModelBakeSettings rotationContainer) {
        BakedModel baseModel = baker.bake(baseId, rotationContainer);
        BakedModel pointerModel = baker.bake(pointerId, rotationContainer);
        if (baseModel == null || pointerModel == null) {
            return null;
        }
        return new MegaCompassBakedModel(baseModel, pointerModel);
    }
}
