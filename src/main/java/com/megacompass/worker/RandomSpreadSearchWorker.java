package com.megacompass.worker;

import java.util.List;

import com.mojang.datafixers.util.Pair;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.gen.chunk.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.gen.structure.Structure;

public class RandomSpreadSearchWorker extends StructureSearchWorker<RandomSpreadStructurePlacement> {

	private int spacing;
	private int length;
	// Starting chunk coordinates (not region coords — getStartChunk takes chunk
	// coords directly)
	private int startChunkX;
	private int startChunkZ;
	private int x;
	private int z;

	public RandomSpreadSearchWorker(ServerWorld world, PlayerEntity player, ItemStack stack, BlockPos startPos,
			RandomSpreadStructurePlacement placement, List<Structure> structureSet, String managerId) {
		super(world, player, stack, startPos, placement, structureSet, managerId);

		spacing = placement.getSpacing();
		// getStartChunk() expects chunk coordinates; we step in multiples of spacing
		startChunkX = ChunkSectionPos.getSectionCoord(startPos.getX());
		startChunkZ = ChunkSectionPos.getSectionCoord(startPos.getZ());
		x = 0;
		z = 0;
		length = 0;

		finished = !world.getServer().getSaveProperties().getGeneratorOptions().shouldGenerateStructures();
	}

	@Override
	public boolean hasWork() {
		return super.hasWork();
	}

	@Override
	public boolean doWork() {
		super.doWork();
		if (hasWork()) {
			boolean shouldSampleX = x == -length || x == length;
			boolean shouldSampleZ = z == -length || z == length;

			if (shouldSampleX || shouldSampleZ) {
				// Step by one spacing-width per ring — getStartChunk receives chunk coords
				int sampleX = startChunkX + (spacing * x);
				int sampleZ = startChunkZ + (spacing * z);

				ChunkPos chunkPos = placement.getStartChunk(world.getSeed(), sampleX, sampleZ);
				currentPos = new BlockPos(ChunkSectionPos.getBlockCoord(chunkPos.x) + 8, 0,
						ChunkSectionPos.getBlockCoord(chunkPos.z) + 8);

				Pair<BlockPos, Structure> pair = getStructureGeneratingAt(chunkPos);
				samples++;
				if (pair != null) {
					succeed(pair.getFirst(), pair.getSecond());
				}
			}
			// Interior grid positions: just skip, do NOT call fail()

			z++;
			if (z > length) {
				z = -length;
				x++;
				if (x > length) {
					// FIX: increment length FIRST, then reset x and z to the new -length
					// (old code reset x,z to -oldLength=0 and then incremented length,
					// causing the spiral to collapse back to origin every ring)
					length++;
					x = -length;
					z = -length;
				}
			}
		}

		if (hasWork()) {
			return true;
		}

		if (!finished) {
			fail();
		}

		return false;
	}

	@Override
	protected String getName() {
		return "RandomSpreadSearchWorker";
	}

	@Override
	public boolean shouldLogRadius() {
		return true;
	}

}
