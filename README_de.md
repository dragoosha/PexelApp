# PexelApp

PexelApp ist eine Android-Anwendung, die es Benutzern ermöglicht, atemberaubende, hochwertige Stockfotos und Videos zu erkunden und zu durchsuchen, die über die Pexels API bezogen werden. Mit einer nahtlosen Benutzererfahrung können Benutzer kostenlose, lizenzfreie Medien entdecken, die von Kreativen geteilt werden, und sie in ihren Projekten verwenden.

## Sprachen
- [English](README.md)
- [Русский](README_ru.md)
- [German](README_de.md)

## Funktionen

- **Hochwertige Medien durchsuchen:** Suche und erkunde eine große Auswahl an Bildern und Videos, die aus der Pexels API stammen.
- **Unterstützung für Paging:** Effizientes Laden großer Bildmengen mit Paging3 für flüssiges Scrollen.
- **Favoriten:** Speichere deine Lieblingsbilder und -videos für schnellen Zugriff.
- **Suchfunktion:** Finde Bilder und Videos ganz einfach anhand von Schlüsselwörtern.
- **Offline-Speicherung:** Verwende Room zum Zwischenspeichern und Zugreifen auf Medien im Offline-Modus.

## Technologiestack

- **Android SDK**: Kernentwicklungswerkzeuge für Android.
- **Jetpack Compose**: Modernes UI-Toolkit für den Bau nativer Android-Oberflächen.
- **Jetpack Navigation**: Vereinfacht die Navigation zwischen Bildschirmen.
- **Room**: Lokale Datenbank zum Zwischenspeichern von Mediendaten.
- **Retrofit**: HTTP-Client zur Interaktion mit der Pexels API.
- **Dagger/Hilt**: Dependency Injection für eine saubere und modulare Architektur.
- **Coroutines + Flow**: Asynchrones Programmieren für eine reaktionsschnelle UI.
- **Paging3**: Effizientes Laden und Paginieren großer Mediendatensätze.

## Vor der Nutzung

Um die Anwendung auszuführen, musst du zunächst deinen **API-Schlüssel** von Pexels zur Datei `local.properties` hinzufügen. Deinen API-Schlüssel erhältst du, indem du dich auf [Pexels](https://www.pexels.com/api/) anmeldest.

Füge die folgende Zeile in deine `local.properties`-Datei ein:
```
API_KEY=your_pexels_api_key_here
```
markdown
Копировать
Редактировать

## Erste Schritte

### Voraussetzungen

Um dieses Projekt lokal auszuführen, stelle sicher, dass du Folgendes hast:

- Android Studio installiert
- Ein physisches Android-Gerät oder einen Emulator
- Eine aktive Internetverbindung zum Abrufen von Medien über die Pexels API

### Installation

1. **Repository klonen:**

    ```bash
    git clone https://github.com/dragoosha/PexelApp.git
    ```

2. **Projekt in Android Studio öffnen:**
   - Öffne das geklonte Repository in Android Studio und lasse die Abhängigkeiten synchronisieren.

3. **Pexels API-Schlüssel hinzufügen:**
   - Wie bereits erwähnt, füge deinen Pexels API-Schlüssel zur Datei `local.properties` hinzu.

4. **App ausführen:**
   - Verbinde ein Android-Gerät oder starte einen Emulator und führe die App über Android Studio aus.

## Nutzung

1. **Medien erkunden:** Durchstöbere trendige Fotos, Videos und Kategorien.
2. **Medien suchen:** Verwende die Suchleiste, um Bilder und Videos anhand von Schlüsselwörtern zu finden.
3. **Favoriten speichern:** Speichere deine Lieblingsbilder und -videos für schnellen Zugriff.
4. **Offline-Modus:** Betrachte zuvor gespeicherte Medien im Offline-Modus.

## Funktionsweise

Die App integriert die Pexels API, um hochwertige Bilder und Videos basierend auf Benutzeranfragen abzurufen. Sie nutzt **Paging3**, um Daten in Blöcken zu laden und effizient darzustellen.

Die folgenden Hauptkomponenten werden in der App verwendet:

- **Retrofit**: Führt Netzwerkaufrufe zur Medienabfrage von Pexels aus.
- **Room**: Speichert Bilder und Videos lokal für den Offline-Zugriff.
- **Coroutines + Flow**: Ermöglicht asynchrone Netzwerkaufrufe und Datenbankinteraktionen für eine flüssige UI.
- **Dagger/Hilt**: Verwaltet die Dependency Injection und hält den Code sauber und modular.

## Beitrag zum Projekt

Beiträge sind willkommen! Du kannst das Repository forken, einen Branch erstellen und Pull Requests mit Fehlerbehebungen, Verbesserungen oder neuen Funktionen einreichen.

### So trägst du bei

1. Forke das Repository.
2. Erstelle einen neuen Branch für deine Funktion oder Fehlerbehebung.
3. Implementiere die Änderungen und füge ggf. Tests hinzu.
4. Commite deine Änderungen mit einer aussagekräftigen Nachricht.
5. Push deine Änderungen in dein Fork und erstelle einen Pull Request.

## Danksagungen

- [Pexels API](https://www.pexels.com/api/) für die Bereitstellung kostenloser Stockfotos und -videos.
- [Android Jetpack](https://developer.android.com/jetpack) für moderne Android-Entwicklungstools.
- [Paging3](https://developer.android.com/topic/libraries/architecture/paging) für effizientes Datenpaging.
- [Retrofit](https://square.github.io/retrofit/) für die einfache Handhabung von HTTP-Anfragen.
