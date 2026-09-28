package com.megacompass.network;

import com.megacompass.MegaCompass;
import com.megacompass.item.MegaCompassItem;
import com.megacompass.util.StructureUtils;

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

    public static final PacketCodec<RegistryByteBuf, SearchPacket> CODEC = PacketCodec.of(
            SearchPacket::write,
            SearchPacket::read);

    public static SearchPacket read(RegistryByteBuf buf) {
        return new SearchPacket(buf.readString());
    }

    public void write(RegistryByteBuf buf) {
        buf.writeString(structureId);
    }

    public static void handle(SearchPacket packet, ServerPlayNetworking.Context context) {
        context.server().execute(() -> {
            // Cast sicuro con instanceof
            if (!(context.player().getWorld() instanceof ServerWorld serverWorld)) {
                MegaCompass.LOGGER.warn("SearchPacket ricevuto da {} ma il mondo non è un ServerWorld.",
                        context.player().getName().getString());
                return;
            }

            // Valida l'ID struttura prima di usarlo
            Identifier targetId;
            try {
                targetId = Identifier.of(packet.structureId());
            } catch (Exception e) {
                MegaCompass.LOGGER.warn("Player {} ha inviato un structureId malformato: {}",
                        context.player().getName().getString(), packet.structureId());
                return;
            }

            if (!targetId.equals(StructureUtils.MEGAROID) &&
                !targetId.equals(StructureUtils.MEGA_SITE) &&
                !targetId.equals(StructureUtils.WISHING_WEALD) &&
                !targetId.equals(StructureUtils.OBSERVATORY) &&
                !targetId.equals(StructureUtils.ARCHAEOLOGICAL_SITE)) {
                MegaCompass.LOGGER.warn("Player {} ha inviato un ID struttura non valido: {}",
                        context.player().getName().getString(), targetId);
                return;
            }

            if (serverWorld.getRegistryKey() != net.minecraft.world.World.OVERWORLD) {
                context.player().sendMessage(net.minecraft.text.Text.translatable("string.mega_compass.status.wrong_dimension").withColor(0xFF5555), true);
                return;
            }

            // Cerca in mano principale, poi in mano secondaria
            ItemStack stack = context.player().getMainHandStack();
            if (stack.isEmpty() || !(stack.getItem() instanceof MegaCompassItem)) {
                stack = context.player().getOffHandStack();
            }

            if (!stack.isEmpty() && stack.getItem() instanceof MegaCompassItem compass) {
                // If not the master combined compass (opensGui), enforce that targetId matches the fixed structure
                if (!compass.opensGui() && !targetId.equals(compass.getDefaultTargetId())) {
                    MegaCompass.LOGGER.warn("Player {} ha tentato di cercare {} con una bussola non autorizzata ({})!",
                            context.player().getName().getString(), targetId, compass.getDefaultTargetId());
                    return;
                }

                context.player().getItemCooldownManager().set(compass, 20);
                compass.searchForStructure(
                        serverWorld,
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