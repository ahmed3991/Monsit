# 🎙️ Monsit

**Monsit** is a powerful AI-driven Android application designed to streamline call management by providing automated recording summarization. It helps users capture the essence of every conversation without having to listen back to hours of audio.

[![Trello](https://img.shields.io/badge/Trello-Project_Management-blue?logo=trello)](https://trello.com/invite/b/69d754261dc12acadbe3ac3d/ATTI2d25726a8322c8f796ec278cf3ecd851E1C377D2/monsit)

---

## ✨ Features

- **🧠 Intelligent Summarization**: Automatically generate concise summaries of your call recordings.
- **📜 Full Transcripts**: Convert call audio into readable text for quick reference.
- **🔍 Topic Search**: Find specific discussions or keywords across all your recorded calls.
- **👥 Contact Management**: Organize calls by contact and view historical communication at a glance.
- **🎵 Integrated Audio Player**: Listen to specific segments of recordings while following the transcript.

---

## 🎨 Design Preview

<p align="center">
  <img src="ui/call_sammuraze%20ui.png" alt="Monsit UI Design" width="300">
</p>

---

## 📱 Screen-by-Screen Breakdown

1.  **Dashboard (Main)**: A clean overview of your latest call recordings, providing quick access to recent conversations.
2.  **Call Details**: The heart of the app. Includes contact info, duration, timestamps, a high-level summary, the full transcript, and a built-in audio player.
3.  **Contacts List**: A searchable directory of all contacts associated with recorded calls.
4.  **Contact Profile**: A dedicated view for each person showing their contact details and a complete history of calls exchanged with them.
5.  **Smart Search**: Advanced search capabilities to find calls based on topics, keywords, or specific dates.
6.  **Add Call Entry**: Manually add or log call recordings by providing the contact name, audio source, duration, and time.

---

## 🛠️ Tech Stack

- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose) for a modern, declarative UI.
- **Dependency Injection**: [Dagger Hilt](https://dagger.dev/hilt/) for clean and scalable architecture.
- **Architecture**: Clean Architecture (Domain/Data/Presentation) following MVVM patterns.
- **UI Design**: Material 3 Design System for a premium Look & Feel.

---

## 🚀 Getting Started

### Prerequisites
- Android Studio Ladybug or newer.
- Android SDK 24+ (Android 7.0 Nougat).

### Installation
1.  Clone the repository:
    ```bash
    git clone https://github.com/ahmed3991/Monsit.git
    ```
2.  Open the project in **Android Studio**.
3.  Sync Gradle and run the app on an emulator or physical device.

---

## 🏗️ Project Structure
```text
com.eloueduniv.monsit
├── data         # Repositories and local/remote data sources
├── domain       # Use cases and domain models
├── presentation # UI Screens, ViewModels, and Navigation
│   ├── main     # Dashboard logic
│   ├── search   # Search functionality
│   └── ...      # Other feature modules
└── ui           # Theme and styling definitions
```

---

## 🤝 Contributing
Contributions are welcome! Please feel free to submit a Pull Request or open an issue for any bugs or feature requests.

---

## ⚖️ License
This project is licensed under the MIT License - see the LICENSE file for details.
