# Changelog - Mega Compass

Tutti i cambiamenti notevoli alla mod **Mega Compass** saranno documentati in questo file.

Il formato è basato su [Keep a Changelog](https://keepachangelog.com/it/1.1.0/),
e questo progetto segue il [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.1.0] - 2026-08-07

### Aggiunto
- **Strutture Osservatorio e Sito Archeologico**: Integrato il supporto alla ricerca delle strutture Osservatorio (`mega_showdown:observatory`) e Sito Archeologico (`mega_showdown:archaeological_site`).
- **Nuove Bussole Dedicate**: Aggiunte la Bussola dell'Osservatorio (`observatory_compass`) e la Bussola del Sito Archeologico (`archaeological_compass`) complete di modelli 3D dedicati, texture e ricette di crafting.
- **Espansione Pannello GUI**: Aumentata l'altezza della GUI della Mega Bussola da 170px a 220px e aggiunti i pulsanti interattivi di selezione per l'Osservatorio e il Sito Archeologico.
- **Localizzazione Globale**: Aggiunte le chiavi di traduzione in tutte e 8 le lingue supportate (`de_de`, `en_us`, `es_es`, `fr_fr`, `it_it`, `pt_br`, `ru_ru`, `zh_cn`) per i nuovi oggetti bussola e nomi delle strutture.

### Modificato
- **Validazione Pacchetti di Rete**: Aggiornata la validazione lato server in `SearchPacket` per riconoscere ed elaborare le richieste di ricerca dei nuovi ID struttura.
- **Utility Registro Strutture**: Aggiornato `StructureUtils` per registrare gli identificatori dell'Osservatorio e del Sito Archeologico e gestirne i nomi visualizzati.
- **Ricetta Mega Bussola**: Aggiornata la ricetta di crafting della Mega Bussola combinata (`mega_compass.json`) per incorporare le nuove bussole nella struttura di crafting.

---

## [1.0.0] - 2026-05-13

### Aggiunto
- **Modelli 3D Dinamici**: Migrazione completa dal vecchio sistema di animazione 2D a 32 frame a un sistema di rendering 3D dinamico e moderno.
- **Rendering Ago**: Implementato un sistema custom di `BakedModel` e `FabricBakedModel` che calcola matematicamente la rotazione dell'ago in tempo reale in base alla posizione del giocatore e allo stato della bussola.
- **Indipendenza Multiplayer**: Logica di ricerca rifatta da zero per supportare più giocatori contemporaneamente senza interferenze, utilizzando un registro dei worker basato su UUID.
- **Robustezza e Sicurezza**: Implementata una gestione dei worker thread-safe con un limite globale di 100 worker attivi e aggiunta la validazione dei pacchetti lato server.
- **Miglioramenti GUI & UX**: Riprogettata l'interfaccia di selezione con posizionamento relativo e aggiunto un messaggio di feedback in tempo reale nella barra dei messaggi (hotbar).
- **Localizzazione Globale**: Aggiunto il supporto ufficiale per 6 nuove lingue: Spagnolo (`es_es`), Francese (`fr_fr`), Tedesco (`de_de`), Portoghese Brasiliano (`pt_br`), Russo (`ru_ru`) e Cinese Semplificato (`zh_cn`), portando il supporto totale a 8 lingue.

### Modificato
- **Mod Rename**: Rinominata completamente la mod da "Meteorite Compass" a "Mega Compass" in tutto il codice, namespace, registri oggetti, cartelle risorse, chiavi di traduzione e pacchetti dati.
- **Bussola Unificata**: La bussola combinata è ora ufficialmente conosciuta come "Mega Bussola".
- **Riprogettazione HUD**: Riprogettata completamente la visualizzazione a schermo con una lista verticale pulita e allineata in alto a sinistra con etichette bianche e valori grigio chiaro.
- **Ottimizzazione Risorse**: Ridotto significativamente il peso della mod eliminando centinaia di file JSON e texture di frame ridondanti.
- **Personalizzazione Texture**: Ogni bussola ora utilizza un singolo file di texture dedicato (`.png`), rendendo estremamente semplice per gli utenti creare ricolorazioni e texture personalizzate.
- **Refactoring dei Modelli**: Implementato un sistema di template (`preset`) per i modelli della base e del puntatore, riducendo drasticamente la duplicazione del codice JSON.
- **Raffinamento Tecnico**: Rimossi i componenti dati ridondanti, risolti tutti i warning dell'IDE e fissate le dipendenze di build (Loom 1.8.13).

### Risolto
- **Animazione Ago della Bussola**: Risolto problema critico di rendering in cui l'ago rimaneva bloccato, correggendo i modelli per interpretare il predicato `mega_compass:compass_angle`.
- **Fix Jitter dell'Ago**: Risolto il tremolio dell'ago quando inattivo o quando la struttura non viene trovata, riportandolo alla posizione di riposo predefinita.

---

## [0.1.1] - 2026-02-27

### Corretto
- **Crash Pacchetti di Rete**: Risolto crash del client (`ClassCastException`) durante la serializzazione dei pacchetti di rete passando a `RegistryByteBuf`.
- **Logica Selezione GUI**: Corretto bug in cui cliccando su "Mega Site" nella GUI la ricerca impostava erroneamente "Megaroid".
- **Rendering Testi GUI**: Risolti problemi di traduzione mancante e testo sfocato (blur) nel pannello della GUI.

---

## [0.1.0] - 2026-02-25

### Aggiunto
- **Funzionalità Core**: Aggiunto item bussola iniziale per localizzare le strutture di CobblemonMegaShowdown (`megaroid` e `mega_site`). Click destro attiva la ricerca, Shift + click destro resetta la bussola.
- **Sistema di Ricerca Avanzato**: Implementati `WorldWorkerManager` e `RandomSpreadSearchWorker` con algoritmo a spirale non-blocking fino a 10.000 blocchi di raggio.
- **Esperienza Utente**: Aggiunto display HUD in tempo reale con conteggio campioni, raggio, distanza e feedback a colori.
- **Ricetta di Crafting**: Aggiunta ricetta con materiali vanilla (Ferro, Ametista, Glowstone, Bussola).
- **Localizzazione**: Aggiunti file di lingua per Inglese (`en_us`) e Italiano (`it_it`).
- **Comandi**: Aggiunti i comandi `/meteoritecompass reset` e `/meteoritecompass info`.

---

## Roadmap Sviluppo

### Fase 1 - Core (v0.1.0) ✅
- Setup iniziale del progetto e configurazione Fabric 1.21.1.
- Implementazione worker di ricerca strutture.
- Comandi base e localizzazione iniziale.

### Fase 2 - Multi-Bussola & GUI (v0.1.1) ✅
- Bussole dedicate per ogni tipo di struttura.
- Bussola combinata con GUI di selezione target.
- Animazioni orientamento ago corrette.

### Fase 3 - Engine 3D & Rebrand (v1.0.0) ✅
- Rename completo mod in Mega Compass.
- Sistema 3D dynamic model baking per l'ago puntatore.
- Registro worker thread-safe per multiplayer.
- Localizzazione globale in 8 lingue.

### Fase 4 - Espansione & Funzionalità (v1.1.0) 📋
- Supporto alle strutture Osservatorio e Sito Archeologico.
- Estensione del pannello di selezione GUI e delle ricette.
- Sistema di tracking delle strutture già visitate.
- Effetti sonori e indicatori particellari personalizzati.
- Sistema di configurazione JSON esterno.

---

*Formato: [Versione] - Data*  
*Categorie: Aggiunto, Modificato, Corretto, Rimosso, Tecnico*
