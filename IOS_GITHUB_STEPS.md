# GitHub par MAX STUDIO iOS build chalane ke steps

## Abhi kya build hoga

Abhi iOS app/UI aur playback port complete nahi hai. Isliye neeche diya workflow **sirf shared Kotlin frameworks** ko iOS device aur simulator ke liye compile karega. Yeh `.ipa` nahi banata aur iPhone par install nahi hoga. Isse pehle check hoga ki GitHub ka macOS runner MAX STUDIO ke shared KMP modules ko build kar pa raha hai.

## Files ko GitHub repo mein daalo

Apne MAX STUDIO repository mein ye files add ya replace karo:

- `.github/workflows/ios-frameworks.yml` — naya macOS build workflow.
- `domain/build.gradle.kts` — KMP/iOS target configuration.
- `metadata/build.gradle.kts` — KMP/iOS target configuration.

`IOS_PORT_STATUS.md` aur `IOS_GITHUB_STEPS.md` documentation hain; app build ke liye mandatory nahi.

Agar local computer par Git use karte ho, repo clone karke files sahi folders mein copy karo, phir:

```bash
git add .github/workflows/ios-frameworks.yml domain/build.gradle.kts metadata/build.gradle.kts IOS_PORT_STATUS.md IOS_GITHUB_STEPS.md
git commit -m "Add initial iOS KMP framework build"
git push origin main
```

Agar tumhari default branch `main` nahi hai, apni branch par push karo. Workflow `push` aur manual run dono par chalta hai.

## GitHub Actions run karo

1. GitHub par MAX STUDIO repository kholo.
2. **Actions** tab kholo, phir **iOS Shared Frameworks** select karo.
3. **Run workflow** dabao; ya files push hone par automatic run ka wait karo.
4. Run successful ho to us run ke **Artifacts** se `max-studio-ios-shared-frameworks` download karo. Is workflow ke liye Apple certificate, password, ya GitHub signing secret nahi chahiye.

## Actual iPhone-installable IPA ke liye baad mein

Pehle iOS app target, app screens, music search/stream resolution, storage, aur AVFoundation playback port karna baaki hai. Jab Xcode app target ready hoga, tab macOS workflow mein archive/export steps add honge. TestFlight ya registered devices par distribution ke liye Apple Developer Program, App ID/bundle identifier, Apple signing certificate aur provisioning profile chahiye. Apple ke mutabik Xcode archive banakar usse distribution ke liye export karta hai; TestFlight/registered-device distribution ke liye Developer Program membership chahiye. [Apple Xcode distribution guide](https://developer.apple.com/documentation/xcode/distributing-your-app-for-beta-testing-and-releases) · [Registered-device guide](https://developer.apple.com/documentation/xcode/distributing-your-app-to-registered-devices)

Signing files ya passwords GitHub repo mein commit mat karna. IPA workflow banate waqt certificate/profile ko GitHub Actions ke encrypted Secrets mein rakhna hoga. Abhi un signing secrets ki zarurat nahi hai, kyunki IPA ke liye Xcode app target hi abhi tayyar nahi.
