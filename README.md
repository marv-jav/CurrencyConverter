# Currency Converter App

## Overview

This is a **Currency Converter** app built using **Kotlin**, **Jetpack Compose**, **MVVM architecture**, and **Hilt** for dependency injection. The app allows users to convert currencies in real-time, fetch historical exchange rates, and store them locally for offline use. The conversion is triggered when the user clicks the "Convert" button. The app interacts with the **Fixer.io API** to retrieve exchange rates and handle currency conversions.

### Features:
- **Currency Conversion**: Convert an amount from one currency to another using live exchange rates.
- **Historical Rates Fetching**: Fetch historical exchange rates between two currencies for a specified date range.
- **Offline Caching**: Store the exchange rates locally for offline access.
- **MVVM Architecture**: Separate the UI from business logic using the **MVVM** architecture pattern.
- **Coroutines for Async Operations**: Asynchronous operations using **Coroutines** to handle network requests and background tasks.
- **Hilt for Dependency Injection**: Use of **Hilt** for dependency injection, improving modularity and testability.

---

## Demo Video

You can find the demo video of the app in here
[Watch the demo video](https://drive.google.com/file/d/1eVXpG4H8d8AeQCulI3jxMsnAAYuKqlap/view?usp=sharing)