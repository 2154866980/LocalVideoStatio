# LocalVideoStation

LocalVideoStation is an Android app project focused on local video playback, video management, author/tag systems, scoring, favorites, watch history, and recommendation logic.

## Project vision

This app is designed for users who want a Bilibili-inspired local video experience without relying on online streaming. It aims to keep content local, organized, and personalized.

## Core features

- Local video playback on Android
- Import and scan local video folders
- Video list, author management, tag management
- Ratings and recommendation logic
- Favorites and recent watch history
- Bilibili-like dark content platform layout

## Recommended stack

- Kotlin
- Jetpack Compose
- Media3 / ExoPlayer
- Room database
- MVVM architecture
- MediaStore / SAF for local video access

## Android project structure

```text
LocalVideoStation
├── app
│   ├── src/main/java/com/localvideostation/app
│   ├── src/main/res
│   └── build.gradle.kts
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
└── README.md
```

## Open in Android Studio

1. Open the repository in Android Studio.
2. Let Gradle sync the project.
3. Select an Android emulator or a physical device.
4. Run the app configuration.

## Development notes

This repository currently contains the initial Android app scaffold for the local video platform concept. Features will be expanded iteratively to include:

- file import and library scanning
- metadata extraction
- scoring and recommendation weighting
- local database persistence
- player resume state
- content discovery screens

## License

This project currently does not include a license. Add a license before public distribution.
