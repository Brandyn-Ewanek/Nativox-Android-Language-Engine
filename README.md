# Nativox-Android-Language-Engine
Native Android language learning engine built with Jetpack Compose, Canvas Waveforms, and MVVM architecture.
# Nativox: Practical Language Learning Engine

**A native Android application designed for rapid language acquisition using "Sentence Drills" and real-time audio visualization.**

## 📱 Project Overview
Nativox is a purpose-built tool for polyglots who need high-repetition practice. Unlike gamified apps (Duolingo) that focus on passive clicking, Nativox focuses on **"Jaw Muscle Memory"**—training the physical ability to speak.

It features a custom **Audio Visualization Engine** that renders real-time waveforms of native speaker audio, helping users match their prosody and intonation visually.

## 🏗️ Technical Architecture
The app follows modern **Android Development** standards (2025):
* **UI:** 100% **Jetpack Compose** (Material 3) for declarative UI.
* **Navigation:** Type-safe Compose Navigation with argument passing (`themes/{language}/{drillId}`).
* **Graphics:** Custom `Canvas` drawing loops for the dynamic audio waveform (Sine wave interpolation).
* **Architecture:** **MVVM** (Model-View-ViewModel) with Unidirectional Data Flow.

## ⚡ Key Features (Code Highlights)
### 1. The Waveform Engine (`PracticeScreen.kt`)
Instead of using a pre-built library, I engineered a custom **Canvas visualizer** that animates based on audio amplitude.
* **Math:** Uses sine wave superposition `sin(i * 0.8f) * amplitude` to create organic "liquid" motion.
* **Performance:** Optimized drawing loop ensures 60 FPS rendering even during complex animations.

### 2. The "SVO" Drill Logic
The app leverages the syntactic similarity between English, Spanish, Portuguese, and French (Subject-Verb-Object).
* **Swap Drills:** Rapidly changing one element (e.g., "I eat bread" -> "I eat *apples*").
* **Ladder Drills:** Progressively building sentence complexity.



## 🛠️ Tech Stack
* **Language:** Kotlin
* **Framework:** Android SDK, Jetpack Compose
* **State Management:** `remember`, `mutableStateOf`, `LaunchedEffect`
* **Tools:** Android Studio, Gradle

## 📄 Documentation
* **[Read the Design Prototype (PDF)](reports/# Nativox: High-Performance Language Learning Engine

**A native Android application designed for rapid language acquisition using "Sentence Drills" and real-time audio visualization.**

## 📱 Project Overview
Nativox is a purpose-built tool for polyglots who need high-repetition practice. Unlike gamified apps (Duolingo) that focus on passive clicking, Nativox focuses on **"Jaw Muscle Memory"**—training the physical ability to speak.

It features a custom **Audio Visualization Engine** that renders real-time waveforms of native speaker audio, helping users match their prosody and intonation visually.

## 🏗️ Technical Architecture
The app follows modern **Android Development** standards (2025):
* **UI:** 100% **Jetpack Compose** (Material 3) for declarative UI.
* **Navigation:** Type-safe Compose Navigation with argument passing (`themes/{language}/{drillId}`).
* **Graphics:** Custom `Canvas` drawing loops for the dynamic audio waveform (Sine wave interpolation).
* **Architecture:** **MVVM** (Model-View-ViewModel) with Unidirectional Data Flow.

## ⚡ Key Features (Code Highlights)
### 1. The Waveform Engine (`PracticeScreen.kt`)
Instead of using a pre-built library, I engineered a custom **Canvas visualizer** that animates based on audio amplitude.
* **Math:** Uses sine wave superposition `sin(i * 0.8f) * amplitude` to create organic "liquid" motion.
* **Performance:** Optimized drawing loop ensures 60 FPS rendering even during complex animations.

### 2. The "SVO" Drill Logic
The app leverages the syntactic similarity between English, Spanish, Portuguese, and French (Subject-Verb-Object).
* **Swap Drills:** Rapidly changing one element (e.g., "I eat bread" -> "I eat *apples*").
* **Ladder Drills:** Progressively building sentence complexity.



## 🛠️ Tech Stack
* **Language:** Kotlin
* **Framework:** Android SDK, Jetpack Compose
* **State Management:** `remember`, `mutableStateOf`, `LaunchedEffect`
* **Tools:** Android Studio, Gradle

## 📄 Documentation
* **[Read the Design Prototype (PDF)](reports/Building_Nativox_Prototype.pdf)** - Deep dive into the "Pixel Grid" gamification and linguistic strategy.

## 📂 Repository Structure
* `app/src/main/java/com/example/nativox/ui/`:
    * `PracticeScreen.kt`: The core game loop and visualization logic.
    * `HomeScreen.kt`: Dashboard with "Ad Space" placeholder logic.
    * `LibraryScreen.kt`: Vocabulary management system.

## 👤 Author
**Brandyn Ewanek**
* [LinkedIn](https://www.linkedin.com/in/brandyn-ewanek/)
* [Portfolio](https://brandynewanek.github.io/).pdf)** - Deep dive into the "Pixel Grid" gamification and linguistic strategy.

## 📂 Repository Structure
* `app/src/main/java/com/example/nativox/ui/`:
    * `PracticeScreen.kt`: The core game loop and visualization logic.
    * `HomeScreen.kt`: Dashboard with "Ad Space" placeholder logic.
    * `LibraryScreen.kt`: Vocabulary management system.

## 👤 Author
**Brandyn Ewanek**
* [LinkedIn](https://www.linkedin.com/in/brandyn-ewanek-9733873b/)
* [Portfolio](https://github.com/Brandyn-Ewanek/)
