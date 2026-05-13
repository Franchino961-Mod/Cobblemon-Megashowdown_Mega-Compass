# 🧭 Mega Bussola
**Localizza le Strutture di MegaShowdown con Facilità!**

[![Download on CurseForge](https://img.shields.io/badge/Download_on-CurseForge-orange?style=for-the-badge&logo=curseforge)](https://www.curseforge.com/minecraft/mc-mods/cobblemon-mega-showdown-meteorite-compass)

[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.1-green.svg)](https://www.minecraft.net/)
[![Versione](https://img.shields.io/badge/versione-2.0.0-blue.svg)]()
[![Fabric](https://img.shields.io/badge/Fabric-0.17.2-blue.svg)](https://fabricmc.net/)
[![Licenza](https://img.shields.io/badge/Licenza-MIT-yellow.svg)](../LICENSE)

[![en](https://img.shields.io/badge/lang-en-red.svg)](../README.md)
[![it](https://img.shields.io/badge/lang-it-green.svg)](README.it.md)

> 📝 **Changelog**: Vedi [CHANGELOG.it.md](CHANGELOG.it.md) per la cronologia delle versioni.

---

## 📖 Panoramica

**Mega Bussola** (in precedenza Meteorite Compass) è un addon essenziale per **CobblemonMegaShowdown** che aggiunge bussole di tracciamento per aiutarti a localizzare rare strutture sotterranee e di superficie!

Stanco di scavare a caso sperando di trovare meteoriti o la Wishing Weald? Questa mod aggiunge **4 bussole distinte** che **puntano direttamente alla struttura più vicina**, complete di aghi che si animano fluidamente, rendendo l'esplorazione mirata e gratificante.

### ✨ Caratteristiche Principali

- 🧭 **Navigazione Intelligente**: Punta al Megaroid, Mega Site o Wishing Weald più vicino.
- 🎯 **Quattro Tipi di Bussole**: Bussole dedicate per ogni tipo di struttura, più una Mega Bussola Combinata che ti permette di scegliere il bersaglio tramite una GUI intuitiva.
- 📐 **Sistema di Rendering 3D**: Moderni aghi 3D stile AE2 che ruotano matematicamente in base alla tua posizione e alle coordinate del bersaglio.
- 🔍 **Ricerca Asincrona**: Algoritmo di ricerca a spirale efficiente che non causa lag al server.
- 📊 **Display HUD**: Visualizzazione in tempo reale del progresso della ricerca, coordinate e distanza.
- 👥 **Multiplayer Ready**: Gestione dei worker isolata per giocatore per un'esperienza multiplayer senza interferenze.
- 🌐 **Multi-Lingua**: Supporta 8 lingue (IT, EN, ES, FR, DE, PT-BR, RU, ZH-CN).
- ⚡ **Ottimizzato**: Sistema worker thread-safe che garantisce massime prestazioni del server.

---

## 🎮 Come Funziona

### Craftare le Bussole

Puoi craftare bussole per le singole strutture unendo una classica Bussola con Ferro, Ametista e una gemma specifica:

- **Bussola Mega Site**: Polvere di Glowstone
- **Bussola Megaroid**: Polvere di Redstone
- **Bussola Wishing Weald**: Smeraldo
- **Mega Bussola Combinata**: Unisci in una griglia di crafting (in qualsiasi ordine) le 3 bussole dedicate!

### Usare le Bussole

1. **Crafta** una bussola.
2. **Click Destro** con la bussola in mano per cercare la struttura. (Se usi la Mega Bussola Combinata, si aprirà un'interfaccia che ti permetterà di scegliere quale delle 3 strutture cercare).
3. **Aspetta** che la ricerca asincrona si completi.
4. **Guarda l'ago**: L'ago della bussola si animerà in modo fluido per puntare esattamente all'angolo orizzontale in cui si trova la struttura!
5. **Guarda l'HUD** per progresso e blocchi di distanza in tempo reale.
6. **Shift + Click Destro** per resettare la bussola in qualsiasi momento.

### Sistema di Ricerca

La bussola usa un **algoritmo di ricerca a spirale intelligente** che:
- Campiona posizioni in quadrati espandenti
- Controlla fino a 100.000 punti campione o 10.000 blocchi di raggio distanti
- Funziona in modo asincrono usando un sistema worker (nessun lag del server)

---

## 🗺️ Le Strutture

Questa mod può localizzare tre tipi importantissimi di strutture da CobblemonMegaShowdown:

### 1. **Megaroid** 🌑
- **Profondità**: Y -32 a Y -20 (sotterraneo profondo)
- **Contiene**: Blocco Meteoroide Mega (Keystone)
- **Strategia**: Scava dritto verso il basso quando l'ago punta sotto di te.

### 2. **Mega Site** ⭐
- **Profondità**: Y -19 a Y 5 (vicino alla superficie)
- **Contiene**: Meteorite Deoxys, Ores Mega Crystal (Mega Stones)
- **Strategia**: Esplora grotte o scava lentamente a ridosso della superficie.

### 3. **Wishing Weald** 🌳
- **Locale**: Sopra il terreno (Biomi Forestali)
- **Contiene**: Wishing Star (Dynamax)
- **Strategia**: Segui la bussola procedendo camminando normalmente in superficie finché non trovi le rovine.

---

## 📦 Installazione

### Requisiti
- **Minecraft**: 1.21.1
- **Fabric Loader**: 0.17.2 o superiore
- **Fabric API**: 0.116.11 o superiore
- **CobblemonMegaShowdown**: Ultima versione
- **Java**: 21 o superiore

### Passaggi
1. Scarica e installa [Fabric Loader](https://fabricmc.net/use/)
2. Scarica [Fabric API](https://modrinth.com/mod/fabric-api)
3. Scarica [CobblemonMegaShowdown](https://www.curseforge.com/minecraft/mc-mods/cobblemon-megashowdown)
4. Posiziona `mega_showdown-mega-compass-fabric-mc1.21.1-2.0.0.jar` nella tua cartella `mods`
5. Avvia Minecraft e divertiti!

## ⚙️ Configurazione

> 📋 Un sistema di configurazione JSON completo è pianificato per una versione futura.

Valori fissi attuali:

| Impostazione | Valore |
|--------------|--------|
| Raggio Massimo Ricerca | 10.000 blocchi |
| Campioni Massimi | 100.000 punti |
| Pattern di Ricerca | Spirale (quadrato espandente) |

### Per Creatori di Modpack — Ricetta di Crafting Personalizzata

Puoi sovrascrivere tutte le ricette predefinite tramite datapack:
```
data/mega_compass/recipe/mega_site_compass.json
data/mega_compass/recipe/megaroid_compass.json
data/mega_compass/recipe/wishing_weald_compass.json
data/mega_compass/recipe/mega_compass.json
```

---

## 🎨 Feedback Visivi

### Display HUD
Quando tieni la bussola, appariranno le scritte:
```
Ricerca <target> in corso... (<campioni>, <raggio>)
<target> trovato! Distanza: <blocchi>
Nessuna struttura trovata entro <raggio> blocchi (<campioni>)
```

---

## 🧪 Note sulle Performance

- **Tempo Ricerca**: Tipicamente 100-500ms a seconda della dimensione del mondo
- **Impatto Server**: Minimo (<0.5ms per tick)
- **Uso Memoria**: ~5KB per bussola attiva

---

## 🤝 Compatibilità

### Testato Con
- ✅ **CobblemonMegaShowdown**: Integrazione completa
- ✅ **JEI/REI/EMI**: Supporto visualizzazione ricette
- ✅ **Cobblemon**: Funziona insieme
- ✅ **Fabric API**: Compatibilità completa
- ✅ **Architectury API**: Supporto multi-loader

### Problemi Noti
- Le bussole trovano strutture solo in **chunk già generati in precedenza**.
- Se nessuna struttura viene trovata, cammina verso orizzonti inesplorati per forzare il gioco a generare nuovi chunk, poi risetta la bussola e riprova.
- Funziona solo nella dimensione **Overworld**.

---

## ❓ FAQ

### D: La bussola dice "Nessuna struttura trovata". Cosa faccio?
**R**: Le strutture esistono solo in chunk già generati. Carica nuovi chunk e riprova.

### D: Funziona in multiplayer?
**R**: Sì! Ogni giocatore ha il proprio tracking della bussola.

### D: Posso usarla senza Cobblemon?
**R**: Sì! CobblemonMegaShowdown funziona standalone (Cobblemon non richiesto).

### D: La bussola non funziona nel Nether/End. Perché?
**R**: Nessuna di queste strutture spawna fuori dall'Overworld!

---

## 📄 Licenza

Questa mod è rilasciata sotto la [Licenza MIT](../LICENSE). Sentiti libero di includerla nei tuoi modpack!

## 👤 Autore

**Franchino961** — [GitHub](https://github.com/Franchino961-Mod)

## 🤝 Contributi

Contributi benvenuti!
- Apri una [Issue](../../issues) per segnalare bug o suggerire funzionalità
- Apri una [Pull Request](../../pulls) per contribuire al codice

## 💬 Supporto

Se riscontri problemi o bug, segnalali includendo:
- Versione della mod
- Versioni di Minecraft / Fabric / Cobblemon
- Descrizione dettagliata del problema
- Log di crash (se applicabili)
- [Apri una Issue](../../issues)

## 🙏 Crediti

### Ringraziamenti Speciali
- **yajatkaul** — Creatore di CobblemonMegaShowdown
- **Team Cobblemon** — Per la fantastica mod Cobblemon
- **Team Fabric/NeoForge** — Per il supporto piattaforme modding
- **Community** — Per feedback e testing

### Asset
- Tutte le texture e suoni creati specificamente per questa mod
- Meccaniche bussola ispirate alla Bussola Magnetite vanilla

---

## 🔗 Link

- [CobblemonMegaShowdown](https://www.curseforge.com/minecraft/mc-mods/cobblemon-mega-showdown) — Mod richiesta
- [Cobblemon](https://cobblemon.com) - Required mod
- [Fabric](https://fabricmc.net/)
- [Fabric API](https://modrinth.com/mod/fabric-api)

---

## 📝 Changelog

Vedi [CHANGELOG.it.md](CHANGELOG.it.md) per la cronologia completa delle versioni.

---

**Fatto con ❤️ per la community di Cobblemon & MegaShowdown!**
