package com.megacompass.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.PlayerEntity;

import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.gen.structure.Structure;

public class StructureUtils {

	// Meteorite structure IDs
	public static final Identifier MEGAROID = Identifier.of("mega_showdown", "megaroid");
	public static final Identifier MEGA_SITE = Identifier.of("mega_showdown", "mega_site");
	public static final Identifier WISHING_WEALD = Identifier.of("mega_showdown", "wishing_weald");

	public static Identifier getIdForStructure(ServerWorld world, Structure structure) {
		return world.getRegistryManager().get(RegistryKeys.STRUCTURE).getId(structure);
	}

	public static Structure getStructureForId(ServerWorld world, Identifier structureId) {
		return world.getRegistryManager().get(RegistryKeys.STRUCTURE).get(structureId);
	}
	
	public static RegistryEntry<Structure> getHolderForStructure(ServerWorld world, Structure structure) {
		return world.getRegistryManager().get(RegistryKeys.STRUCTURE).getEntry(structure);
	}

	public static List<Structure> getMeteoriteStructures(ServerWorld world) {
		final List<Structure> structures = new ArrayList<Structure>();
		Structure megaroid = getStructureForId(world, MEGAROID);
		Structure megaSite = getStructureForId(world, MEGA_SITE);
		
		if (megaroid != null) {
			structures.add(megaroid);
		}
		if (megaSite != null) {
			structures.add(megaSite);
		}
		
		return structures;
	}

	public static int getHorizontalDistanceToLocation(PlayerEntity player, int x, int z) {
		return getHorizontalDistanceToLocation(player.getBlockPos(), x, z);
	}

	public static int getHorizontalDistanceToLocation(BlockPos startPos, int x, int z) {
		return (int) MathHelper.sqrt((float) startPos.getSquaredDistance(new BlockPos(x, startPos.getY(), z)));
	}

	public static String getMeteoriteName(Identifier structureId) {
		if (structureId.equals(MEGAROID)) {
			return "Megaroid";
		} else if (structureId.equals(MEGA_SITE)) {
			return "Mega Site";
		} else if (structureId.equals(WISHING_WEALD)) {
			return "Wishing Weald";
		}
		return "Unknown Meteorite";
	}

}
