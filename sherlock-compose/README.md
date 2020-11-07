# Taking screenshots on Jetpcack Compose
[Sherlock Gradle Plugin](../sherlock-plugin) provides a gradle task that captures screenshots for every
`Composable` that was annotated with the `@Preview` annotation. This task needs to have input from 
the Sherlock's annotation processor. When you build your project, the annotation processor records all
the methods that were annotated with the `@Preview`. These records are used by the plugin to capture
screenshots for all the previews. All you have to do is to run: 
```shell script
./gradlew app-compose:recordPreviews
``` 

## Installation
To install screenshot recorder for Jetpack Compose-based apps, you should add the following dependencies:
```kotlin
// Jetpack Compose-Based UI apps
// Automatically capturing screenshots for every composable that was annotated with the @Preview
implementation("com.quadible:sherlock-compose:1.0.0")
kapt("com.quadible:sherlock-processor:1.0.0")
```

Additionally, you must declare the write to external storage permission. 
```xml
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```
This permission should be added either in your app's `AndroidManifest` or in your test app's `AndroidManifest`.

Finally, you need to apply the [Sherlock Gradle Plugin](../sherlock-plugin).

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

