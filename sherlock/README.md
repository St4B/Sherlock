# Taking screenshots on View-based UI
You can start recording screenshots through implementations of `Recorder`. We decided that there is
no need to be strict on the way of recording screenshots. There are many libraries that can do it
pretty well and you will be more flexible to use the existing infrastructure of your project. In addition, 
you avoid adding an extra library and recording screenshots again (For example you may have already
taken screenshots with [Firebase](https://firebase.google.com/docs/test-lab/android/test-screenshots) 
or [fastlane](https://docs.fastlane.tools/getting-started/android/screenshots/) which could be used 
in order to compare them with their newer versions.) 

Although we are not strict on the way of recording, we have provided a simple implementation of this
interface, the `ViewHierarchyRecorder`. To record a screenshot of the entire activity you must call:
```kotlin
ViewHierarchyRecorder().record(screenshotName = "main_activity", activity = it)
``` 
To record a screenshot of a specific view you must call:
```kotlin
ViewHierarchyRecorder().record(
    screenshotName = "main_activity_fab",
    activity = it,
    viewId = R.id.fab
)
``` 
Obviously, these invocations should happen in an AndroiTest. You will need to run these tests to 
capture the screenshots. The screenshots will be placed in the device/emulator so you will need to 
pull them in order to compare them with a previous version.

## Installation
To install screenshot recorder for View-Based UI apps, you should add the following dependency:
```kotlin
// Provides a simple recorder for View-Based UI apps - Optional as you can use your own recorder
implementation("com.quadible:sherlock:1.0.0")
```

Additionally, you must declare the write to external storage permission. 
```xml
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```
This permission should be added either in your app's `AndroidManifest` or in your test app's `AndroidManifest`.

## Comparison
To compare screenshots you need to use [Sherlock Gradle Plugin](../sherlock-plugin).

## License
```
 Copyright 2020 Quadible Ltd.
 
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
   http://www.apache.org/licenses/LICENSE-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
```

