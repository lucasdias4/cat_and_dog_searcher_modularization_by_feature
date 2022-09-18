# Cat and dog searcher

## Introduction
This application has as main objective to consult the list of cat and dog breeds.

### Use cases:
- Search for a list of cat breeds;
- Search for a list of dog breeds;
- See a list of cat breeds;
- See a list of dog breeds;
- See the detail of the selected breed.

### Tech stack:
- Kotlin;
- MVVM;
- Clean Architecture;
- Coroutines;
- Koin;
- MockK;
- Github Actions;
- etc.

### Screenshoots
<p align="center">
 <img src="/documentation/splash_day.jpg" width=20% height=20%>
 <img src="/documentation/splash_night.jpg" width=20% height=20%>
 <img src="/documentation/search_day.jpg" width=20% height=20%>
 <img src="/documentation/search_night.jpg" width=20% height=20%>
</p>
<p align="center">
 <img src="/documentation/breed_list_day.jpg" width=20% height=20%>
 <img src="/documentation/breed_list_night.jpg" width=20% height=20%>
 <img src="/documentation/breed_detail_day.jpg" width=20% height=20%>
 <img src="/documentation/breed_detail_night.jpg" width=20% height=20%>
</p>

## Architecture
The app was built using MVVM and Clean Architecture.

The modularization was done by layer. Each layer has its own modules (view, view model, domain, repository, data source, etc). The reason for this is to have restrictions, in this way a developer could not disrespect the proposed architecture. For example, an Use Case could never be called directly from an Activity.

Below is the representation of the main modules of the application:
<p align="center">
 <img src="/documentation/architecture.png" width=70% height=70%>
</p>

#### Note:
* presentation:common_model module is only used for models that need to be shared between features. Feature-specific models must live within the specific feature.

## Unit test
* [unit test 1](https://github.com/lucasdias4/cat_and_dog_searcher_modularization_by_feature/blob/master/_feature/breed/src/test/java/com/lucasdias/breed/presentation/BreedListViewModelTest.kt)
* [unit test 2](https://github.com/lucasdias4/cat_and_dog_searcher_modularization_by_feature/blob/master/_feature/breed/src/test/java/com/lucasdias/breed/domain/GetBreedByNameAndAnimalTypeUseCaseTest.kt)


Some unit tests from another project I made:
* [unit test 1](https://github.com/lucasdias4/marvel/blob/master/_presentation/profile/view_model/src/test/java/com/lucasdias/profile/view_model/ProfileViewModelTest.kt)
* [unit test 2](https://github.com/lucasdias4/marvel/blob/master/_domain/domain/src/test/java/com/lucasdias/domain/GetPreferencesVariableUseCaseTest.kt)
* [unit test 3]( https://github.com/lucasdias4/marvel/blob/master/_data/repository/src/test/java/com/lucasdias/repository/PreferencesRepositoryImplTest.kt)

## Continuous Integration
Every time some code is merged into the master or develop branches, the unit tests, ktlint and lint tasks are executed in order to validate the code's quality.

For this the app makes use of Github Actions. In this [link](https://github.com/lucasdias4/cat_and_dog_searcher_modularization_by_feature/actions) you can check the jobs that were executed.
