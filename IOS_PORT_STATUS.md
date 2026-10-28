# MAX STUDIO iOS port — status

## Current status

This is an **in-progress full port**, not yet an installable iOS app. The first platform-neutral Kotlin modules have been converted to Kotlin Multiplatform while retaining the Android/JVM variants:

- `:domain` now targets Android, JVM, iOS arm64, and iOS simulator arm64; its existing model, repository-contract, use-case sources, and JVM tests remain in place. It exports a static `MaxStudioDomain` framework for Apple targets.
- `:metadata` now targets Android, JVM, iOS arm64, and iOS simulator arm64; its existing cleaner/model sources and JVM tests remain in place. It exports a static `MaxStudioMetadata` framework for Apple targets.

The migrations use AGP 9's `com.android.kotlin.multiplatform.library` plugin. This is a foundation only; these frameworks do not yet implement the full MAX STUDIO app.

## Verification completed

- `:domain:jvmTest` — passed.
- `:metadata:jvmTest` — passed.
- `:app:compileUniversalFossDebugKotlin` — passed after both modules were migrated, confirming Android can still consume their Android variants.
- Gradle exposes iOS device and simulator framework-link tasks for the migrated targets. The Apple framework link itself has **not** been run here.

## Reference architecture

The supplied Spotube project uses Kotlin Multiplatform/Compose Multiplatform for shared code and a thin SwiftUI host that embeds the Kotlin framework. Its source is licensed AGPL-3.0-or-later, while MAX STUDIO is GPL-3. Spotube was inspected only as an architectural reference; no Spotube source was copied into MAX STUDIO.

## Remaining full-port work

1. Separate portable Innertube request/response models and provider/network logic from Android/JVM extractors; provide an iOS-compatible Ktor Darwin client and stream-resolution path.
2. Port provider integrations and lyrics/search/discovery logic incrementally, preserving Android implementations.
3. Replace or abstract Android Room/DataStore persistence for iOS, with schema and upgrade tests so existing Android data behavior is not regressed.
4. Define a platform-neutral playback contract and build the iOS AVFoundation/audio-session/background-playback implementation alongside the Android Media3 implementation.
5. Port the app shell and features, replace Hilt composition on iOS, and document features without a direct Apple equivalent (for example GMS Cast and USB audio).
6. Add macOS/Xcode framework, simulator, signing, and device acceptance checks before calling the port complete.

## Build limitation

The current execution environment is Linux and has no Xcode or Apple SDK. It can validate JVM/Android compilation and source configuration, but it cannot link and test an iOS app, sign an `.ipa`, or run it on an iOS simulator. Those steps require macOS with Xcode and Apple signing/provisioning set up.
