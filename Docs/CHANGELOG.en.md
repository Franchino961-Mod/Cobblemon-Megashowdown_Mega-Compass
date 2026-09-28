# Changelog

All notable changes to the **Mega Compass** mod will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.1.1] - 2026-09-28

### Added
- **In-Game HUD Status Overlay**: Added `CompassHudRenderer` (`com.megacompass.client.render.CompassHudRenderer`) to render real-time compass search status (Searching with samples and radius, Found with structure name, exact coordinates, and distance, or Not Found) in the top-left HUD corner when holding a compass.
- **Dynamic 3D Model Registration**: Registered custom 3D baked model loaders via Fabric's `ModelLoadingPlugin` in `MegaCompassClient` for all compass variants.
- **Overworld Dimension Restriction**: Added dimension check blocking searches in Nether/End with translated overlay feedback (`string.mega_compass.status.wrong_dimension`) across all 8 languages.
- **Worker Lifecycle Management**: Registered disconnect (`ServerPlayConnectionEvents.DISCONNECT`) and dimension-change (`ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD`) event handlers to automatically stop and clean up active search workers.
- **Anti-Spam Cooldown**: Added a 1-second cooldown when starting searches to prevent packet and worker churn.

### Changed
- **Updated Fabric Loader**: Bumped Fabric Loader dependency version to `0.18.0` in `gradle.properties` for mod compatibility.
- **Updated CurseForge Links**: Corrected official project slug to `cobblemon-mega-showdown-mega-compass` in `fabric.mod.json`, `README.md`, and documentation.
- **Documentation Alignment**: Aligned README and Italian documentation to accurately describe all 6 compass variants and 5-ingredient master recipe.

### Fixed
- **Server Tick Starvation & Exception Safety**: Fixed tick time budgeting in `WorldWorkerManager` with safe elapsed-time checks and exception handling to prevent server lag or tick crashes.
- **Dynamic Player ItemStack Resolution**: Fixed worker memory leak and orphan updates by resolving the player's active held item at search completion (`StructureSearchWorker.getActiveCompassStack()`).
- **HUD Sample Count Sync**: Fixed real-time HUD samples counter being stuck at 0 during search by updating `SAMPLES_COMPONENT` alongside search radius.
- **Dimension Needle Rotation**: Fixed 3D needle pointing to invalid coordinates when entering non-Overworld dimensions.

### Security
- **C2S Payload Target Validation**: Fixed vulnerability in `SearchPacket` to prevent unauthorized structure target modifications on single-structure compasses.

### Performance
- **Zero-Allocation 3D Render Loop**: Replaced JOML `Quaternionf` and `Vector3f` heap allocations in `MegaCompassBakedModel` with direct inline trigonometric vertex transformations.

### Removed
- **Leftover Backup Files**: Removed redundant `.bak` files (`icon.png.bak`, `compass_base_preset.json.bak`) from production resources.

---

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
