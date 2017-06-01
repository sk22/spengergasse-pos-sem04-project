# JavaFX weather app
Weather app build with JavaFX for school

## Proposal

### Features

* Displaying weather details for specified cities
* Displaying a temperature graph using JavaFX's API
* Fetching weather details from an online API (Weather Underground)
* Places can be added and removed
* Persisting those places in a configuration file
* Exporting and importing the current configuration as a JSON file

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
  - Temperature forecast graph

#### Add Dialog

* Text fields for the state and city
* OK and Cancel buttons

