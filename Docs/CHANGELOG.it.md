# Changelog

Tutti i cambiamenti notevoli alla mod **Mega Compass** saranno documentati in questo file.

Il formato è basato su [Keep a Changelog](https://keepachangelog.com/it/1.1.0/),
e questo progetto segue il [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

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
