# CarsTradeApp

### How it's built

* Technologies used
    * [Kotlin](https://kotlinlang.org/)
    * [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
    * [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
    * [Retrofit](https://square.github.io/retrofit/)
    * [Jetpack](https://developer.android.com/jetpack)
        * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
        * [Paging3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)

* Architecture
    * Clean Modular Architecture
    * MVVM - Model View View Model

* Gradle
    * Gradle Kotlin DSL


* CI/CD
    * Github Actions

### Screenshots

I added some screenshots in the `screenshots` folder, in the root directory of the project.

Explore Page 1 | Explore Page 2 --- | --- | ---
<img src="https://github.com/sammy-mutahi/CarsTradeApp/blob/master/screenshots/image1.png" width="280"/>
| <img src="https://github.com/sammy-mutahi/CarsTradeApp/blob/master/screenshots/image2.png" width="280"/>

### Undone parts (Due to time constraint)

* Caching capability on search items(To reduce the number of network calls that have to be made)
* Details Screen
* Firebase Firestore DB (Save Favorites)
* Huawei Support Version of Firebase Firestore Support
* Adding Items to Cart and Making a Purchase
* Unit Tests(70%) and Instrumented Tests(20%)
* Appropriate use of CI/CD to automate our tests
* Code Coverage
* Use Detekt to analyse kotlin Code
