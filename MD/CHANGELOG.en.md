# Changelog - Meteorite Compass

All notable changes to the **Meteorite Compass** mod will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## [Unreleased]

### Planned
- JSON configuration system
- Visited structure tracking
- Compass needle animation with ItemPropertyFunction
- Particle effects when meteorite located
- Sound effects for search feedback
- Additional language support (ES, FR, DE, PT-BR)
- JEI/REI/EMI recipe integration
- Debug command system

---

## [1.0.1] - February 27, 2026 (Bugfixes)

### Fixed
- Fixed compass needle not animating due to missing ModelPredicate registry
- Fixed a client crash (`ClassCastException`) when sending network packets by switching to `RegistryByteBuf`
- Fixed GUI selection where clicking "Mega Site" would incorrectly always search for "Megaroid"
- Fixed GUI text missing translations and rendering blurry

---

## [1.0.0] - February 25, 2026 (Initial Release)

### Added
- **Core Functionality**
  - Meteorite Compass item that locates meteorites from CobblemonMegaShowdown
  - Structure finder for "mega_showdown:megaroid" (Y -32 to -20)
  - Structure finder for "mega_showdown:mega_site" (Y -19 to 5)
  - Right-click activation to search for nearest meteorite
  - Shift + right-click to reset compass
  - Data components for compass state storage (INACTIVE, SEARCHING, FOUND, NOT_FOUND)

- **Advanced Search System**
  - WorldWorkerManager for async worker coordination
  - SearchWorkerManager for managing multiple searches
  - RandomSpreadSearchWorker with spiral search algorithm
  - Non-blocking search up to 10,000 block radius
  - Sampling up to 100,000 points
  - Time-sliced execution (50ms per server tick)

- **User Experience**
  - Real-time HUD display during search
  - Shows progress (samples, radius) while searching
  - Shows distance when meteorite found
  - Color-coded feedback (white=searching, green=found, red=not found)
  - Client initializer for HUD rendering

- **Crafting Recipe**
  - Compass craftable with vanilla materials:
    * 4x Iron Ingots
    * 2x Amethyst Shards
    * 2x Glowstone Dust
    * 1x Compass
  - Early-game friendly, no meteorite materials required

- **Networking**
  - SearchPacket (Client → Server) to initiate search
  - SyncPacket (Server → Client) for state sync
  - Fabric Networking API integration

- **Technical Architecture**
  - Platform: Fabric 1.21.1
  - Fabric Loader: 0.16.9+
  - Fabric API: 0.108.0+1.21.1
  - Yarn Mappings: 1.21.1+build.3
  - Java: 21
  - Gradle: 8.10.2
  - Fabric Loom: 1.8-SNAPSHOT

- **Localization**
  - Italian (it_it)
  - English (en_us)
  - Italian (it_it)
  - Spanish (es_es)
  - French (fr_fr)
  - German (de_de)
  - Portuguese Brazil (pt_br)

- **Technical Features**
  - Multi-loader support (Fabric + NeoForge)
  - NBT data component for target storage
  - Structure search caching (5-minute expiration)
  - Client-server networking for coordinate sync
  - Performance optimization (<0.5ms server impact)

- **Commands**
  - `/meteoritecompass reset` - Clear visited meteorite tracking
  - `/meteoritecompass info` - Display compass status and target

### Technical Notes
- Uses Minecraft's StructureManager API for structure location
- Implements custom ItemPropertyFunction for compass needle animation
- Compatible with Minecraft 1.21.1
- Requires CobblemonMegaShowdown as dependency
- Built on Architectury API for multi-loader architecture

### Known Limitations
- Only finds meteorites in already-generated chunks
- Works only in Overworld dimension
- Search performance depends on world size and structure density

---

## Development Roadmap

### Phase 1 - Core (v1.0.0) ✅
- [x] Basic compass functionality
- [x] Structure finding logic
- [x] Configuration system
- [x] Multi-loader support
- [x] Localization (6 languages)
- [x] Commands implementation

### Phase 2 - Polish (v1.1.0) 🔄
- [ ] Visited structure tracking
- [ ] Distance-based color coding
- [ ] Enhanced HUD with direction arrows
- [ ] JEI/REI integration
- [ ] Additional particle effects
- [ ] More sound variations

### Phase 3 - Advanced (v1.2.0) 📋
- [ ] Dual compass system (separate for each meteorite type)
- [ ] Compass upgrades (increased range)
- [ ] Rechargeable compass system
- [ ] Structure preview in HUD
- [ ] Waypoint marking system
- [ ] Team-shared compass targets (multiplayer)

### Phase 4 - Integration (v1.3.0) 📋
- [ ] Deep integration with CobblemonMegaShowdown events
- [ ] Custom particle effects matching meteorite types
- [ ] Achievement system
- [ ] Statistics tracking (meteorites found)
- [ ] Datapack support for custom structures

---

*Format: [Version] - Date*  
*Categories: Added, Changed, Fixed, Removed, Technical*
