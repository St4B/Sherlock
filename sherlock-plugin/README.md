# Sherlock Gradle Plugin

## Pulling screenshots 
To pull screenshots from a device/emulator you can just run the available gradle task that is provided
from the `Sherlock Gradle Plugin`. Specifically, you need to run: 
```shell script
./gradlew app:pullScreenshots
```

## Comparing screenshots
You should store a valid version of your screenshots in `/[module]/screenshots/` directory before starting
any comparison. Then, you can compare them with the pulled screenshots that are stored in the 
`/[module]/pulled-screenshots/` directory (sherlock identifies whether the screenshots refer to the 
same UI element based on their file name). If there are changes between the screenshot versions, then
an image that contains the differences will be created in `/[module]/pulled-screenshots/screenshot-testing-results/`
directory. To start a comparison you need to run the available gradle task 
```shell script
./gradlew app:compareScreenshots
```

## Available Gradle Tasks
Here we list that tasks that were mentioned earlier. Also we have included some more tasks to reduce
the steps that are needed to take in order to record, pull and compare screenshots

- **compareScreenshots:** Used to compare screenshots that already exist in `[module]/screenshots` and `[module]/pulled-screenshots/` folders.
- **pullScreenshots:** Used to pull screenshots from the device/emulator to the `[module]/pulled-screenshots/` folder.
- **pullAndCompare:** Used to pull screenshots from the device/emulator to the `[module]/pulled-screenshots/` folder and compare them with the screenshots that already exist in `[module]/screenshots`.
- **recordPreviews:** Used in order to only record all the `Composables` that were annotated with the `@Preview` annotation.
- **recordAndPull:** Used in order to record all the `Composables` that were annotated with the `@Preview` annotation and pull them from the device/emulator to the `[module]/pulled-screenshots/` folder.
- **recordAndCompare:** Used in order to record all the `Composables` that were annotated with the `@Preview` annotation, pull them from the device/emulator to the `[module]/pulled-screenshots/` folder and compare them with the screenshots that already exist in `[module]/screenshots`.

## Installation
To install the `Sherlock Gradle Plugin` that automates the process of recording (available only for 
composables), pulling and comparing screenshots, you should apply at the root build.gradle.kts the 
following: 
```kotlin
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath("com.quadible.sherlockplugin:sherlock-plugin:1.0")
    }
}
```
Then, in the app level build.gradle.kts you should apply the plugin and provide the applicationId:
```kotlin
plugins {
    id("com.quadible.sherlockplugin")
}

// Need to configure the applicationId
sherlock.applicationId = "com.package.app"
```

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

