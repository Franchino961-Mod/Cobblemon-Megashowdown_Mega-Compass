# Changelog - Meteorite Compass

Tutti i cambiamenti notevoli alla mod **Meteorite Compass** saranno documentati in questo file.

Il formato è basato su [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
e questo progetto segue il [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## [Non Rilasciato]

### Pianificato
- Sistema di configurazione JSON
- Tracciamento strutture visitate
- Animazione ago bussola con ItemPropertyFunction
- Effetti particelle quando meteorite localizzato
- Effetti sonori per feedback ricerca
- Supporto lingue aggiuntive (ES, FR, DE, PT-BR)
- Integrazione ricette JEI/REI/EMI
- Sistema comandi per debug

---

## [1.0.1] - 27 Febbraio 2026 (Bugfix)

### Corretto
- Risolto bug per cui l'ago della bussola non si animava a causa del predicate mancante
- Risolto un crash del client (`ClassCastException`) nell'invio dei pacchetti passando a `RegistryByteBuf`
- Risolta la logica della GUI che cercava sempre "Megaroid" indipendentemente dal pulsante selezionato (Mega Site)
- Risolti problemi di traduzione mancante e testo sfocato (blur) nel pannello della GUI

---

## [1.0.0] - 25 Febbraio 2026 (Release Iniziale)

### Aggiunto
- **Funzionalità Core**
  - Item Bussola Meteorite che localizza meteoriti da CobblemonMegaShowdown
  - Ricerca strutture per "mega_showdown:megaroid" (Y -32 a -20)
  - Ricerca strutture per "mega_showdown:mega_site" (Y -19 a 5)
  - Attivazione click destro per cercare meteorite più vicino
  - Shift + click destro per resettare bussola
  - Componenti dati per storage stato bussola (INACTIVE, SEARCHING, FOUND, NOT_FOUND)

- **Sistema di Ricerca Avanzato**
  - WorldWorkerManager per coordinamento worker asincroni
  - SearchWorkerManager per gestione ricerche multiple
  - RandomSpreadSearchWorker con algoritmo ricerca a spirale
  - Ricerca non-blocking fino a 10.000 blocchi di raggio
  - Campionamento fino a 100.000 punti
  - Time-sliced execution (50ms per server tick)

- **Esperienza Utente**
  - Display HUD in tempo reale durante ricerca
  - Mostra progresso (campioni, raggio) durante ricerca
  - Mostra distanza quando meteorite trovato
  - Feedback colore (bianco=ricerca, verde=trovato, rosso=non trovato)
  - Client initializer per rendering HUD

- **Ricetta di Crafting**
  - Bussola craftabile con materiali vanilla:
    * 4x Lingotti Ferro
    * 2x Frammenti Ametista  
    * 2x Polvere di Glowstone
    * 1x Bussola
  - Early-game friendly, non richiede materiali da meteoriti

- **Networking**
  - Pacchetto SearchPacket (Client → Server) per iniziare ricerca
  - Pacchetto SyncPacket (Server → Client) per sync stato
  - Fabric Networking API integration

- **Architettura Tecnica**
  - Piattaforma: Fabric 1.21.1
  - Fabric Loader: 0.16.9+
  - Fabric API: 0.108.0+1.21.1
  - Yarn Mappings: 1.21.1+build.3
  - Java: 21
  - Gradle: 8.10.2
  - Fabric Loom: 1.8-SNAPSHOT

- **Localizzazione**
  - Italiano (it_it)
  - Inglese (en_us)
  - Italiano (it_it)
  - Spagnolo (es_es)
  - Francese (fr_fr)
  - Tedesco (de_de)
  - Portoghese Brasiliano (pt_br)

- **Funzionalità Tecniche**
  - Supporto multi-loader (Fabric + NeoForge)
  - Componente dati NBT per storage target
  - Caching ricerca strutture (scadenza 5 minuti)
  - Networking client-server per sync coordinate
  - Ottimizzazione performance (<0.5ms impatto server)

- **Comandi**
  - `/meteoritecompass reset` - Cancella tracking meteoriti visitati
  - `/meteoritecompass info` - Mostra stato bussola e target

### Note Tecniche
- Usa API StructureManager di Minecraft per localizzazione strutture
- Implementa ItemPropertyFunction personalizzata per animazione ago bussola
- Compatibile con Minecraft 1.21.1
- Richiede CobblemonMegaShowdown come dipendenza
- Costruito su Architectury API per architettura multi-loader

### Limitazioni Note
- Trova solo meteoriti in chunk già generati
- Funziona solo nella dimensione Overworld
- Performance ricerca dipende da dimensione mondo e densità strutture

---

## Roadmap Sviluppo

### Fase 1 - Core (v1.0.0) ✅
- [x] Funzionalità base bussola
- [x] Logica ricerca strutture
- [x] Sistema configurazione
- [x] Supporto multi-loader
- [x] Localizzazione (6 lingue)
- [x] Implementazione comandi

### Fase 2 - Polish (v1.1.0) 🔄
- [ ] Tracking strutture visitate
- [ ] Codifica colore basata su distanza
- [ ] HUD migliorato con frecce direzione
- [ ] Integrazione JEI/REI
- [ ] Effetti particelle aggiuntivi
- [ ] Più variazioni sonore

### Fase 3 - Avanzato (v1.2.0) 📋
- [ ] Sistema bussola duale (separata per ogni tipo meteorite)
- [ ] Upgrade bussola (raggio aumentato)
- [ ] Sistema bussola ricaricabile
- [ ] Anteprima struttura nell'HUD
- [ ] Sistema marcatori waypoint
- [ ] Target bussola condivisi team (multiplayer)

### Fase 4 - Integrazione (v1.3.0) 📋
- [ ] Integrazione profonda con eventi CobblemonMegaShowdown
- [ ] Effetti particelle personalizzati corrispondenti tipi meteorite
- [ ] Sistema achievement
- [ ] Tracking statistiche (meteoriti trovati)
- [ ] Supporto datapack per strutture personalizzate

---

*Formato: [Versione] - Data*  
*Categorie: Aggiunto, Modificato, Corretto, Rimosso, Tecnico*
