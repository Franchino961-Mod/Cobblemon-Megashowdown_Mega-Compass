# Changelog - Mega Compass

All notable changes to the **Mega Compass** mod will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## [Unreleased]

### Planned
- JSON configuration system
- Visited structure tracking
- Particle effects when meteorite located
- Sound effects for search feedback
- JEI/REI/EMI recipe integration
- Debug command system

---

## [2.1.0] - May 6, 2026 (3D Rendering & Refactoring Update)

### Added
- **Dynamic 3D Models**: Complete migration from the old static 32-frame 2D animation system to a modern, dynamic 3D rendering system.
- **AE2-Style Pointer Rendering**: Implemented a custom `BakedModel` and `FabricBakedModel` system that mathematically calculates the needle's rotation in real-time based on the player's position and compass state.
- **Multiplayer Independence**: Completely refactored the internal search logic to support multiple players simultaneously without interference by using a UUID-based worker registry.
- **Improved Security**: Added server-side validation for structure search requests to prevent malicious client packets from being processed.
- **Robustness & Performance**: Implemented thread-safe worker management with a global limit of 100 active search workers to protect server stability.
- **GUI & UX Enhancements**: Redesigned the structure selection GUI with relative layout positioning and added real-time search feedback in the player's hotbar.

### Changed
- **Resource Optimization**: Significantly reduced mod size by removing hundreds of redundant JSON files and frame textures.
- **Improved Texture Customization**: Each compass now uses a dedicated single-texture system (one `.png` per compass), making it extremely easy for users to create high-quality recolors or custom textures.
- **Technical Refinement**: Removed redundant data components, fixed IDE lint warnings, and pinned build dependencies (Fabric Loom 1.8.13) to stable versions.

---

## [2.0.0] - March 23, 2026 (Major Update)

### Added
- **Global Localization**: Added official support for 6 new languages: Spanish (ES), French (FR), German (DE), Brazilian Portuguese (PT-BR), Russian (RU), and Simplified Chinese (ZH-CN). The mod now supports 8 languages in total.

### Changed
- **Mod Rename Engine**: Completely renamed the mod from "Meteorite Compass" to "Mega Compass". 
- **Namespace Update**: All internal IDs, item registries, resource folders, language keys, and data packets now use the `mega_compass` namespace.
- **Unified Compass**: The combined compass is now officially known as the "Mega Compass".
- **HUD Redesign**: Completely redesigned the on-screen display. The UI now features a clean, top-left aligned vertical list with white labels and light-gray values.

### Fixed
- **Compass Needle Animation**: Fixed a critical rendering issue where the compass needle remained frozen. The root Item models were corrected to properly interpret the `mega_compass:compass_angle` predicate so the needle smoothly points toward the nearest meteorite.
- **Needle Jitter Fix**: The compass needle no longer frantically shakes each frame when inactive or when a meteorite is not found. It now rests peacefully at its default position.

---

## [1.0.1] - February 27, 2026 (Bugfixes)

### Fixed
- Fixed a client crash (`ClassCastException`) when sending network packets by switching to `RegistryByteBuf`
- Fixed GUI selection where clicking "Mega Site" would incorrectly always search for "Megaroid"
- Fixed GUI text missing translations and rendering blurry

---

## [1.0.0] - February 25, 2026 (Initial Release)

### Added
- **Core Functionality**
  - Item that locates meteorites from CobblemonMegaShowdown
  - Structure finder for "mega_showdown:megaroid" (Y -32 to -20)
  - Structure finder for "mega_showdown:mega_site" (Y -19 to 5)
  - Right-click activation to search for nearest meteorite
  - Shift + right-click to reset compass
  - Data components for compass state storage

- **Advanced Search System**
  - WorldWorkerManager for async worker coordination
  - RandomSpreadSearchWorker with spiral search algorithm
  - Non-blocking search up to 10,000 block radius
  - Sampling up to 100,000 points

- **User Experience**
  - Real-time HUD display during search
  - Shows progress (samples, radius) while searching
  - Shows distance when meteorite found
  - Color-coded feedback (white=searching, green=found, red=not found)

- **Crafting Recipe**
  - Compass craftable with vanilla materials: Iron, Amethyst, Glowstone, Compass

- **Technical Architecture**
  - Platform: Fabric 1.21.1
  - Fabric Loader: 0.16.9+
  - Fabric API: 0.108.0+1.21.1

- **Localization**
  - English (en_us)
  - Italian (it_it)

- **Commands**
  - `/meteoritecompass reset` - Clear visited meteorite tracking
  - `/meteoritecompass info` - Display compass status and target

---

## Development Roadmap

### Phase 1 - Core (v1.0.0) ✅
- [x] Basic compass functionality
- [x] Structure finding logic
- [x] Multi-loader support
- [x] Commands implementation

### Phase 2 - Polish (v1.1.0) ✅
- [x] Dedicated compasses for each structure
- [x] Combined compass with GUI selection
- [x] Working needle animations
- [x] Individual compass textures

### Phase 3 - Advanced (v2.0.0) ✅
- [x] Mod ID separation and Full Rename to Mega Compass
- [x] Codebase Cleanup and Refactor

### Phase 4 - Integration & Rendering (v2.1.0) ✅
- [x] Migration to Dynamic 3D Models (AE2-style)
- [x] Asset and Performance Optimization
- [ ] Visited structure tracking
- [ ] JEI/REI integration
- [ ] Custom particle effects matching meteorite types
- [ ] Support for non-overworld structure types

---

*Format: [Version] - Date*  
*Categories: Added, Changed, Fixed, Removed, Technical*
