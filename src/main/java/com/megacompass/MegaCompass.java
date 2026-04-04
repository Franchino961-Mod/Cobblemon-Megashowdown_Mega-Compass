package com.megacompass;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.megacompass.item.MegaCompassItem;
import com.megacompass.worker.WorldWorkerManager;
import com.mojang.serialization.Codec;

import com.megacompass.network.SearchPacket;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.component.ComponentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class MegaCompass implements ModInitializer {

	public static final String MODID = "mega_compass";

	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	public static MegaCompassItem MEGA_SITE_COMPASS;
	public static MegaCompassItem MEGAROID_COMPASS;
	public static MegaCompassItem WISHING_WEALD_COMPASS;
	public static MegaCompassItem MEGA_COMPASS;
	public static net.minecraft.item.ItemGroup MEGA_COMPASS_TAB;
	public static net.minecraft.registry.RegistryKey<net.minecraft.item.ItemGroup> MEGA_COMPASS_TAB_KEY;

	// Data components for storing compass state in ItemStack
	public static final ComponentType<String> STRUCTURE_ID_COMPONENT = ComponentType.<String>builder()
			.codec(Codec.STRING)
			.packetCodec(PacketCodecs.STRING)
			.build();
	public static final ComponentType<Integer> COMPASS_STATE_COMPONENT = ComponentType.<Integer>builder()
			.codec(Codec.INT)
			.packetCodec(PacketCodecs.VAR_INT)
			.build();
	public static final ComponentType<Integer> FOUND_X_COMPONENT = ComponentType.<Integer>builder()
			.codec(Codec.INT)
			.packetCodec(PacketCodecs.VAR_INT)
			.build();
	public static final ComponentType<Integer> FOUND_Z_COMPONENT = ComponentType.<Integer>builder()
			.codec(Codec.INT)
			.packetCodec(PacketCodecs.VAR_INT)
			.build();
	public static final ComponentType<Integer> SEARCH_RADIUS_COMPONENT = ComponentType.<Integer>builder()
			.codec(Codec.INT)
			.packetCodec(PacketCodecs.VAR_INT)
			.build();
	public static final ComponentType<Integer> SAMPLES_COMPONENT = ComponentType.<Integer>builder()
			.codec(Codec.INT)
			.packetCodec(PacketCodecs.VAR_INT)
			.build();
	public static final ComponentType<Integer> METEORITE_TYPE_COMPONENT = ComponentType.<Integer>builder()
			.codec(Codec.INT)
			.packetCodec(PacketCodecs.VAR_INT)
			.build();

	@Override
	public void onInitialize() {
		LOGGER.info("Mega Compass is initializing...");

		// Register data components
		Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(MODID, "structure_id"), STRUCTURE_ID_COMPONENT);
		Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(MODID, "compass_state"),
				COMPASS_STATE_COMPONENT);
		Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(MODID, "found_x"), FOUND_X_COMPONENT);
		Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(MODID, "found_z"), FOUND_Z_COMPONENT);
		Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(MODID, "search_radius"),
				SEARCH_RADIUS_COMPONENT);
		Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(MODID, "samples"), SAMPLES_COMPONENT);
		Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(MODID, "meteorite_type"),
				METEORITE_TYPE_COMPONENT);

		// Register items
		MEGA_SITE_COMPASS = Registry.register(
				Registries.ITEM,
				Identifier.of(MODID, "mega_site_compass"),
				new MegaCompassItem(new Item.Settings().maxCount(1), com.megacompass.util.StructureUtils.MEGA_SITE,
						false));

		MEGAROID_COMPASS = Registry.register(
				Registries.ITEM,
				Identifier.of(MODID, "megaroid_compass"),
				new MegaCompassItem(new Item.Settings().maxCount(1), com.megacompass.util.StructureUtils.MEGAROID,
						false));

		WISHING_WEALD_COMPASS = Registry.register(
				Registries.ITEM,
				Identifier.of(MODID, "wishing_weald_compass"),
				new MegaCompassItem(new Item.Settings().maxCount(1), com.megacompass.util.StructureUtils.WISHING_WEALD,
						false));

		MEGA_COMPASS = Registry.register(
				Registries.ITEM,
				Identifier.of(MODID, "mega_compass"),
				new MegaCompassItem(new Item.Settings().maxCount(1), null, true));

		// Register custom creative tab
		MEGA_COMPASS_TAB_KEY = net.minecraft.registry.RegistryKey.of(
				RegistryKeys.ITEM_GROUP,
				Identifier.of(MODID, "mega_compass_tab"));
		MEGA_COMPASS_TAB = net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup.builder()
				.displayName(net.minecraft.text.Text.translatable("itemGroup.mega_compass.tab"))
				.icon(() -> new net.minecraft.item.ItemStack(MEGA_COMPASS))
				.build();
		Registry.register(Registries.ITEM_GROUP, MEGA_COMPASS_TAB_KEY, MEGA_COMPASS_TAB);

		// Add to custom creative tab
		ItemGroupEvents.modifyEntriesEvent(MEGA_COMPASS_TAB_KEY).register(content -> {
			content.add(MEGA_SITE_COMPASS);
			content.add(MEGAROID_COMPASS);
			content.add(WISHING_WEALD_COMPASS);
			content.add(MEGA_COMPASS);
		});

		// Register the C2S packet type so Fabric Networking knows how to encode it.
		// Without this, sending SearchPacket throws a ClassCastException.
		PayloadTypeRegistry.playC2S().register(SearchPacket.TYPE, SearchPacket.CODEC);

		// Register the server-side handler for SearchPacket.
		ServerPlayNetworking.registerGlobalReceiver(SearchPacket.TYPE, SearchPacket::handle);

		ServerTickEvents.START_SERVER_TICK.register(server -> {
			WorldWorkerManager.tick(true);
		});

		ServerTickEvents.END_SERVER_TICK.register(server -> {
			WorldWorkerManager.tick(false);
		});

		// Register server stopping event
		ServerLifecycleEvents.SERVER_STOPPING.register(server -> {
			WorldWorkerManager.clear();
		});
	}

	public static List<MegaCompassItem> getAllCompassItems() {
		return List.of(MEGA_SITE_COMPASS, MEGAROID_COMPASS, WISHING_WEALD_COMPASS, MEGA_COMPASS);
	}

	public static boolean isMegaCompassItem(ItemStack stack) {
		return !stack.isEmpty() && stack.getItem() instanceof MegaCompassItem;
	}

}
