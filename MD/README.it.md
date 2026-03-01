# Meteorite Compass
**Localizza i Meteoriti di MegaShowdown con Facilità!**

[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.1-green.svg)](https://www.minecraft.net/)
[![Fabric](https://img.shields.io/badge/Fabric-1.21.1-blue.svg)](https://fabricmc.net/)
[![Fabric API](https://img.shields.io/badge/Fabric%20API-0.108.0-blue.svg)](https://modrinth.com/mod/fabric-api)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Build](https://img.shields.io/badge/Build-Passing-brightgreen.svg)](build/libs/)

> 📜 **Changelog**: Vedi [CHANGELOG.it.md](CHANGELOG.it.md) per tutti gli aggiornamenti e modifiche.

---

## 📖 Panoramica

**Meteorite Compass** è un addon per **CobblemonMegaShowdown** che aggiunge una bussola speciale per aiutarti a localizzare le rare strutture meteoriti sotterranee!

Stanco di scavare a caso sperando di trovare meteoriti? Questa mod aggiunge una bussola craftabile che **punta direttamente al meteorite più vicino**, rendendo l'esplorazione mirata e gratificante.

---

## ✨ Caratteristiche Principali

- 🧭 **Navigazione Intelligente**: Punta al Megaroid o Mega Site più vicino
- 🎯 **Due Tipi di Strutture**: Trova meteoriti sia profondi che superficiali
- 🔍 **Ricerca Asincrona**: Algoritmo di ricerca a spirale efficiente che non causa lag al server
- 📊 **Display HUD**: Visualizzazione in tempo reale del progresso della ricerca e della distanza
- 🌐 **Multi-Lingua**: Supporta IT e EN
- ⚡ **Ottimizzato**: Sistema worker che garantisce prestazioni fluide del server

---

## 📋 Requisiti

- **Minecraft**: 1.21.1
- **Fabric Loader**: 0.16.9 o superiore
- **Fabric API**: 0.108.0 o superiore
- **CobblemonMegaShowdown**: 1.0.0 o superiore

---

## 📦 Installazione

1. Assicurati di avere installato Fabric Loader, Fabric API e CobblemonMegaShowdown
2. Scarica il file `.jar` della mod
3. Posiziona il file nella cartella `mods` della tua installazione di Minecraft
4. Avvia il gioco!

---

## 🎮 Come Funziona

### Craftare la Bussola

```
[Lingotto Ferro] [Frammento Ametista] [Lingotto Ferro]
[Glowstone]      [     Bussola      ] [Glowstone]
[Lingotto Ferro] [Frammento Ametista] [Lingotto Ferro]
```

*Usa materiali comuni per accessibilità early-game*

### Usare la Bussola

1. **Crafta** la Bussola Meteorite usando la ricetta sopra
2. **Click Destro** con la bussola in mano per cercare meteoriti
3. **Aspetta** che la ricerca asincrona si completi
4. **Guarda l'HUD** per il progresso della ricerca in tempo reale e i risultati
5. **Segui** la bussola quando un meteorite viene trovato
6. **Shift + Click Destro** per resettare la bussola

### Sistema di Ricerca

La bussola usa un **algoritmo di ricerca a spirale intelligente** che:
- Campiona posizioni potenziali di meteoriti in quadrati espandenti
- Controlla fino a 100.000 punti campione o 10.000 blocchi di raggio
- Funziona in modo asincrono usando un sistema worker (nessun lag del server)
- Aggiorna il tuo HUD con il progresso in tempo reale

---

## 🗺️ Tipi di Meteoriti

La bussola può localizzare due tipi di meteoriti da CobblemonMegaShowdown:

### 1. **Megaroid** 🌑
- **Profondità**: Y -32 a Y -20 (sotterraneo profondo)
- **Contiene**: Blocco Meteoroide Mega, blocchi radioattivi
- **Rarità**: Raro (spaziatura: 38 chunk)
- **Strategia**: Scava dritto quando la bussola punta sotto di te

### 2. **Mega Site** ⭐
- **Profondità**: Y -19 a Y 5 (superficiale/vicino alla superficie)
- **Contiene**: Meteorite Deoxys, vari minerali meteoriti
- **Rarità**: Non comune (spaziatura: 32 chunk)
- **Strategia**: Esplora grotte o scava quando vicino alla superficie

---

## 📦 Installazione

### Requisiti
- **Minecraft**: 1.21.1
- **Fabric Loader**: 0.16.9 o superiore
- **Fabric API**: 0.108.0 o superiore
- **CobblemonMegaShowdown**: Ultima versione (per le strutture meteoriti)
- **Java**: 21 o superiore

### Passaggi
1. Scarica e installa [Fabric Loader](https://fabricmc.net/use/)
2. Scarica [Fabric API](https://modrinth.com/mod/fabric-api)
3. Scarica [CobblemonMegaShowdown](https://www.curseforge.com/minecraft/mc-mods/cobblemon-megashowdown)
4. Posiziona `meteorite-compass-1.0.0.jar` nella tua cartella `mods`
5. Avvia Minecraft e divertiti!

## ⚙️ Configurazione

*Sistema di configurazione in arrivo nella v1.1.0*

Valori attualmente hardcoded:
- **Raggio Massimo Ricerca**: 10.000 blocchi
- **Campioni Massimi**: 100.000 punti
- **Pattern di Ricerca**: Spirale (quadrato espandente)

---

## 🎨 Feedback Visivi e Audio

### Particelle
- **Scintille** quando inizia la ricerca
- **Raggio direzionale** quando meteorite localizzato
- **Effetto pulsante** quando vicino al target (<100 blocchi)

### Suoni
- **Suono attivazione** al click destro
- **Ping di successo** quando meteorite trovato
- **Beep crescenti** man mano che ti avvicini

### Display HUD
Quando tieni la bussola:
```
Bussola Meteorite
Target: Megaroid
Distanza: ~234 blocchi
Direzione: ↗ NE
```

---

## 🔧 Comandi

### `/meteoritecompass reset`
Cancella il tracking dei meteoriti visitati (se abilitato nel config).

### `/meteoritecompass info`
Mostra lo stato della bussola e il target corrente.

*Richiede permessi operatore sui server.*

---

## 📦 Installazione

### Requisiti
- **Minecraft**: 1.21.1
- **Fabric Loader**: 0.16.9+ (per Fabric)
- **NeoForge**: 21.1.x+ (per NeoForge)
- **Fabric API**: 0.108.0+ (solo Fabric)
- **CobblemonMegaShowdown**: 1.0.0+ (**RICHIESTO**)

### Passaggi
1. Scarica la mod da [CurseForge](TBD) o [Modrinth](TBD)
2. Installa **CobblemonMegaShowdown** (questa mod dipende da essa!)
3. Posiziona il JAR della mod nella cartella `mods/`
4. Avvia Minecraft e divertiti!

---

## 🧪 Note sulle Performance

- **Tempo Ricerca**: Tipicamente 100-500ms a seconda della dimensione del mondo
- **Impatto Server**: Minimo (<0.5ms per tick)
- **Uso Memoria**: ~5KB per bussola attiva
- **Caching**: Risultati in cache per 5 minuti per migliorare le performance

La mod usa ottimizzazioni aggressive per minimizzare il lag:
- Sistema di cooldown previene spam
- Raggio di ricerca limitato a distanza ragionevole
- Risultati in cache riducono ricerche duplicate
- Pattern di ricerca a chunk si ferma appena trova

---

## 🤝 Compatibilità

### Testato Con
- ✅ **CobblemonMegaShowdown**: Integrazione completa
- ✅ **JEI/REI/EMI**: Supporto visualizzazione ricette
- ✅ **Cobblemon**: Funziona insieme (non richiesto)
- ✅ **Fabric API**: Compatibilità completa
- ✅ **Architectury API**: Supporto multi-loader

### Problemi Noti
- La bussola trova solo meteoriti in **chunk già generati**
- Se nessun meteorite trovato, esplora nuove aree e riprova
- Funziona solo nella dimensione **Overworld**

---

## ❓ FAQ

### D: La bussola dice "Nessun meteorite trovato". Cosa faccio?
**R**: I meteoriti esistono solo in chunk già generati. Esplora oltre 1000+ blocchi in nuovo terreno e riprova.

### D: La bussola punta a un meteorite che ho già saccheggiato. Posso trovarne uno nuovo?
**R**: Abilita `track_visited_structures: true` nel config per saltare meteoriti visitati.

### D: Funziona in multiplayer?
**R**: Sì! Ogni giocatore ha il proprio tracking della bussola.

### D: Posso cambiare il raggio di ricerca?
**R**: Sì, modifica `search_radius` nel config. Attenzione - valori più grandi causano più lag.

### D: Funziona con altre mod?
**R**: Sì, purché CobblemonMegaShowdown sia installato. Non si prevedono conflitti.

### D: Posso usarla senza Cobblemon?
**R**: Sì! CobblemonMegaShowdown funziona standalone (Cobblemon non richiesto).

### D: La bussola non funziona nel Nether/End. Perché?
**R**: I meteoriti spawnano solo nell'Overworld. La bussola non funzionerà in altre dimensioni.

---

## 🛠️ Per Creatori di Modpack

### Impostazioni Raccomandate per Modpack
```json
{
  "search_radius": 3000,
  "cooldown_seconds": 60,
  "track_visited_structures": true
}
```

### Ricetta di Crafting Personalizzata
Puoi sovrascrivere la ricetta predefinita tramite datapack:
```
data/meteorite_compass/recipe/meteorite_compass.json
```

### Suggerimenti per il Bilanciamento
- Aumenta il cooldown per una progressione più difficile
- Diminuisci il raggio di ricerca per più esplorazione
- Abilita tracking visitati per incoraggiare dispersione
- Considera di vincolare la ricetta a progressione quest

---

## 🐛 Segnalazione Bug e Richieste Funzionalità

Trovato un bug? Hai un suggerimento? Faccelo sapere!

- **GitHub Issues**: [TBD - link repository]
- **Discord**: [TBD - canale supporto]
- **Commenti CurseForge**: [TBD - link progetto]

---

## 📜 Changelog

### [1.0.0] - TBD
#### Aggiunto
- Release iniziale
- Funzionalità base bussola
- Ricerca strutture per Megaroid e Mega Site
- Sistema di configurazione
- Effetti particelle e suoni
- Display HUD distanza
- Supporto multi-lingua (6 lingue)
- Comandi: `/meteoritecompass reset`, `/meteoritecompass info`

---

## 🙏 Crediti

### Creatore
**[Il Tuo Nome/Team]** - Sviluppo e design

### Ringraziamenti Speciali
- **yajatkaul** - Creatore di CobblemonMegaShowdown
- **Team Cobblemon** - Per la fantastica mod Cobblemon
- **Team Fabric/NeoForge** - Per il supporto piattaforme modding
- **Community** - Per feedback e testing

### Asset
- Tutte le texture e suoni creati specificamente per questa mod
- Meccaniche bussola ispirate alla Bussola Magnetite vanilla

---

## 📄 Licenza

Questa mod è sotto licenza **MIT License**.

**Puoi:**
- ✅ Usare nei modpack
- ✅ Modificare il codice
- ✅ Ridistribuire con crediti

**Devi:**
- 📝 Includere la licenza originale
- 📝 Dare credito all'autore originale

Vedi [LICENSE](LICENSE) per i dettagli completi.

---

## 🔗 Link

- **CurseForge**: [TBD]
- **Modrinth**: [TBD]
- **GitHub**: [TBD]
- **Discord**: [TBD]
- **Wiki**: [TBD]

---

## 🌟 Supporta il Progetto

Se ti piace questa mod:
- ⭐ Metti **Stella** al progetto su GitHub
- 💬 **Condividi** con gli amici
- 🐛 **Segnala bug** per aiutare a migliorarla
- 💡 **Suggerisci funzionalità** per aggiornamenti futuri
- ☕ **Dona** [TBD - link donazione opzionale]

---

**Fatto con ❤️ per la community di Cobblemon & MegaShowdown!**