# 🧭 Mega Bussola
**Trova le strutture di MegaShowdown con facilità!**

[![Scarica su CurseForge](https://img.shields.io/badge/Scarica_su-CurseForge-orange?style=for-the-badge&logo=curseforge)](https://www.curseforge.com/minecraft/mc-mods/cobblemon-mega-showdown-mega-compass)

[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.1-green.svg)](https://www.minecraft.net/)
[![Versione](https://img.shields.io/badge/versione-1.1.1-blue.svg)]()
[![Fabric](https://img.shields.io/badge/Fabric-0.18.0-blue.svg)](https://fabricmc.net/)
[![Licenza](https://img.shields.io/badge/Licenza-MIT-yellow.svg)](../LICENSE)

[![en](https://img.shields.io/badge/lang-en-red.svg)](../README.md)
[![it](https://img.shields.io/badge/lang-it-green.svg)](README.it.md)

> 📝 **Changelog**: Vedi [CHANGELOG.it.md](CHANGELOG.it.md) per la cronologia delle versioni.

---

## 📖 Panoramica

**Mega Bussola** (in precedenza Meteorite Compass) è un addon per **CobblemonMegaShowdown** che aggiunge bussole di tracciamento per aiutarti a localizzare rare strutture sotterranee e di superficie!

Aggiunge **6 bussole distinte** (5 per strutture specifiche + 1 combinata) che **puntano direttamente alle strutture più vicine**, con ago 3D fluido e HUD in tempo reale!

---

## ✨ Funzionalità Principali

- 🧭 **Navigazione Intelligente**: Punta a Megaroid, Mega Site, Wishing Weald, Osservatorio o Sito Archeologico.
- 🎯 **Sei Tipi di Bussola**: Bussole dedicate per ciascuna struttura, più una Mega Bussola Combinata con GUI di selezione.
- 📐 **Rendering 3D Dinamico**: Ago 3D calcolato in tempo reale su coordinate, posizione e yaw del giocatore.
- 🔍 **Algoritmo a Spirale**: Scansione coordinata fino a 100.000 campioni o 10.000 blocchi di raggio.
- 📊 **Display HUD**: Mostra stato, campioni, coordinate e distanza in blocchi.
- 👥 **Pronto per il Multiplayer**: Pulizia automatica dei worker alla disconnessione o cambio dimensione.
- 🌐 **Multilingua**: Supporto completo per 8 lingue (EN, IT, ES, FR, DE, PT-BR, RU, ZH-CN).

---

## 🚀 Guida Rapida

### Creazione delle Bussole

- **Bussola Mega Site**: Polvere di Luminite + Ferro + Ametista + Bussola
- **Bussola Megaroid**: Polvere di Pietrarossa + Ferro + Ametista + Bussola
- **Bussola Wishing Weald**: Smeraldo + Ferro + Ametista + Bussola
- **Bussola Osservatorio**: Lapislazzuli + Ferro + Ametista + Bussola
- **Bussola Sito Archeologico**: Lingotto d'Oro + Ferro + Ametista + Bussola
- **Mega Bussola Combinata**: Posiziona insieme le 5 bussole dedicate nella griglia di crafting!

### Utilizzo

1. **Click Destro**: Avvia la ricerca della struttura (sulla bussola combinata apre la GUI di selezione).
2. **Attendi il risultato**: L'HUD mostra progresso e distanza.
3. **Segui l'Ago**: L'ago 3D punta verso la struttura.
4. **Shift + Click Destro**: Reimposta la bussola.

---

## 🗺️ Strutture Tracciate

1. **Megaroid** 🌑 (Sotterraneo Y -32 a -20) — Mega Pietre e Meteoriti
2. **Mega Site** ⭐ (Superficie/Grotte Y -19 a 5) — Cristalli Mega e Deoxys
3. **Wishing Weald** 🌳 (Superficie, Foreste) — Stelle Desiderio
4. **Osservatorio** 🔭 (Superficie, Alture) — Strumenti astronomici
5. **Sito Archeologico** 🏺 (Superficie, Deserti/Calanchi) — Reperti antichi e fossili

---

## ⚙️ Configurazione Datapack

Le ricette possono essere sovrascritte via datapack in `data/mega_compass/recipe/`:
```
data/mega_compass/recipe/mega_site_compass.json
data/mega_compass/recipe/megaroid_compass.json
data/mega_compass/recipe/wishing_weald_compass.json
data/mega_compass/recipe/observatory_compass.json
data/mega_compass/recipe/archaeological_compass.json
data/mega_compass/recipe/mega_compass.json
```

---

## 📄 Licenza

Rilasciato sotto licenza [MIT License](../LICENSE). Libero utilizzo nei modpack!
