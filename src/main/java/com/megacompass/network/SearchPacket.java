package com.megacompass.network;

import com.megacompass.MegaCompass;
import com.megacompass.item.MegaCompassItem;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

public record SearchPacket(String structureId) implements CustomPayload {

    public static final Id<SearchPacket> TYPE = new Id<>(
            Identifier.of(MegaCompass.MODID, "search"));

    public static final PacketCodec<RegistryByteBuf, SearchPacket> CODEC = PacketCodec.of(SearchPacket::write,
            SearchPacket::read);

    public static SearchPacket read(RegistryByteBuf buf) {
        return new SearchPacket(buf.readString());
    }

    public void write(RegistryByteBuf buf) {
        buf.writeString(structureId);
    }

    public static void handle(SearchPacket packet, ServerPlayNetworking.Context context) {
        context.server().execute(() -> {
            ItemStack stack = context.player().getMainHandStack();
            if (stack.isEmpty() || !(stack.getItem() instanceof MegaCompassItem)) {
                stack = context.player().getOffHandStack();
            }

            if (!stack.isEmpty() && stack.getItem() instanceof MegaCompassItem compass) {
                Identifier targetId = Identifier.of(packet.structureId());
                compass.searchForMeteoriteStructures(
                        (ServerWorld) context.player().getWorld(),
                        context.player(),
                        context.player().getBlockPos(),
                        stack,
                        targetId);
            }
        });
    }

    @Override
    public Id<SearchPacket> getId() {
        return TYPE;
    }
}
