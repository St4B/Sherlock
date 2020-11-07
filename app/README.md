# Sample app with View-based UI
To demonstrate this library on View-based UI apps we created a sample app. To record screenshots of 
this app you need to run:
```shell script
adb shell am instrument -w -r    -e debug false -e class 'com.quadible.app.MainActivityTest' com.quadible.app.test/androidx.test.runner.AndroidJUnitRunner
```
This command will run the tests and the screenshots will be generated. You should avoid using 
`./gradlew connectedAndroidTest` because it would erase our screenshots.

Then, you must run the pull and compare task to check if there are any issues. 
```shell script
./gradlew app:pullAndCompare
```
We have provided some versions of screenshot that are going to fail in order to present the generated
images that contain info about the changes between the screenshots.

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
