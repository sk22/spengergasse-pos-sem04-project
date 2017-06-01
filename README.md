# JavaFX weather app
Weather app build with JavaFX for school

## Proposal

### Features

* Displaying weather details for specific places
* Fetching weather details from an online API
* Places can be added, removed and edited
* Persisting those places in a configuration file

### Technologies

* Java 8
* JavaFX 8
* FXML
* HTTP (GET Requests with `HttpURLConnection`)
* JSON (Parsing received data, using GSON)
* Multithreading (Multiple locations are fetched at once)

### Layout

#### Main Window

```
+-------------------------------+
+--------+----------------------+
|        |                      |
|        |                      |
|        |                      |
|        |                      |
|        |                      |
+--------+----------------------+

```

* Top Bar: File (Save, Import, Export, Exit)
* Sidebar: List of places
  - Top: Toolbar (Add, Remove, Refresh)
* Main Content: Weather details
  - Hard facts (Temperature, Air pressure, Wind, ...)
  - Forecast with icons

#### Editing Dialog

Used to edit or add a place.

* Text field for the place's name (searching the API for places)
* Optional display name

