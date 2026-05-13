# Changelog - Mega Compass

Tutti i cambiamenti notevoli alla mod **Mega Compass** saranno documentati in questo file.

Il formato è basato su [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
e questo progetto segue il [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## [Non Rilasciato]

### Pianificato
- Sistema di configurazione JSON
- Tracciamento strutture visitate
- Effetti particelle quando meteorite localizzato
- Effetti sonori per feedback ricerca
- Integrazione ricette JEI/REI/EMI
- Sistema comandi per debug

---

## [2.0.0] - 13 Maggio 2026 (Major 3D & Refactoring Update)

### Aggiunto
- **Modelli 3D Dinamici**: Migrazione completa dal vecchio sistema di animazione 2D a 32 frame a un sistema di rendering 3D dinamico e moderno.
- **Rendering Ago stile AE2**: Implementato un sistema custom di `BakedModel` e `FabricBakedModel` che calcola matematicamente la rotazione dell'ago in tempo reale in base alla posizione del giocatore e dello stato della bussola.
- **Indipendenza Multiplayer**: Logica di ricerca rifatta da zero per supportare più giocatori contemporaneamente senza interferenze, utilizzando un registro dei worker basato su UUID.
- **Sicurezza Migliorata**: Aggiunta la validazione lato server per le richieste di ricerca, prevenendo l'invio di pacchetti malevoli dal client.
- **Robustezza e Performance**: Implementata una gestione dei worker thread-safe con un limite globale di 100 worker attivi per proteggere la stabilità del server.
- **Miglioramenti GUI & UX**: Riprogettata l'interfaccia di selezione con posizionamento relativo e aggiunto un messaggio di feedback in tempo reale nella barra dei messaggi (hotbar).
- **Localizzazione Globale**: Aggiunto il supporto ufficiale per 6 nuove lingue: Spagnolo (ES), Francese (FR), Tedesco (DE), Portoghese Brasiliano (PT-BR), Russo (RU) e Cinese Semplificato (ZH-CN). La mod ora supporta 8 lingue in totale.

### Modificato
- **Mod Rename Engine**: Rinominata completamente la mod da "Meteorite Compass" a "Mega Compass".
- **Aggiornamento Namespace**: Tutti gli ID interni, i registri degli oggetti, le cartelle delle risorse, le chiavi di traduzione e i pacchetti dati ora utilizzano il namespace `mega_compass`.
- **Bussola Unificata**: La bussola combinata è ora ufficialmente conosciuta come "Mega Bussola".
- **Riprogettazione HUD**: Riprogettata completamente la visualizzazione a schermo. L'interfaccia ora presenta una lista verticale pulita e allineata in alto a sinistra con etichette bianche e valori grigio chiaro.
- **Ottimizzazione Risorse**: Ridotto significativamente il peso della mod eliminando centinaia di file JSON e texture di frame ridondanti.
- **Personalizzazione Texture Migliorata**: Ogni bussola ora utilizza un singolo file di texture dedicato (`.png`), rendendo estremamente semplice per gli utenti creare ricolorazioni o texture personalizzate di alta qualità.
- **Refactoring dei Modelli**: Implementato un sistema di template (`preset`) per i modelli della base e del puntatore, riducendo drasticamente la duplicazione del codice JSON e facilitando la manutenzione globale dei modelli 3D.
- **Raffinamento Tecnico**: Rimossi i componenti dati ridondanti, risolti tutti i warning dell'IDE e pinate le dipendenze di build (Loom 1.8.13) a versioni stabili.

### Risolto
- **Animazione Ago della Bussola**: Risolto un problema critico di rendering in cui l'ago della bussola rimaneva bloccato. I modelli degli oggetti sono stati corretti per interpretare correttamente il predicato `mega_compass:compass_angle` in modo che l'ago punti fluidamente verso il meteorite più vicino.
- **Fix Jitter dell'Ago**: L'ago della bussola non trema più freneticamente ogni frame quando è inattivo o quando non viene trovato un meteorite. Ora riposa tranquillamente nella sua posizione predefinita.

---

## [1.0.1] - 27 Febbraio 2026 (Bugfix)

### Corretto
- Risolto un crash del client (`ClassCastException`) nell'invio dei pacchetti passando a `RegistryByteBuf`
- Risolta la logica della GUI che cercava sempre "Megaroid" indipendentemente dal pulsante selezionato (Mega Site)
- Risolti problemi di traduzione mancante e testo sfocato (blur) nel pannello della GUI

---

## [1.0.0] - 25 Febbraio 2026 (Release Iniziale)

### Aggiunto
- **Funzionalità Core**
  - Item Bussola che localizza meteoriti da CobblemonMegaShowdown
  - Ricerca strutture per "mega_showdown:megaroid" (Y -32 a -20)
  - Ricerca strutture per "mega_showdown:mega_site" (Y -19 a 5)
  - Attivazione click destro per cercare meteorite più vicino
  - Shift + click destro per resettare bussola

- **Sistema di Ricerca Avanzato**
  - WorldWorkerManager per coordinamento worker asincroni
  - RandomSpreadSearchWorker con algoritmo ricerca a spirale
  - Ricerca non-blocking fino a 10.000 blocchi di raggio
  - Campionamento fino a 100.000 punti

- **Esperienza Utente**
  - Display HUD in tempo reale durante ricerca
  - Mostra progresso (campioni, raggio) durante ricerca
  - Mostra distanza quando meteorite trovato
  - Feedback colore (bianco=ricerca, verde=trovato, rosso=non trovato)

- **Ricetta di Crafting**
  - Bussola craftabile con materiali vanilla: Ferro, Ametista, Glowstone, Bussola
  
- **Architettura Tecnica**
  - Piattaforma: Fabric 1.21.1
  - Fabric Loader: 0.16.9+
  - Fabric API: 0.108.0+1.21.1

- **Localizzazione**
  - Italiano (it_it)
  - Inglese (en_us)

- **Comandi**
  - `/meteoritecompass reset` - Cancella tracking meteoriti visitati
  - `/meteoritecompass info` - Mostra stato bussola e target

---

## Roadmap Sviluppo

### Fase 1 - Core (v1.0.0) ✅
- [x] Funzionalità base bussola
- [x] Logica ricerca strutture
- [x] Supporto multi-loader base
- [x] Implementazione comandi shell

### Fase 2 - Polish (v1.1.0) ✅
- [x] Bussole dedicate per ogni struttura
- [x] Bussola combinata con GUI di selezione target
- [x] Fix texture e icone personalizzate 
- [x] Funzionamento corretto orientamento ago

### Fase 3 - Avanzato & Rendering (v2.0.0) ✅
- [x] Refactoring totale Namespace ID e Classi
- [x] Rename finale mod in "Mega Compass" per coerenza tematica
- [x] Migrazione a Modelli 3D dinamici (stile AE2)
- [x] Ottimizzazione degli asset e delle performance
- [ ] Tracking strutture visitate per evitarne il ri-lookup
- [ ] Integrazione JEI/REI per ricette
- [ ] Effetti particellari direzionali e Suoni
- [ ] Supporto datapack configurazione esteso
- [ ] Implementazione Json configuration

---

*Formato: [Versione] - Data*  
*Categorie: Aggiunto, Modificato, Corretto, Rimosso, Tecnico*
