# Android Boilerplate

Sample Android app that we use as a reference for new Android projects. It demonstrates the architecture, tools and guidelines that we use when developing for the Android platform:

Libraries and tools included:

- Support Library
- Recycleview & Cardview
- material design support
- palette support
- Retrofit2
- Butterknife

# Requirements
- Android SDK.
- Android 6.0 (API 23) .
- Latest Android SDK Tools and build tools.

# Architecture
This project follows Android architecture guidelines that are based on MVP (Model View Presenter). Read more about them [here].(http://hannesdorfmann.com/mosby/mvp/)

# Project structure
```
new-structure
├─ library-foobar
├─ app
│  ├─ libs
│  ├─ src
│  │  ├─ androidTest
│  │  │  └─ java
│  │  │     └─ com/abderrazak/recycleviewcardview
      ├─ test
│  │  │  └─ java
│  │  │     └─ com/abderrazak/recycleviewcardview
│  │  └─ main
│  │     ├─ java
│  │     │  └─ com/abderrazak/recycleviewcardview
│  │     ├─ res
│  │     └─ AndroidManifest.xml
│  ├─ build.gradle
│  └─ proguard-rules.pro
├─ build.gradle
└─ settings.gradle
```
# Java packages architecture

```
abderrazak.com.recycleviewcardview
├─ network
├─ models
├─ managers
├─ utils
├─ fragments
└─ views
   ├─ adapters
   ├─ actionbar
   ├─ widgets
   └─ notifications
```
# How to implement a new screen following MVP
