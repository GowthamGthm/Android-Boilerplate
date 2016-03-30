# Android Boilerplate


Sample Android app that I use as a reference for my new Android projects. It demonstrates the architecture, tools and guidelines that I use when developing for the Android platform:



##Third Party Libraries & tools included

- Support Library
- [Recycleview & Cardview](http://developer.android.com/training/material/lists-cards.html)
- [Material design support](http://android-developers.blogspot.com/2015/05/android-design-support-library.html)
- [Android Transition Framework](https://developer.android.com/training/transitions/overview.html)
- [Palette support](http://developer.android.com/reference/android/support/v7/graphics/Palette.html)
- [Support annotations](http://tools.android.com/tech-docs/support-annotations)
- [RxJava/ RxAndroid](https://github.com/ReactiveX/RxAndroid)
- [Retrolambda](https://github.com/evant/gradle-retrolambda)
- [Dagger 2](http://google.github.io/dagger/)
- [Retrofit2](http://square.github.io/retrofit/)
- [Glide](https://github.com/bumptech/glide)  or  [Picasso](http://square.github.io/picasso/)
- [ButterKnife](http://jakewharton.github.io/butterknife/)
- [Otto](http://square.github.io/otto/) event bus

## Requirements
- [Latest Android SDK Tools and build tools](http://developer.android.com/sdk/index.html).
- Android 6.0 (API 23) .

## Good Architecture

- Clean **Architecture** is :
  - Independent of UI
  - Independent of Frameworks
  - Independent of any agency externe 
  - Independent of Database
  - Testable

Read more [here](https://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html)

## Architecture

This project follows Android architecture guidelines that are based on **MVP** (Model View Presenter). Read more about them [here](http://www.tinmegali.com/en/model-view-presenter-android-part-1/).

<p align="center">
    <img src="https://github.com/laanayabdrzak/Android-Boilerplate/blob/master/boilerplate_arch.png" alt="Screenshots"/>
</p>

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
│  │  ├─ test
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
├─ data
│   ├─ local
│   ├─ model
│   └─ remote
├─ injection
│   ├─ component
│   └─ module
├─ ui
│   ├─ main
│   ├─ detail
│   └─ etc..
├─ util
└─ views
    ├─ adapters
    └─ widgets
```

####Gains and Benefits

By looking at the structure you can already tell what the app is all about (figure above);
- Higher modularity;
- Easier code navigation;
- Higher level of abstraction;
- Separates both features and layers;
- More readable and maintainable structure;
- More cohesion;
- Much easier to scale;
- Less chance to accidentally modify unrelated classes or files;
- Much easier to add or remove application features;
- And much more reusable modules.


## Project & code guidelines 

check [this](https://github.com/laanayabdrzak/android-guidelines/blob/master/project_and_code_guidelines.md)


## How to implement a new screen following MVP

Imagine you have to implement a **main** screen.

1. Create a new package under ```ui``` called **main**.

2. Create an new Activity called ```MainActivity```. You could also use a Fragment.

3. Define the view interface that your Activity is going to implement. Create a new interface called MainView. Add the methods that you think will be necessary, ```e.g. showDialog()```

4. Create a MainPresenterImpl class that implement MainPresenter.Implement the methods in MainPresenter that your Activity requires to perform the necessary actions, ```e.g. setItems(List<Movie> movieItems)```.

5. Create a MainPresenterTest and write unit tests for ```e.g. setItems(List<Movie> movieItems)```. Remember to mock the MainView.

6. Make your MainActivity implement SignInMvpView and implement the required methods like showDialog()
In your activity, inject a new instance of MAinPresenter and call ``` presenter = new MainPresenterImpl(this) ``` from onCreate and ``` presenter.onDestroy() ``` from onDestroy(). Also, set up a click listener in your button that calls presenter.sortList().

## Some of best practices

Check my post [here](https://medium.com/@laanayabdrzak/android-development-some-of-the-best-practices-27722c685b6a#.ctm7ophba)
 

## TODO #1

Push Project comming soon

## TODO #2
Include those libraries :

- [recyclerview-animators](https://github.com/wasabeef/recyclerview-animators)
- [FAB menu](https://github.com/yavski/fab-speed-dial)
- [custom snakbar](https://github.com/MrEngineer13/SnackBar)
- [FAB toolbar](https://github.com/bowyer-app/fab-toolbar)
- [FAB transition](https://github.com/bowyer-app/FabTransitionLayout)
- [Material spinner](https://github.com/jaredrummler/Material-Spinner)
- [CircularImageView](https://github.com/lopspower/CircularImageView)
- [Drag Recycle](https://github.com/AleBarreto/DragRecyclerView)
- [Parallax Recycleview](https://github.com/yayaa/ParallaxRecyclerView)
- [FAB Progress](https://github.com/JorgeCastilloPrz/FABProgressCircle)
- [Material Preference](https://github.com/consp1racy/android-support-preference)
- [Retainable Tasks](https://github.com/NeoTech-Software/Android-Retainable-Tasks)
- [material intro](https://github.com/HeinrichReimer/material-intro)
- [ MaterialShowcaseView](https://github.com/deano2390/MaterialShowcaseView)
- [Boot Animation](https://github.com/Cleveroad/CRAndroidMBootAnimationView)
- [Sharing using NFC](http://developer.android.com/training/beam-files/index.html)
- Functional tests with **Espresso**
- **Robolectric**
- **Mockito**
- **Checkstyle**, **PMD** and **Findbugs** for code analysis

## Developed By
 Author: **Abderrazak LAANAYA**

<a href="https://www.linkedin.com/in/laanayabdrzak">
  <img alt="Follow me on LinkedIn"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/linkedin.png" />
</a>

## Contributions
 Any contributions are welcome! :smile:




