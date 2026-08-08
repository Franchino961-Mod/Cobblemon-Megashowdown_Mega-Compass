package com.megacompass.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.structure.Structure;

public class StructureUtils {

    public static final Identifier MEGAROID            = Identifier.of("mega_showdown", "megaroid");
    public static final Identifier MEGA_SITE           = Identifier.of("mega_showdown", "mega_site");
    public static final Identifier WISHING_WEALD       = Identifier.of("mega_showdown", "wishing_weald");
    public static final Identifier OBSERVATORY         = Identifier.of("mega_showdown", "observatory");
    public static final Identifier ARCHAEOLOGICAL_SITE = Identifier.of("mega_showdown", "archaeological_site");

    /** Restituisce tutte le strutture di MegaShowdown presenti nel mondo corrente. */
    public static List<Structure> getAllTargetStructures(ServerWorld world) {
        List<Structure> structures = new ArrayList<>();
        addIfPresent(world, MEGAROID, structures);
        addIfPresent(world, MEGA_SITE, structures);
        addIfPresent(world, WISHING_WEALD, structures);
        addIfPresent(world, OBSERVATORY, structures);
        addIfPresent(world, ARCHAEOLOGICAL_SITE, structures);
        return structures;
    }

    private static void addIfPresent(ServerWorld world, Identifier id, List<Structure> list) {
        Structure structure = getStructureForId(world, id);
        if (structure != null) {
            list.add(structure);
        }
    }

    public static Structure getStructureForId(ServerWorld world, Identifier id) {
        Registry<Structure> registry = world.getRegistryManager().get(RegistryKeys.STRUCTURE);
        return registry.get(id);
    }

    public static Identifier getIdForStructure(ServerWorld world, Structure structure) {
        Registry<Structure> registry = world.getRegistryManager().get(RegistryKeys.STRUCTURE);
        return registry.getId(structure);
    }

    public static int getHorizontalDistanceToLocation(Entity entity, int x, int z) {
        return getHorizontalDistanceToLocation(entity.getBlockPos(), x, z);
    }

    public static int getHorizontalDistanceToLocation(BlockPos start, int x, int z) {
        double dx = start.getX() - x;
        double dz = start.getZ() - z;
        return (int) Math.sqrt(dx * dx + dz * dz);
    }

    /** Restituisce il nome leggibile di una struttura dato il suo Identifier. */
    public static String getStructureName(Identifier id) {
        if (id == null) return "Unknown";

        if (id.equals(MEGAROID))            return "Megaroid";
        if (id.equals(MEGA_SITE))           return "Mega Site";
        if (id.equals(WISHING_WEALD))       return "Wishing Weald";
        if (id.equals(OBSERVATORY))         return "Observatory";
        if (id.equals(ARCHAEOLOGICAL_SITE)) return "Archaeological Site";

        return id.getPath();
    }
}