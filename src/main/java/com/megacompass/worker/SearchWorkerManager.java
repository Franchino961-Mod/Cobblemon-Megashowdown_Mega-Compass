package com.megacompass.worker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import com.megacompass.MegaCompass;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.chunk.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.gen.chunk.placement.StructurePlacement;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.structure.StructureSet;

public class SearchWorkerManager {

	private final String id = generateRandomId();
	private final List<StructureSearchWorker<?>> workers = new ArrayList<>();

	public void createWorkers(ServerWorld world, PlayerEntity player, ItemStack stack, List<Structure> structures,
			BlockPos startPos) {
		workers.clear();

		Map<StructurePlacement, List<Structure>> placementToStructuresMap = new Object2ObjectArrayMap<>();

		try {
			var structureSetRegistry = world.getRegistryManager().get(RegistryKeys.STRUCTURE_SET);
			for (Structure structure : structures) {
				for (StructureSet structureSet : structureSetRegistry) {
					boolean containsStructure = structureSet.structures().stream()
							.anyMatch(entry -> entry.structure().value().equals(structure));
					if (containsStructure) {
						placementToStructuresMap.computeIfAbsent(structureSet.placement(), p -> new ObjectArrayList<>())
								.add(structure);
					}
				}
			}
		} catch (Exception e) {
			MegaCompass.LOGGER.error("Failed to get real structure placements: {}", e.getMessage());
		}

		if (placementToStructuresMap.isEmpty()) {
			MegaCompass.LOGGER.warn(
					"No structure placements found for meteorite structures! Structures may not generate in this world.");
		}

		for (Map.Entry<StructurePlacement, List<Structure>> entry : placementToStructuresMap.entrySet()) {
			StructurePlacement placement = entry.getKey();
			if (placement instanceof RandomSpreadStructurePlacement rsp) {
				workers.add(new RandomSpreadSearchWorker(world, player, stack, startPos, rsp, entry.getValue(), id));
			} else {
				MegaCompass.LOGGER.debug("Skipping unsupported placement type: {}", placement.getClass().getSimpleName());
			}
		}
	}

	// Returns true if a worker starts, false otherwise
	public boolean start() {
		if (!workers.isEmpty()) {
			workers.get(0).start();
			return true;
		}
		return false;
	}

	public void pop() {
		if (!workers.isEmpty()) {
			workers.remove(0);
		}
	}

	public void stop() {
		for (StructureSearchWorker<?> worker : workers) {
			worker.stop();
		}
	}

	public void clear() {
		workers.clear();
	}

	private static String generateRandomId() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			sb.append(Integer.toHexString(ThreadLocalRandom.current().nextInt(16)));
		}
		return sb.toString();
	}

}
