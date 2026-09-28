# Changelog

Tutti i cambiamenti notevoli alla mod **Mega Compass** saranno documentati in questo file.

Il formato è basato su [Keep a Changelog](https://keepachangelog.com/it/1.1.0/),
e questo progetto segue il [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.1.1] - 2026-09-28

### Aggiunto
- **Overlay HUD di Stato in Gioco**: Aggiunto `CompassHudRenderer` (`com.megacompass.client.render.CompassHudRenderer`) per visualizzare lo stato della ricerca in tempo reale (In ricerca con campioni e raggio, Trovata con nome struttura, coordinate esatte e distanza, o Non trovata) nell'angolo in alto a sinistra dell'HUD quando si impugna una bussola.
- **Registrazione Modelli 3D Dinamici**: Registrati i caricatori di modelli 3D baked personalizzati tramite `ModelLoadingPlugin` di Fabric in `MegaCompassClient` per tutte le varianti di bussola.
- **Restrizione Dimensione Overworld**: Aggiunto il blocco della ricerca nel Nether e nell'End con notifica overlay tradotta (`string.mega_compass.status.wrong_dimension`) in tutte le 8 lingue supportate.
- **Gestione Ciclo di Vita Worker**: Registrati listener di disconnessione (`ServerPlayConnectionEvents.DISCONNECT`) e cambio dimensione (`ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD`) per arrestare e pulire automaticamente i worker attivi.
- **Cooldown Anti-Spam**: Aggiunto un cooldown di 1 secondo all'avvio della ricerca per prevenire spam di pacchetti e sovraccarico di worker.
- **Workflow di Rilascio Automatico**: Aggiunto workflow GitHub Actions (`.github/workflows/publish.yml`) per la pubblicazione automatica su CurseForge, Modrinth e GitHub Releases al push di un tag di versione (`v*`).
- **Integrazione Mod Publish Plugin**: Integrato `me.modmuss50.mod-publish-plugin` in `build.gradle` con estrazione automatica del changelog da `Docs/CHANGELOG.en.md`.

### Modificato
- **Aggiornamento Fabric Loader**: Aggiornata la versione di Fabric Loader a `0.18.0` in `gradle.properties` per garantire la compatibilità con le mod.
- **Proprietà di Pubblicazione**: Aggiunti `mod_id`, `mod_name`, `curseforge_project_id` e `modrinth_project_id` in `gradle.properties`.
- **Aggiornamento Link CurseForge**: Corretto lo slug ufficiale del progetto in `cobblemon-mega-showdown-mega-compass` in `fabric.mod.json`, `README.md` e documentazione.
- **Allineamento Documentazione**: Allineati README e documentazione italiana per descrivere accuratamente tutte le 6 bussole e la ricetta master a 5 ingredienti.

### Corretto
- **Time Budget Tick Server & Gestione Eccezioni**: Corretto il calcolo del tempo residuo in `WorldWorkerManager` con controlli sicuri e gestione eccezioni per prevenire cali di TPS o crash del server tick.
- **Risoluzione Dinamica ItemStack**: Risolto il leak di memoria e la mancata sincronizzazione degli item aggiornando dinamicamente l'oggetto tenuto in mano dal giocatore alla conclusione della ricerca (`StructureSearchWorker.getActiveCompassStack()`).
- **Sincronizzazione Campioni HUD**: Risolto il conteggio campioni bloccato a 0 durante la ricerca sincronizzando `SAMPLES_COMPONENT` ad ogni avanzamento del raggio.
- **Rotazione Ago per Dimensione**: Risolto il puntamento errato dell'ago 3D quando ci si trova in dimensioni diverse dall'Overworld.

### Sicurezza
- **Validazione Target Payload C2S**: Risolta vulnerabilità in `SearchPacket` che consentiva di manipolare il bersaglio di strutture su bussole a bersaglio fisso.

### Prestazioni
- **Render Loop 3D a Zero Allocazioni**: Eliminate le allocazioni `Quaternionf` e `Vector3f` per quad in `MegaCompassBakedModel`, sostituite con trasformazioni trigonometriche dirette inline.

### Rimosso
- **File di Backup Residui**: Rimossi i file `.bak` obsoleti (`icon.png.bak`, `compass_base_preset.json.bak`) dalle risorse di produzione.

---

## [1.1.0] - 2026-08-08

### Aggiunto
- **Initializer & Entrypoint Client**: Aggiunta la classe `MegaCompassClient` (`com.megacompass.client.MegaCompassClient`) che implementa `ClientModInitializer` per registrare il callback di apertura della GUI `MegaCompassScreen`.
- **Calcolo Angolo 3D in Tempo Reale**: Aggiunto il metodo `MegaCompassClient.getCompassAngle(...)` che calcola in tempo reale la rotazione dell'ago verso le coordinate della struttura in base alla posizione e al yaw del giocatore.
- **Renderer Modello 3D Composito**: Aggiunti il caricatore di modelli unbaked `MegaCompassModel` e il renderer 3D composito `MegaCompassBakedModel` per il baking personalizzato dell'item.
- **Tooltip Dinamico dello Stato**: Migliorato `MegaCompassItem.appendTooltip(...)` per mostrare lo stato dinamico della ricerca (Ricerca in corso con raggio e campioni, Trovata con coordinate X/Z esatte, Non trovata entro il raggio, oppure Lore inattiva) con componenti di testo traducibili.
- **Supporto Osservatorio & Sito Archeologico**: Integrato il supporto alla ricerca per le strutture Osservatorio (`mega_showdown:observatory`) e Sito Archeologico (`mega_showdown:archaeological_site`).
- **Nuovi Item & Modelli Bussola**: Aggiunte la Bussola dell'Osservatorio (`observatory_compass`) e la Bussola del Sito Archeologico (`archaeological_compass`) con le relative definizioni di modello (`observatory_compass.json`, `observatory_compass_base.json`, `archaeological_compass.json`, `archaeological_compass_base.json`).
- **Nuove Ricette di Crafting**: Aggiunte le ricette di crafting per la Bussola dell'Osservatorio (`observatory_compass.json`) e la Bussola del Sito Archeologico (`archaeological_compass.json`).

### Modificato
- **Pannello GUI Espanso**: Aumentata l'altezza del pannello in `MegaCompassScreen` da 170px a 220px e aggiunti i pulsanti di selezione per Osservatorio e Sito Archeologico con posizionamento relativo.
- **Inizializzatore Principale Aggiornato**: Aggiornata la classe principale `MegaCompass` per registrare i nuovi item bussola, i componenti dati e le voci nel tab della modalità creativa.
- **Ricetta Combinata Aggiornata**: Aggiornata la ricetta della `MegaCompass` combinata (`mega_compass.json`) per richiedere tutte e 5 le bussole individuali.
- **Utility Strutture & Pacchetto di Rete**: Aggiornati `StructureUtils` e `SearchPacket` per registrare e validare gli identificatori target di Osservatorio e Sito Archeologico.
- **Traduzioni Aggiornate**: Aggiornati i file di traduzione in tutte le 8 lingue supportate (`en_us`, `it_it`, `de_de`, `es_es`, `fr_fr`, `pt_br`, `ru_ru`, `zh_cn`).

### Corretto
- **Fix Regola Gitignore**: Corretta la regola in `.gitignore` per il pattern `client/` (modificata in `/client/`) permettendo a Git di tracciare correttamente il package sorgente `src/main/java/com/megacompass/client/`.
- **Fix Crash Avvio Client**: Risolto il crash all'avvio del client (`ClassNotFoundException` per `MegaCompassClient`).

### Rimosso
- **Modelli & Texture Obsolete**: Rimossi i file di override del puntatore (`mega_compass_pointer.json`, ecc.) e le texture 2D statiche non utilizzate a favore del sistema di rendering 3D dinamico baked.

---

## [1.0.0] - 2026-05-13

### Aggiunto
- **Modelli 3D Dinamici**: Migrazione dalle animazioni 2D a frame statici a un sistema di rendering 3D dinamico che combina i preset della base (`compass_base_preset.json`) e del puntatore (`compass_pointer_preset.json`).
- **Thread Safety Multiplayer**: Implementato `WorldWorkerManager` con un registro worker thread-safe basato su `CopyOnWriteArrayList` che supporta ricerche contemporanee per più giocatori fino a un limite massimo globale di 100 worker.
- **Localizzazione Globale**: Aggiunto il supporto ufficiale per 6 nuove lingue (`es_es`, `fr_fr`, `de_de`, `pt_br`, `ru_ru`, `zh_cn`), portando il totale a 8 lingue supportate.
- **Feedback Messaggi Hotbar**: Aggiunti messaggi overlay sopra la barra degli oggetti del giocatore all'avvio della ricerca da GUI.

### Modificato
- **Rebrand della Mod**: Rinominata la mod da "Meteorite Compass" a "Mega Compass" (`mega_compass`) in tutti i package, namespace, ID item, componenti e data pack.
- **Nome Item Combinato**: Rinominato l'item bussola combinata in "Mega Compass".
- **Refactoring dei Modelli**: Creati preset di modelli riutilizzabili per eliminare la duplicazione di codice JSON nei modelli degli item.

### Corretto
- **Fix Ago Bloccato**: Risolto problema di rendering dell'ago aggiornando i predicati del modello per `compass_angle`.
- **Fix Tremolio Ago**: Risolto il tremolio dell'ago quando inattivo o quando la struttura non viene trovata.

---

## [0.1.1] - 2026-02-27

### Corretto
- **Crash Pacchetti di Rete**: Risolto crash del client (`ClassCastException`) durante la serializzazione dei pacchetti passando a `RegistryByteBuf`.
- **Bug Selezione GUI**: Risolto bug in cui cliccando su "Mega Site" nella GUI veniva avviata la ricerca di "Megaroid".
- **Rendering Testo GUI**: Corretti testi sfocati e traduzioni mancanti nel pannello della GUI.

---

## [0.1.0] - 2026-02-25

### Aggiunto
- **Rilascio Iniziale**: Aggiunta l'implementazione iniziale della mod Meteorite Compass per le strutture di Cobblemon MegaShowdown (`megaroid` e `mega_site`).
- **Ricerca Non-Blocking**: Implementato l'algoritmo di ricerca a spirale asincrono (`RandomSpreadSearchWorker`, `SearchWorkerManager`) fino a 10.000 blocchi di raggio.
- **GUI di Selezione**: Aggiunta la schermata GUI interattiva (`MegaCompassScreen`) e la gestione dei pacchetti C2S (`SearchPacket`).
- **Ricette & Lingue**: Aggiunte ricette vanilla (Ferro, Ametista, Glowstone, Bussola) e i file di lingua iniziali Inglese (`en_us`) e Italiano (`it_it`).
