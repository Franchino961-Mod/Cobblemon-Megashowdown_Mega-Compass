package com.megacompass.worker;

import java.util.List;

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

	protected String managerId;
	protected ServerWorld world;
	protected PlayerEntity player;
	protected ItemStack stack;
	protected BlockPos startPos;
	protected BlockPos currentPos;
	protected T placement;
	protected List<Structure> structureSet;
	protected int samples;
	protected boolean finished;
	protected int lastRadiusThreshold;

	public StructureSearchWorker(ServerWorld world, PlayerEntity player, ItemStack stack, BlockPos startPos,
			T placement, List<Structure> structureSet, String managerId) {
		this.world = world;
		this.player = player;
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
			if (MAX_RADIUS > 0) {
				MegaCompass.LOGGER.info("SearchWorkerManager " + managerId + ": " + getName() + " starting with "
						+ (shouldLogRadius() ? MAX_RADIUS + " max radius, " : "") + MAX_SAMPLES + " max samples");
				WorldWorkerManager.addWorker(this);
			} else {
				fail();
			}
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

	protected Pair<BlockPos, Structure> getStructureGeneratingAt(ChunkPos chunkPos) {
		for (Structure structure : structureSet) {
			// FIX 2: use true (not false) so the chunk is generated up to STRUCTURE_STARTS
			// status if needed. STRUCTURE_STARTS is the very first (lightest) generation
			// phase and is required to detect structures in unloaded/unexplored areas.
			Chunk chunk = world.getChunk(chunkPos.x, chunkPos.z, ChunkStatus.STRUCTURE_STARTS, true);
			if (chunk != null) {
				StructureStart structureStart = world.getStructureAccessor()
						.getStructureStart(ChunkSectionPos.from(chunkPos, 0), structure, chunk);
				if (structureStart != null && structureStart.hasChildren()) {
					// FIX 3: use placement.getLocatePos() for the canonical structure position
					// instead of a hardcoded +8, Y=64 offset.
					BlockPos pos = placement.getLocatePos(structureStart.getPos());
					return Pair.of(pos, structure);
				}
			}
		}

		return null;
	}

	protected void succeed(BlockPos pos, Structure structure) {
		MegaCompass.LOGGER.info("SearchWorkerManager " + managerId + ": " + getName() + " succeeded with "
				+ (shouldLogRadius() ? getRadius() + " radius, " : "") + samples + " samples");
		if (MegaCompass.isMegaCompassItem(stack)) {
			((MegaCompassItem) stack.getItem()).succeed(stack, StructureUtils.getIdForStructure(world, structure),
					pos.getX(), pos.getZ(), samples, true);
		} else {
			MegaCompass.LOGGER.error("SearchWorkerManager " + managerId + ": " + getName()
					+ " found invalid compass after successful search");
		}
		finished = true;
	}

	protected void fail() {
		MegaCompass.LOGGER.info("SearchWorkerManager " + managerId + ": " + getName() + " failed with "
				+ (shouldLogRadius() ? getRadius() + " radius, " : "") + samples + " samples");
		if (MegaCompass.isMegaCompassItem(stack)) {
			((MegaCompassItem) stack.getItem()).fail(stack, roundRadius(getRadius(), 250), samples);
		} else {
			MegaCompass.LOGGER.error("SearchWorkerManager " + managerId + ": " + getName()
					+ " found invalid compass after failed search");
		}
		finished = true;
	}

	public void stop() {
		MegaCompass.LOGGER.info("SearchWorkerManager " + managerId + ": " + getName() + " stopped with "
				+ (shouldLogRadius() ? getRadius() + " radius, " : "") + samples + " samples");
		finished = true;
	}

	protected int getRadius() {
		return StructureUtils.getHorizontalDistanceToLocation(startPos, currentPos.getX(), currentPos.getZ());
	}

	protected int roundRadius(int radius, int roundTo) {
		return ((int) radius / roundTo) * roundTo;
	}

	protected abstract String getName();

	protected abstract boolean shouldLogRadius();

}
