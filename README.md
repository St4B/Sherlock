# Sherlock ![alt text](http://3.bp.blogspot.com/_lUAe0fBqoG4/TA-Fau6_KiI/AAAAAAAAAD0/ufl5dxU4A_A/s1600/brunocb-sherlock-holmes-tux-5975.png)
Sherlock is a simple screenshot testing library. Internally, it uses [FFmpeg](https://www.ffmpeg.org/) to 
compare screenshots and produce reports. 

## Prerequisite
This project uses [FFmpeg](https://ffmpeg.org/) in order to handle screenshots. The 
[Sherlock Gradle Plugin](./sherlock-plugin) installs the library by itself, but it assumes that we 
run it on `Ubuntu` or `MacOS`. In other OS you may need to handle FFmpeg's installation manually.

## How to use it
- [Taking screenshots on View-based UI](./sherlock)
- [View-based sample app](./app) 
- [Taking screenshots on Jetpcack Compose](./sherlock-compose)
- [Jetpack Compose-based sample app](./app-compose) 
- [Comparing screenshots](./sherlock-plugin)

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

