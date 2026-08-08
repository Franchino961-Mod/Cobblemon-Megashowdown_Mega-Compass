# Changelog

All notable changes to the **Mega Compass** mod will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.1.0] - 2026-08-08

### Added
- **Client Initializer & Entrypoint**: Added `MegaCompassClient` (`com.megacompass.client.MegaCompassClient`) implementing `ClientModInitializer` to register the screen opener callback for `MegaCompassScreen`.
- **Real-Time 3D Angle Calculation**: Added `MegaCompassClient.getCompassAngle(...)` calculating real-time compass rotation toward target structure coordinates relative to player position and yaw.
- **3D Composite Model Renderer**: Added `MegaCompassModel` unbaked model loader and `MegaCompassBakedModel` 3D composite renderer for custom item model baking.
- **Dynamic Status Tooltip**: Enhanced `MegaCompassItem.appendTooltip(...)` to dynamically display search status (Searching with radius and sample count, Found with exact X/Z target coordinates, Not Found within search radius, or Inactive lore) using translatable components.
- **Observatory & Archaeological Site Support**: Added support for searching Observatory (`mega_showdown:observatory`) and Archaeological Site (`mega_showdown:archaeological_site`) structures.
- **New Compass Items & Models**: Added Observatory Compass (`observatory_compass`) and Archaeological Compass (`archaeological_compass`) along with model definitions (`observatory_compass.json`, `observatory_compass_base.json`, `archaeological_compass.json`, `archaeological_compass_base.json`).
- **New Crafting Recipes**: Added crafting recipes for Observatory Compass (`observatory_compass.json`) and Archaeological Compass (`archaeological_compass.json`).

### Changed
- **Expanded Selection GUI**: Increased `MegaCompassScreen` panel height from 170px to 220px and added selection buttons for Observatory and Archaeological Site with relative coordinate positioning.
- **Updated Main Initializer**: Updated `MegaCompass` main class to register new compass items, custom creative tab entries, and data components.
- **Updated Combined Recipe**: Updated master `MegaCompass` recipe (`mega_compass.json`) to require all 5 individual structure compasses.
- **Updated Structure Utilities & Packet**: Updated `StructureUtils` and `SearchPacket` to register and validate target identifiers for Observatory and Archaeological Site.
- **Updated Translations**: Updated translation files across all 8 supported languages (`en_us`, `it_it`, `de_de`, `es_es`, `fr_fr`, `pt_br`, `ru_ru`, `zh_cn`).

### Fixed
- **Gitignore Pattern Fix**: Fixed `.gitignore` rule for `client/` pattern (changed to `/client/`) so `src/main/java/com/megacompass/client/` package and its client rendering classes are properly tracked by Git.
- **Client Loading Crash Fix**: Fixed startup crash (`ClassNotFoundException` for `MegaCompassClient`) during client initialization.

### Removed
- **Obsolete Models & Textures**: Removed obsolete pointer model overrides (`mega_compass_pointer.json`, etc.) and unused static 2D texture files in favor of the dynamic 3D baked model system.

---

## [1.0.0] - 2026-05-13

### Added
- **Dynamic 3D Models**: Migrated from static 2D frame animations to a dynamic 3D rendering system combining base presets (`compass_base_preset.json`) and pointer presets (`compass_pointer_preset.json`).
- **Multiplayer Thread Safety**: Implemented `WorldWorkerManager` with `CopyOnWriteArrayList` thread-safe worker registry supporting concurrent player searches up to a global cap of 100 workers.
- **Global Localization**: Added official localization for 6 new languages (`es_es`, `fr_fr`, `de_de`, `pt_br`, `ru_ru`, `zh_cn`), bringing total supported languages to 8.
- **Hotbar Search Feedback**: Added overlay messages displayed above the player hotbar when selecting a target structure in the GUI.

### Changed
- **Mod Rebrand**: Renamed mod from "Meteorite Compass" to "Mega Compass" (`mega_compass`) across all packages, namespaces, item IDs, components, and data packs.
- **Master Item Name**: Renamed combined compass item to "Mega Compass".
- **Model Preset Refactoring**: Created reusable model template presets to eliminate code duplication across JSON model definitions.

### Fixed
- **Frozen Needle Fix**: Fixed needle rendering issue by updating model predicates for `compass_angle`.
- **Needle Jitter Fix**: Resolved needle jittering when inactive or when structure is not found.

---

## [0.1.1] - 2026-02-27

### Fixed
- **Network Packet Crash**: Resolved client crash (`ClassCastException`) during network packet serialization by migrating to `RegistryByteBuf`.
- **GUI Selection Bug**: Fixed bug where clicking "Mega Site" in the GUI defaulted to searching for "Megaroid".
- **GUI Text Rendering**: Fixed missing translations and blurry text rendering within selection screen.

---

## [0.1.0] - 2026-02-25

### Added
- **Initial Release**: Added initial Meteorite Compass mod implementation for Cobblemon MegaShowdown structures (`megaroid` and `mega_site`).
- **Non-Blocking Search**: Implemented async spiral search algorithm (`RandomSpreadSearchWorker`, `SearchWorkerManager`) up to a 10,000 block search radius.
- **Selection GUI**: Added interactive structure selection GUI screen (`MegaCompassScreen`) and C2S packet handling (`SearchPacket`).
- **Crafting Recipes & Lang**: Added vanilla recipes (Iron, Amethyst, Glowstone, Compass) and initial English (`en_us`) and Italian (`it_it`) localization files.
