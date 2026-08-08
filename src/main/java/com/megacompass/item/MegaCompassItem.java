package com.megacompass.item;

import java.util.List;

import com.megacompass.MegaCompass;
import com.megacompass.util.CompassState;
import com.megacompass.util.StructureUtils;
import com.megacompass.worker.SearchWorkerManager;
import com.megacompass.worker.WorldWorkerManager;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.Structure;

public class MegaCompassItem extends Item {

    private final Identifier defaultTargetId;
    private final boolean opensGui;

    /**
     * Impostato dall'entrypoint client per aprire la GUI di selezione struttura.
     * Mantenuto come Consumer per evitare riferimenti a classi client-only in questa
     * classe comune (server+client), che crasherebbe su server dedicato.
     */
    public static java.util.function.Consumer<ItemStack> screenOpener = null;

    public MegaCompassItem(Settings settings, Identifier defaultTargetId, boolean opensGui) {
        super(settings);
        this.defaultTargetId = defaultTargetId;
        this.opensGui = opensGui;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        // Shift + Click destro = reset bussola
        if (player.isSneaking()) {
            if (!world.isClient) {
                WorldWorkerManager.stopWorkersForPlayer(player.getUuid());
            }
            setState(stack, CompassState.INACTIVE);
            return TypedActionResult.consume(stack);
        }

        if (opensGui) {
            // Bussola combinata: apre la GUI di selezione struttura (solo client, via callback)
            if (world.isClient() && screenOpener != null) {
                screenOpener.accept(stack);
            }
            return TypedActionResult.consume(stack);
        }

        // Bussole dirette: avvia la ricerca immediatamente sul server, senza GUI
        if (!world.isClient() && defaultTargetId != null) {
            searchForStructure(world, player, player.getBlockPos(), stack, defaultTargetId);
        }

        return TypedActionResult.consume(stack);
    }

    @Override
    public boolean allowComponentsUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack,
            ItemStack newStack) {
        if (getState(oldStack) == getState(newStack)) {
            return false;
        }
        return super.allowComponentsUpdateAnimation(player, hand, oldStack, newStack);
    }

    public void searchForStructure(World world, PlayerEntity player, BlockPos pos, ItemStack stack,
            Identifier targetId) {
        setSearching(stack, targetId);
        stack.set(MegaCompass.SEARCH_RADIUS_COMPONENT, 0);

        if (world instanceof ServerWorld serverWorld) {
            Structure targetStructure = StructureUtils.getStructureForId(serverWorld, targetId);

            if (targetStructure == null) {
                MegaCompass.LOGGER.error("Struttura sconosciuta o non presente nel mondo: {}", targetId);
                setNotFound(stack, 0, 0);
                return;
            }

            List<Structure> structures = List.of(targetStructure);

            WorldWorkerManager.stopWorkersForPlayer(player.getUuid());

            SearchWorkerManager workerManager = new SearchWorkerManager();
            workerManager.createWorkers(serverWorld, player, stack, structures, pos);
            boolean started = workerManager.start();

            if (!started) {
                MegaCompass.LOGGER.warn("Nessun worker avviato per la struttura {} — tipo di placement non supportato?", targetId);
                setNotFound(stack, 0, 0);
            }
        }
    }

    public void succeed(ItemStack stack, Identifier structureId, int x, int z, int samples,
            boolean displayCoordinates) {
        setFound(stack, structureId, x, z, samples);
    }

    public void fail(ItemStack stack, int radius, int samples) {
        setNotFound(stack, radius, samples);
    }

    public boolean isActive(ItemStack stack) {
        return getState(stack) != CompassState.INACTIVE;
    }

    public void setSearching(ItemStack stack, Identifier structureId) {
        stack.set(MegaCompass.STRUCTURE_ID_COMPONENT, structureId.toString());
        stack.set(MegaCompass.COMPASS_STATE_COMPONENT, CompassState.SEARCHING.getID());
    }

    public void setFound(ItemStack stack, Identifier structureId, int x, int z, int samples) {
        stack.set(MegaCompass.COMPASS_STATE_COMPONENT, CompassState.FOUND.getID());
        stack.set(MegaCompass.STRUCTURE_ID_COMPONENT, structureId.toString());
        stack.set(MegaCompass.FOUND_X_COMPONENT, x);
        stack.set(MegaCompass.FOUND_Z_COMPONENT, z);
        stack.set(MegaCompass.SAMPLES_COMPONENT, samples);
    }

    public void setNotFound(ItemStack stack, int searchRadius, int samples) {
        stack.set(MegaCompass.COMPASS_STATE_COMPONENT, CompassState.NOT_FOUND.getID());
        stack.set(MegaCompass.SEARCH_RADIUS_COMPONENT, searchRadius);
        stack.set(MegaCompass.SAMPLES_COMPONENT, samples);
    }

    public void setState(ItemStack stack, CompassState state) {
        stack.set(MegaCompass.COMPASS_STATE_COMPONENT, state.getID());
    }

    public CompassState getState(ItemStack stack) {
        if (stack.contains(MegaCompass.COMPASS_STATE_COMPONENT)) {
            return CompassState.fromID(stack.get(MegaCompass.COMPASS_STATE_COMPONENT));
        }
        return CompassState.INACTIVE;
    }

    public int getFoundStructureX(ItemStack stack) {
        if (stack.contains(MegaCompass.FOUND_X_COMPONENT)) {
            return stack.get(MegaCompass.FOUND_X_COMPONENT);
        }
        return 0;
    }

    public int getFoundStructureZ(ItemStack stack) {
        if (stack.contains(MegaCompass.FOUND_Z_COMPONENT)) {
            return stack.get(MegaCompass.FOUND_Z_COMPONENT);
        }
        return 0;
    }

    public Identifier getStructureId(ItemStack stack) {
        if (stack.contains(MegaCompass.STRUCTURE_ID_COMPONENT)) {
            return Identifier.of(stack.get(MegaCompass.STRUCTURE_ID_COMPONENT));
        }
        return null;
    }

    public int getSearchRadius(ItemStack stack) {
        if (stack.contains(MegaCompass.SEARCH_RADIUS_COMPONENT)) {
            return stack.get(MegaCompass.SEARCH_RADIUS_COMPONENT);
        }
        return -1;
    }

    public int getSamples(ItemStack stack) {
        if (stack.contains(MegaCompass.SAMPLES_COMPONENT)) {
            return stack.get(MegaCompass.SAMPLES_COMPONENT);
        }
        return -1;
    }

    public int getDistanceToStructure(PlayerEntity player, ItemStack stack) {
        return StructureUtils.getHorizontalDistanceToLocation(player, getFoundStructureX(stack),
                getFoundStructureZ(stack));
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip,
            net.minecraft.item.tooltip.TooltipType tooltipType) {
        super.appendTooltip(stack, context, tooltip, tooltipType);

        CompassState state = getState(stack);
        Identifier targetId = getStructureId(stack);

        if (state == CompassState.FOUND && targetId != null) {
            Text structureName = Text.translatable("string.mega_compass." + targetId.getPath());
            int x = getFoundStructureX(stack);
            int z = getFoundStructureZ(stack);
            tooltip.add(Text.translatable("string.mega_compass.hud.found").append(": ").append(structureName).withColor(0x55FF55));
            tooltip.add(Text.literal("X: " + x + ", Z: " + z).withColor(0xAAFFAA));
        } else if (state == CompassState.SEARCHING && targetId != null) {
            Text structureName = Text.translatable("string.mega_compass." + targetId.getPath());
            int radius = getSearchRadius(stack);
            int samples = getSamples(stack);
            tooltip.add(Text.translatable("string.mega_compass.status.searching", structureName, Math.max(0, samples), Math.max(0, radius)).withColor(0xFFFF55));
        } else if (state == CompassState.NOT_FOUND) {
            int radius = getSearchRadius(stack);
            int samples = getSamples(stack);
            tooltip.add(Text.translatable("string.mega_compass.status.not_found", Math.max(0, radius), Math.max(0, samples)).withColor(0xFF5555));
        } else {
            String baseKey = this.getTranslationKey() + ".lore";
            Text loreText = Text.translatable(baseKey);
            String loreString = loreText.getString();
            for (String line : loreString.split("\n")) {
                if (!line.isEmpty()) {
                    tooltip.add(Text.literal(line).withColor(0x7F7F7F));
                }
            }
        }
    }
}