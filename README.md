# Sherlock ![alt text](http://3.bp.blogspot.com/_lUAe0fBqoG4/TA-Fau6_KiI/AAAAAAAAAD0/ufl5dxU4A_A/s1600/brunocb-sherlock-holmes-tux-5975.png)
Sherlock is a simple screenshot testing library. It was created with Android world in mind, but its
script can be used in other projects too. Internally, it uses [FFmpeg](https://www.ffmpeg.org/) to 
compare screenshots and produce reports. 

## Prerequisite
This project uses FFmpeg in order to handle screenshots. The script  installs the library by itself,
but it assumes that we run it on Ubuntu or MacOS. In other OS you may need to handle ffmpeg's 
installation manually.

Another thing that we must keep in mind is that we must declare the write to external storage 
permission. 
```xml
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```
This permission should be added either in our app's AndroidManifest or in our test app's AndroidManifest.

## Taking screenshots
We can start recording screenshots through implementations of `Recorder`. We decided that there is
no need to be strict on the way of recording screenshots. There are many libraries that can do it
pretty well and we are more flexible to use the existing infrastructure of our project. In addition, 
we avoid adding an extra library and to recording screenshots again (For example we may have already
taken screenshots with [Firebase](https://firebase.google.com/docs/test-lab/android/test-screenshots) or [fastlane](https://docs.fastlane.tools/getting-started/android/screenshots/) 
which could be used in order to compare them with their newer versions.) 

Although we are not strict on the way of recording, we have provided a simple implementation of this
interface, the `ViewHierarchyRecorder`. To record a screenshot of the entire activity we must call:
```kotlin
ViewHierarchyRecorder().record(screenshotName = "main_activity", activity = it)
``` 
To record a screenshot of a specific view we must call:
```kotlin
ViewHierarchyRecorder().record(
    screenshotName = "main_activity_fab",
    activity = it,
    viewId = R.id.fab
)
``` 

## Compare
We should store a valid version of our screenshots in `/app/screenshots/` directory. 

When we execute the following script:
```shell script
./compare.sh
```
It will install FFmpeg, pull screenshots from device, and generate the results. The pulled 
screenshots will be stored in the `/pulled-screenshots/` directory. We are going to compare them
with the screenshots that exist in the `/app/screenshots/` directory (we identify whether the 
screenshots refer to the same UI element based on their file name). If there are changes between the 
screenshot versions, then an image that contains the differences will be created in 
`/pulled-screenshots/screenshot-testing-results/` directory.

## Sample app 
To test this library we can use the sample project. All we need to do is to run 
```shell script
adb shell am instrument -w -r    -e debug false -e class 'com.theblueground.app.MainActivityTest' com.theblueground.app.test/androidx.test.runner.AndroidJUnitRunner
```
This command will run the tests and the screenshots will be generated. We avoided using 
`./gradlew connectedAndroidTest` because it would erase our screenshots.

Then, we must run the compare script 
```shell script
./compare.sh
```
We have provided some versions of screenshot that are going to fail in order to present the generated
images that contain info about the changes between the screenshots.

## Roadmap
1. Enhance `ViewHierarchyRecorder` and publish it
2. Convert it to Gradle Plugin
3. Define configurable paths
4. Different `/pulled-screenshots/` && `/pulled-screenshots/screenshot-testing-results/` per device

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

