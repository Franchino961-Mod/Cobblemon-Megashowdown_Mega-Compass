# 🧭 Mega Compass
**Locate MegaShowdown Structures with Ease!**

[![Download on CurseForge](https://img.shields.io/badge/Download_on-CurseForge-orange?style=for-the-badge&logo=curseforge)](https://www.curseforge.com/minecraft/mc-mods/cobblemon-mega-showdown-meteorite-compass)

[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.1-green.svg)](https://www.minecraft.net/)
[![Version](https://img.shields.io/badge/version-2.0.0-blue.svg)]()
[![Fabric](https://img.shields.io/badge/Fabric-0.16.9-blue.svg)](https://fabricmc.net/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

[![en](https://img.shields.io/badge/lang-en-red.svg)](README.md)
[![it](https://img.shields.io/badge/lang-it-green.svg)](Docs/README.it.md)

> 📝 **Changelog**: See [CHANGELOG.en.md](Docs/CHANGELOG.en.md) for version history.

---

## 📖 Overview

**Mega Compass** (formerly Meteorite Compass) is an essential addon for **CobblemonMegaShowdown** that adds tracking compasses to help you locate rare underground and surface structures!

Tired of digging randomly hoping to find meteorites or wishing wealds? This mod adds **4 distinct compasses** that **point directly to the nearest structures**, complete with smoothly animating needles, making exploration focused and rewarding.

### ✨ Key Features

- 🧭 **Smart Navigation**: Points to nearest Megaroid, Mega Site, or Wishing Weald.
- 🎯 **Four Compass Types**: Dedicated compasses for each structure type, plus a Combined Mega Compass that lets you choose your target via an intuitive GUI.
- 🔍 **Async Search**: Efficient spiral search algorithm that doesn't lag the server.
- 📊 **HUD Display**: Real-time search progress and distance display.
- 🌐 **Multi-Language**: Supports 8 languages (EN, IT, ES, FR, DE, PT-BR, RU, ZH-CN).
- ⚡ **Optimized**: Worker system ensures smooth server performance.

---

## 🎮 How It Works

### Crafting the Compasses

You can craft tier-specific compasses using a standard compass mixed with Iron, Amethyst, and a specific gem:

- **Mega Site Compass**: Glowstone Dust
- **Megaroid Compass**: Redstone Dust
- **Wishing Weald Compass**: Emerald
- **Combined Mega Compass**: Simply place the 3 dedicated compasses together in a crafting grid!

### Using the Compasses

1. **Craft** a compass.
2. **Right-Click** with the compass in hand to search for the specific structure. (If using the Combined Mega Compass, a GUI will open allowing you to select which of the 3 structures to find).
3. **Wait** for the asynchronous search to complete.
4. **Watch the Needle**: The compass needle will smoothly animate and point you in the direct horizontal angle of the structure!
5. **Watch the HUD** for real-time search progress and block distance.
6. **Shift + Right-Click** to reset the compass.

### Search System

The compass uses an **intelligent spiral search algorithm** that:
- Samples potential locations in expanding squares
- Checks up to 100,000 sample points or 10,000 block radius
- Runs asynchronously using a worker system (no server lag)

---

## 🗺️ Targeted Structures

The compasses can locate three types of crucial structures from CobblemonMegaShowdown:

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

---

## 📦 Installation

### Requirements
- **Minecraft**: 1.21.1
- **Fabric Loader**: 0.16.9 or higher
- **Fabric API**: 0.108.0 or higher
- **CobblemonMegaShowdown**: Latest version (for the structures)
- **Java**: 21 or higher

### Steps
1. Download and install [Fabric Loader](https://fabricmc.net/use/)
2. Download [Fabric API](https://modrinth.com/mod/fabric-api)
3. Download [CobblemonMegaShowdown](https://www.curseforge.com/minecraft/mc-mods/cobblemon-megashowdown)
4. Place `mega_showdown-mega-compass-2.0.0.jar` in your `mods` folder
5. Launch Minecraft and enjoy!

## ⚙️ Configuration

> 📋 A full JSON configuration system is planned for a future version.

Current fixed values:

| Setting | Value |
|---------|-------|
| Max Search Radius | 10,000 blocks |
| Max Samples | 100,000 points |
| Search Pattern | Spiral (expanding square) |

### Modpack Creators — Custom Crafting Recipe

You can override the default recipes via datapack by targeting:
```
data/mega_compass/recipe/mega_site_compass.json
data/mega_compass/recipe/megaroid_compass.json
data/mega_compass/recipe/wishing_weald_compass.json
data/mega_compass/recipe/mega_compass.json
```

---

## 🎨 Visual Feedback

### HUD Display
When holding the compass, HUD shows state information:
```
Searching for <target>... (<samples>, <radius>)
<target> found! Distance: <blocks>
No structures found within <radius> blocks (<samples>)
```

---

## 🧪 Performance Notes

- **Search Time**: Typically 100-500ms depending on world size
- **Server Impact**: Minimal (<0.5ms per tick)
- **Memory Usage**: ~5KB per active compass

---

## 🤝 Compatibility

### Tested With
- ✅ **CobblemonMegaShowdown**: Full integration
- ✅ **JEI/REI/EMI**: Recipe viewing support
- ✅ **Cobblemon**: Works alongside
- ✅ **Fabric API**: Full compatibility
- ✅ **Architectury API**: Multi-loader support

### Known Issues
- Compasses only find structures in **already-generated chunks**.
- If no structure is found, explore new areas (generate new chunks) and try again.
- Works only in the **Overworld** dimension.

---

## ❓ FAQ

### Q: The compass says "No structure found". What do I do?
**A**: Structures only exist in already-generated chunks. Explore 1000+ blocks away into new terrain and try again.

### Q: Does this work in multiplayer?
**A**: Yes! Each player has their own compass tracking.

### Q: Does this work with other mods?
**A**: Yes, as long as CobblemonMegaShowdown is installed. No conflicts expected.

### Q: The compass doesn't work in the Nether/End. Why?
**A**: The structures only spawn in the Overworld. The compass won't function in other dimensions.

---

## 📄 License

This mod is licensed under the [MIT License](LICENSE). Feel free to include it in your modpacks!

## 👤 Author

**Franchino961** — [GitHub](https://github.com/Franchino961-Mod)

## 🤝 Contributing

Contributions are welcome!
- Open an [Issue](../../issues) to report bugs or suggest features
- Open a [Pull Request](../../pulls) to contribute code

## 💬 Support

If you encounter issues or bugs, please report them with:
- Mod version
- Minecraft / Fabric / Cobblemon versions
- Detailed description of the problem
- Crash logs (if applicable)
- [Open an Issue](../../issues)

## 🙏 Credits

### Special Thanks
- **yajatkaul** — Creator of CobblemonMegaShowdown
- **Cobblemon Team** — For the amazing Cobblemon mod
- **Fabric/NeoForge Teams** — For modding platform support
- **Community** — For feedback and testing

### Assets
- All textures and sounds created specifically for this mod
- Compass mechanics inspired by vanilla Lodestone Compass

---

## 🔗 Links

- [CobblemonMegaShowdown](https://www.curseforge.com/minecraft/mc-mods/cobblemon-mega-showdown) — Required mod
- [Cobblemon](https://cobblemon.com) - Required mod
- [Fabric](https://fabricmc.net/)
- [Fabric API](https://modrinth.com/mod/fabric-api)

---

## 📝 Changelog

See [CHANGELOG.en.md](Docs/CHANGELOG.en.md) for full version history.

---

**Made with ❤️ for the Cobblemon & MegaShowdown community!**