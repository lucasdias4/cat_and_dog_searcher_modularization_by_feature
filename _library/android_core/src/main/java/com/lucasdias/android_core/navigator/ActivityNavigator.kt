package com.lucasdias.android_core.navigator

import android.app.Activity

interface ActivityNavigator {
    fun navigateToSearch(activity: Activity, flags: Int)

    fun navigateToBreedList(breedName: String, animalTypeName: String, activity: Activity)
}
