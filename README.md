# Android Boilerplate

Sample Android app that we use as a reference for new Android projects. It demonstrates the architecture, tools and guidelines that we use when developing for the Android platform:

Libraries and tools included:

- Support Library
- Recycleview & Cardview
- material design support
- palette support
- Retrofit2
- Picasso
- Butterknife

## Requirements
- Android SDK.
- Android 6.0 (API 23) .
- Latest Android SDK Tools and build tools.

## Architecture
This project follows Android architecture guidelines that are based on MVP (Model View Presenter). Read more about them [here](http://hannesdorfmann.com/mosby/mvp/).

## Project structure
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
## Java packages architecture

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
### How to implement a new screen following MVP
Imagine you have to implement a main screen.

1. Create a new package under ui called main.

2. Create an new Activity called MainActivity. You could also use a Fragment.

3. Define the view interface that your Activity is going to implement. Create a new interface called MainView. Add the methods that you think will be necessary, e.g. showDialog()

4. Create a MainPresenterImpl class that implement MainPresenter.Implement the methods in MainPresenter that your Activity requires to perform the necessary actions, e.g. signIn(String email).

5. Create a SignInPresenterTestand write unit tests for signIn(email). Remember to mock the SignInMvpView and also the DataManager.

6. Make your MainActivity implement SignInMvpView and implement the required methods like showDialog()
In your activity, inject a new instance of MAinPresenter and call presenter = new MainPresenterImpl(this) from onCreate and presenter.onDestroy() from onDestroy(). Also, set up a click listener in your button that calls presenter.sortList().

### Some Best practices

- Use Gradle and its recommended project structure

- Put passwords and sensitive data in gradle.properties

- Use Retrofit for fetch data from the internet

- Use (Picasso || Glide) for thumbnails, I suggest you to use glide instead of Picasso

- Don't write your own HTTP client, use Volley or OkHttp libraries

- Avoid Guava and use only a few libraries due to the 65k method limit

- Use Fragments to represent a UI screen

- Use Activities just to manage Fragments

- Layout XMLs are code, organize them well

- Use styles to avoid duplicate attributes in layout XMLs

- Use multiple style files to avoid a single huge one

- Keep your colors.xml short and DRY, just define the palette

- Also keep dimens.xml DRY, define generic constants

- Do not make a deep hierarchy of ViewGroups

- Avoid client-side processing for WebViews, and beware of leaks

- Use Robolectric for unit tests, Robotium or Espresso for connected (UI) tests

- Use Genymotion as your emulator || Vysor lets you view and control your Android on your computer Easy peasy. 

- Always use ProGuard or DexGuard

- Use SharedPreferences for simple persistence, otherwise ContentProviders

