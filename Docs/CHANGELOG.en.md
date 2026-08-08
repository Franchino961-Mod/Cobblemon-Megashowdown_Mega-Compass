# Changelog - Mega Compass

All notable changes to the **Mega Compass** mod will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.1.0] - 2026-08-07

### Added
- **Observatory & Archaeological Site Structures**: Integrated search support for the Observatory (`mega_showdown:observatory`) and Archaeological Site (`mega_showdown:archaeological_site`) structures.
- **New Dedicated Compasses**: Added the Observatory Compass (`observatory_compass`) and Archaeological Compass (`archaeological_compass`) with dedicated 3D item models, textures, and crafting recipes.
- **GUI Selection Expansion**: Expanded the Mega Compass GUI panel height from 170px to 220px and added interactive selection buttons for both Observatory and Archaeological Site.
- **Global Translations**: Added localized translation keys across all 8 supported languages (`de_de`, `en_us`, `es_es`, `fr_fr`, `it_it`, `pt_br`, `ru_ru`, `zh_cn`) for the new compass items and structure names.

### Changed
- **Network Packet Validation**: Updated `SearchPacket` server-side validation to recognize and process search requests for the new structure IDs.
- **Structure Registry Utilities**: Updated `StructureUtils` to register Observatory and Archaeological Site identifiers and handle their display names.
- **Mega Compass Recipe**: Updated the combined Mega Compass recipe (`mega_compass.json`) to incorporate the new structure compasses into its crafting tree.

---

## [1.0.0] - 2026-05-13

### Added
- **Dynamic 3D Models**: Complete migration from the old static 32-frame 2D animation system to a modern, dynamic 3D rendering system.
- **Pointer Rendering**: Implemented a custom `BakedModel` and `FabricBakedModel` system that mathematically calculates the needle's rotation in real-time based on player position and compass state.
- **Multiplayer Independence**: Completely refactored internal search logic to support multiple players simultaneously without interference using a UUID-based worker registry.
- **Robustness & Security**: Implemented thread-safe worker management with a global limit of 100 active search workers and added server-side packet validation.
- **GUI & UX Enhancements**: Redesigned structure selection GUI with relative layout positioning and added real-time search feedback in player hotbar.
- **Global Localization**: Added official support for 6 new languages: Spanish (`es_es`), French (`fr_fr`), German (`de_de`), Brazilian Portuguese (`pt_br`), Russian (`ru_ru`), and Simplified Chinese (`zh_cn`), bringing total language support to 8.

### Changed
- **Mod Rename**: Completely renamed mod from "Meteorite Compass" to "Mega Compass" across codebase, namespaces, item registries, resource folders, language keys, and data packets.
- **Unified Compass**: Combined compass renamed officially to "Mega Compass".
- **HUD Redesign**: Redesigned on-screen display to a clean, top-left aligned vertical list with white labels and light-gray values.
- **Resource Optimization**: Significantly reduced mod size by removing hundreds of redundant JSON files and frame textures.
- **Texture Customization**: Each compass now uses a dedicated single-texture system (`.png`), allowing easy recolors and custom texture creation.
- **Model Refactoring**: Implemented template preset system for base and pointer models, drastically reducing JSON duplication.
- **Technical Refinement**: Removed redundant data components, fixed IDE lint warnings, and pinned Fabric Loom 1.8.13 build dependency.

### Fixed
- **Compass Needle Animation**: Fixed critical rendering issue where needle remained frozen by correcting root item models to interpret `mega_compass:compass_angle` predicate.
- **Needle Jitter Fix**: Prevented needle from frantically shaking when inactive or when structure is not found, returning it to default resting position.

---

## [0.1.1] - 2026-02-27

### Fixed
- **Network Packet Crash**: Resolved client crash (`ClassCastException`) during network packet serialization by migrating to `RegistryByteBuf`.
- **GUI Selection Logic**: Fixed bug where clicking "Mega Site" in the GUI incorrectly defaulted to searching for "Megaroid".
- **GUI Text Rendering**: Fixed missing translations and blurry text rendering within selection screen.

---

## [0.1.0] - 2026-02-25

### Added
- **Core Functionality**: Added initial compass item locating CobblemonMegaShowdown structures (`megaroid` and `mega_site`). Right-click activates search; Shift + right-click resets compass.
- **Advanced Search System**: Implemented `WorldWorkerManager` and `RandomSpreadSearchWorker` using spiral search algorithm up to 10,000 block radius non-blocking.
- **User Experience**: Added real-time HUD display showing sample count, radius, distance, and color-coded status feedback.
- **Crafting Recipe**: Added recipe using vanilla materials (Iron, Amethyst, Glowstone, Compass).
- **Localization**: Added English (`en_us`) and Italian (`it_it`) language files.
- **Commands**: Added `/meteoritecompass reset` and `/meteoritecompass info` commands.

---

## Development Roadmap

### Phase 1 - Core (v0.1.0) ✅
- Project bootstrap and Fabric 1.21.1 setup.
- Structure search worker implementation.
- Basic commands and initial localization.

### Phase 2 - Multi-Compass & GUI (v0.1.1) ✅
- Dedicated compass items per structure type.
- Combined compass with selection GUI.
- Fixed needle orientation animations.

### Phase 3 - 3D Engine & Rebrand (v1.0.0) ✅
- Full mod rename to Mega Compass.
- Dynamic 3D model baking system for pointer needle.
- Multiplayer thread-safe worker registry.
- Global 8-language localization.

### Phase 4 - Expansion & Feature Set (v1.1.0) 📋
- Support for Observatory and Archaeological Site structures.
- Extended GUI selection panel and recipes.
- Visited structure tracking system.
- Sound effects and custom particle indicators.
- External JSON configuration system.

---

*Format: [Version] - Date*  
*Categories: Added, Changed, Fixed, Removed, Technical*
