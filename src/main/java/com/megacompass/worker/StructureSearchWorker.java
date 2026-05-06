package com.megacompass.worker;

import java.util.List;
import java.util.UUID;

import com.megacompass.MegaCompass;
import com.megacompass.item.MegaCompassItem;
import com.megacompass.util.StructureUtils;
import com.mojang.datafixers.util.Pair;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.gen.chunk.placement.StructurePlacement;
import net.minecraft.world.gen.structure.Structure;

public abstract class StructureSearchWorker<T extends StructurePlacement> implements WorldWorkerManager.IWorker {

	protected static final int MAX_RADIUS = 10000; // 10000 blocks
	protected static final int MAX_SAMPLES = 100000; // Maximum samples

	protected final String managerId;
	protected final ServerWorld world;
	protected final UUID playerUuid;
	protected final ItemStack stack;
	protected final BlockPos startPos;
	protected BlockPos currentPos;
	protected final T placement;
	protected final List<Structure> structureSet;
	protected int samples;
	protected boolean finished;
	protected int lastRadiusThreshold;

	public StructureSearchWorker(ServerWorld world, PlayerEntity player, ItemStack stack, BlockPos startPos,
			T placement, List<Structure> structureSet, String managerId) {
		this.world = world;
		this.playerUuid = player.getUuid();
		this.stack = stack;
		this.startPos = startPos;
		this.structureSet = structureSet;
		this.placement = placement;
		this.managerId = managerId;

		currentPos = startPos;
		samples = 0;

		finished = !world.getServer().getSaveProperties().getGeneratorOptions().shouldGenerateStructures();
	}

	public void start() {
		if (MegaCompass.isMegaCompassItem(stack)) {
			MegaCompass.LOGGER.info("SearchWorkerManager {}: {} starting with {} max samples{}", 
					managerId, getName(), MAX_SAMPLES, shouldLogRadius() ? ", " + MAX_RADIUS + " max radius" : "");
			WorldWorkerManager.addWorker(this);
		}
	}

	@Override
	public boolean hasWork() {
		return !finished && getRadius() < MAX_RADIUS && samples < MAX_SAMPLES;
	}

	@Override
	public boolean doWork() {
		int radius = getRadius();
		if (radius > 250 && radius / 250 > lastRadiusThreshold) {
			if (MegaCompass.isMegaCompassItem(stack)) {
				stack.set(MegaCompass.SEARCH_RADIUS_COMPONENT, roundRadius(radius, 250));
			}
			lastRadiusThreshold = radius / 250;
		}
		return false;
	}

    @Override
    public UUID getOwnerUuid() {
        return playerUuid;
    }

	protected Pair<BlockPos, Structure> getStructureGeneratingAt(ChunkPos chunkPos) {
		for (Structure structure : structureSet) {
			Chunk chunk = world.getChunk(chunkPos.x, chunkPos.z, ChunkStatus.STRUCTURE_STARTS, true);
			if (chunk != null) {
				StructureStart structureStart = world.getStructureAccessor()
						.getStructureStart(ChunkSectionPos.from(chunkPos, 0), structure, chunk);
				if (structureStart != null && structureStart.hasChildren()) {
					BlockPos pos = placement.getLocatePos(structureStart.getPos());
					return Pair.of(pos, structure);
				}
			}
		}

		return null;
	}

	protected void succeed(BlockPos pos, Structure structure) {
		MegaCompass.LOGGER.info("SearchWorkerManager {}: {} succeeded with {} samples{}", 
				managerId, getName(), samples, shouldLogRadius() ? " and " + getRadius() + " radius" : "");
		
		if (MegaCompass.isMegaCompassItem(stack)) {
			((MegaCompassItem) stack.getItem()).succeed(stack, StructureUtils.getIdForStructure(world, structure),
					pos.getX(), pos.getZ(), samples, true);
		} else {
			MegaCompass.LOGGER.error("SearchWorkerManager {}: {} found invalid compass after successful search", 
					managerId, getName());
		}
		finished = true;
	}

	protected void fail() {
		MegaCompass.LOGGER.info("SearchWorkerManager {}: {} failed after {} samples{}", 
				managerId, getName(), samples, shouldLogRadius() ? " and " + getRadius() + " radius" : "");
		
		if (MegaCompass.isMegaCompassItem(stack)) {
			((MegaCompassItem) stack.getItem()).fail(stack, roundRadius(getRadius(), 250), samples);
		} else {
			MegaCompass.LOGGER.error("SearchWorkerManager {}: {} found invalid compass after failed search", 
					managerId, getName());
		}
		finished = true;
	}

	@Override
	public void stop() {
		MegaCompass.LOGGER.info("SearchWorkerManager {}: {} stopped", managerId, getName());
		finished = true;
	}

	protected int getRadius() {
		return StructureUtils.getHorizontalDistanceToLocation(startPos, currentPos.getX(), currentPos.getZ());
	}

	protected int roundRadius(int radius, int roundTo) {
		return (radius / roundTo) * roundTo;
	}

	protected abstract String getName();

	protected abstract boolean shouldLogRadius();

}
