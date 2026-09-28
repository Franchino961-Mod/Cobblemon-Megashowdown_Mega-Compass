# 🧭 Mega Compass
**Locate MegaShowdown Structures with Ease!**

[![Download on CurseForge](https://img.shields.io/badge/Download_on-CurseForge-orange?style=for-the-badge&logo=curseforge)](https://www.curseforge.com/minecraft/mc-mods/cobblemon-mega-showdown-mega-compass)

[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.1-green.svg)](https://www.minecraft.net/)
[![Version](https://img.shields.io/badge/version-1.1.1-blue.svg)]()
[![Fabric](https://img.shields.io/badge/Fabric-0.18.0-blue.svg)](https://fabricmc.net/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

[![en](https://img.shields.io/badge/lang-en-red.svg)](README.md)
[![it](https://img.shields.io/badge/lang-it-green.svg)](Docs/README.it.md)

> 📝 **Changelog**: See [CHANGELOG.en.md](Docs/CHANGELOG.en.md) for version history.

---

## 📖 Overview

**Mega Compass** (formerly Meteorite Compass) is an essential addon for **CobblemonMegaShowdown** that adds tracking compasses to help you locate rare underground and surface structures!

Tired of digging randomly hoping to find meteorites or wishing wealds? This mod adds **6 distinct compasses** (5 dedicated structure compasses + 1 combined compass) that **point directly to the nearest structures**, complete with smoothly animating 3D needles and real-time HUD status!

---

## 🌟 Why Use Mega Compass?

- **Save Time Exploring**: No more wandering randomly for thousands of blocks hoping to stumble upon deep underground structures.
- **Visual & Fluid Navigation**: Includes smoothly animating 3D needles and real-time HUD indicators showing you the exact direction, coordinates, and distance.
- **Optimized Server Performance**: Time-budgeted search algorithm runs efficiently on the server tick thread without blocking gameplay.

---

## ✨ Main Features

- 🧭 **Smart Navigation**: Points to nearest Megaroid, Mega Site, Wishing Weald, Observatory, or Archaeological Site.
- 🎯 **Six Compass Types**: Dedicated compasses for each structure type, plus a Combined Mega Compass that lets you choose your target via an intuitive GUI.
- 📐 **3D Rendering System**: Modern 3D compass needles that rotate mathematically based on your position and target coordinates.
- 🔍 **Spiral Search Algorithm**: Efficient spiral search algorithm that checks up to 100,000 sample points or a 10,000 block radius.
- 📊 **HUD Display**: Real-time search progress, sample counts, coordinates, and distance display.
- 👥 **Multiplayer Ready**: Isolated search state and automatic worker cleanup on disconnect or dimension change.
- 🌐 **Multi-Language**: Supports 8 languages (EN, IT, ES, FR, DE, PT-BR, RU, ZH-CN).
- ⚡ **Optimized**: Thread-safe worker system ensures maximum server performance.

---

## 🚀 Quick Start

### Crafting the Compasses

You can craft tier-specific compasses using a standard compass mixed with Iron, Amethyst, and a specific item:

- **Mega Site Compass**: Glowstone Dust
- **Megaroid Compass**: Redstone Dust
- **Wishing Weald Compass**: Emerald
- **Observatory Compass**: Lapis Lazuli
- **Archaeological Compass**: Gold Ingot
- **Combined Mega Compass**: Simply place all 5 dedicated compasses together in a crafting grid!

### Using the Compasses

1. **Craft** a compass.
2. **Right-Click** with the compass in hand to search for the specific structure. (If using the Combined Mega Compass, a GUI will open allowing you to select which structure to find).
3. **Wait** for the search to complete.
4. **Watch the Needle**: The compass needle will smoothly animate and point you in the direct horizontal angle of the structure!
5. **Watch the HUD** for real-time search progress, coordinates, and block distance.
6. **Shift + Right-Click** to reset the compass.

---

## 🗺️ Targeted Structures

The compasses can locate five types of crucial structures from CobblemonMegaShowdown:

### 1. **Megaroid** 🌑
- **Depth**: Y -32 to Y -20 (deep underground)
- **Contains**: Mega Meteoroid Block (Keystone)
- **Strategy**: Dig straight down when compass points below you.

### 2. **Mega Site** ⭐
- **Depth**: Y -19 to Y 5 (shallow/near surface)
- **Contains**: Deoxys Meteorite, Mega Crystal Ores (Mega Stones)
- **Strategy**: Explore caves or dig down when near surface.

### 3. **Wishing Weald** 🌳
- **Location**: Surface Overworld (Forests)
- **Contains**: Wishing Star (Dynamax)
- **Strategy**: Follow the compass directly over ground until you reach the ruins.

### 4. **Observatory** 🔭
- **Location**: Surface Overworld (Elevated terrain)
- **Contains**: Astronomical and Mega equipment

### 5. **Archaeological Site** 🏺
- **Location**: Surface Overworld (Deserts / Badlands)
- **Contains**: Ancient relics and fossil materials

---

## ⚙️ Configuration

### Modpack Creators — Custom Crafting Recipe

You can override the default recipes via datapack by targeting:
```
data/mega_compass/recipe/mega_site_compass.json
data/mega_compass/recipe/megaroid_compass.json
data/mega_compass/recipe/wishing_weald_compass.json
data/mega_compass/recipe/observatory_compass.json
data/mega_compass/recipe/archaeological_compass.json
data/mega_compass/recipe/mega_compass.json
```

---

## 🎨 Visual Feedback

### HUD Display
When holding the compass, the HUD shows real-time state information:
- **Searching**: Structure name, sample count, and search radius.
- **Found**: Structure name, exact coordinates, and distance in blocks.
- **Not Found**: Radius and sample count reached.

---

## 📦 Requirements

- **Minecraft**: 1.21.1
- **Fabric Loader**: 0.16.0 or higher (0.18.0+ recommended)
- **Fabric API**: 0.116.11 or higher
- **CobblemonMegaShowdown**: Latest version (for the structures)
- **Java**: 21 or higher

---

## 📥 Installation

1. Download and install [Fabric Loader](https://fabricmc.net/use/)
2. Download [Fabric API](https://modrinth.com/mod/fabric-api)
3. Download [CobblemonMegaShowdown](https://www.curseforge.com/minecraft/mc-mods/cobblemon-megashowdown)
4. Place `mega_showdown-mega-compass-fabric-mc1.21.1-1.1.0.jar` in your `mods` folder
5. Launch Minecraft and enjoy!

---

## 🖥️ Client/Server Behavior

- **Server**: Required. Executes the search algorithms, tracks structure coordinates, and manages worker ticks.
- **Client**: Required. Renders the custom 3D compass needles, manages the compass selection GUI, and updates the search HUD display.

---

## 🤝 Compatibility & Modpack Notes

### Tested With
- ✅ **CobblemonMegaShowdown**: Full integration
- ✅ **JEI / REI / EMI**: Automatic recipe viewing support (vanilla recipes)
- ✅ **Cobblemon**: Works alongside
- ✅ **Fabric API**: Full compatibility

### Compatibility Notes
- Works in both Singleplayer and Multiplayer Dedicated Servers.
- **Overworld Only**: Compasses are designed to track Overworld structures and will notify the player if used in other dimensions.
- **Modpacks**: You are free to distribute and include this mod in any modpack.

---

## ❓ FAQ

### Q: Does this work in multiplayer?
**A**: Yes! Each player has their own compass tracking and independent worker lifecycle.

### Q: Does this work with other mods?
**A**: Yes, as long as CobblemonMegaShowdown is installed. No conflicts expected.

### Q: The compass doesn't work in the Nether/End. Why?
**A**: The structures only spawn in the Overworld. The compass will notify you that it only functions in the Overworld.

---

## 💬 Support & Feedback

If you encounter issues or bugs, please report them with:
- Mod version
- Minecraft / Fabric / Cobblemon versions
- Detailed description of the problem
- Crash logs (if applicable)

---

## 📄 License

This mod is licensed under the [MIT License](LICENSE). Feel free to include it in your modpacks!

---

## 👤 Author

**Franchino961** — [GitHub](https://github.com/Franchino961-Mod)

---

## 🤝 Contributing

Contributions are welcome via GitHub issues and pull requests.

---

## 🔗 Links

- [CobblemonMegaShowdown](https://www.curseforge.com/minecraft/mc-mods/cobblemon-mega-showdown) — Required mod
- [Cobblemon](https://cobblemon.com) - Required mod
- [Fabric](https://fabricmc.net/)
- [Fabric API](https://modrinth.com/mod/fabric-api)

---

**Made with ❤️ for the Cobblemon & MegaShowdown community!**