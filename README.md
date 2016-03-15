# Android Boilerplate

Sample Android app that I use as a reference for my new Android projects. It demonstrates the architecture, tools and guidelines that I use when developing for the Android platform:

Libraries and tools included:

- Support Library
- Recycleview & Cardview
- Material design support
- Palette support
- [Support annotations](http://tools.android.com/tech-docs/support-annotations)
- [Retrolambda](https://github.com/evant/gradle-retrolambda)
- [Retrofit2](http://square.github.io/retrofit/)
- [Glide](https://github.com/bumptech/glide)
- [ButterKnife](http://jakewharton.github.io/butterknife/)
- [Otto](http://square.github.io/otto/)

## Requirements
- Android SDK.
- Android 6.0 (API 23) .
- Latest Android SDK Tools and build tools.

## Architecture
This project follows Android architecture guidelines that are based on MVP (Model View Presenter). Read more about them [here](http://www.tinmegali.com/en/model-view-presenter-android-part-1/).

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
├─ data
    ├─ local
    ├─ model
    ├─ remote
├─ network
    ├─ callbacks
    ├─ services
    ├─ tasks
├─ managers
├─ ui
│   ├─ main
    ├─ detail
    ├─ etc..
├─ util
└─ views
   ├─ adapters
   ├─ widgets
   └─ callbacks
```
### How to implement a new screen following MVP

Imagine you have to implement a main screen.

1. Create a new package under ```ui``` called main.

2. Create an new Activity called ```MainActivity```. You could also use a Fragment.

3. Define the view interface that your Activity is going to implement. Create a new interface called MainView. Add the methods that you think will be necessary, ```e.g. showDialog()```

4. Create a MainPresenterImpl class that implement MainPresenter.Implement the methods in MainPresenter that your Activity requires to perform the necessary actions, ```e.g. setItems(List<Movie> movieItems)```.

5. Create a MainPresenterTest and write unit tests for ```e.g. setItems(List<Movie> movieItems)```. Remember to mock the MainView.

6. Make your MainActivity implement SignInMvpView and implement the required methods like showDialog()
In your activity, inject a new instance of MAinPresenter and call ``` presenter = new MainPresenterImpl(this) ``` from onCreate and ``` presenter.onDestroy() ``` from onDestroy(). Also, set up a click listener in your button that calls presenter.sortList().

### Things that experience made me learn the hard way
```
 1. Think twice before adding any third party library, it’s a really serious commitment

 2. Don’t use a database unless you really need to

 3. Hitting the 65k method count mark is gonna happen fast, I mean really fast! And multidexing can save you

 4. RxJava is the best alternative to AsyncTasks and so much more

 5. Retrofit is the best networking library there is

 6. Shorten your code with Retrolambda

 7. Combine RxJava with Retrofit and Retrolambda for maximum awesomeness!

 8. I use EventBus and it’s great, but I don’t use it too much because the codebase would get really messy

 9. Package by Feature, not layers

 10. Move everything off the application thread

 11. lint your views to help you optimize the layouts and layout hierarchies so you can identify redundant views that  could perhaps be removed
      
 12. Use Gradle and its recommended project structure

 13. Put passwords and sensitive data in gradle.properties
      
 14. Don't write your own HTTP client, use Volley or OkHttp libraries
      
 15. Use styles to avoid duplicate attributes in layout XMLs
      
 16. Do not make a deep hierarchy of ViewGroups
      
 17. Don't write your own HTTP client, use Volley or OkHttp libraries
      
 18. Monitor power source and battery (more data updates while charging? Suspend updates when battery is low?)
      
 19. Monitor connectivity and type of connection (more data updates while on wifi?)
      
 20. Use the [Account Manager](http://developer.android.com/reference/android/accounts/AccountManager.html) to suggest login usernames and email addresses
      
 21. Tests are great for performance: Write slow (but correct) implementation then verify optimizations don’t break anything with tests.

```
### TODO
