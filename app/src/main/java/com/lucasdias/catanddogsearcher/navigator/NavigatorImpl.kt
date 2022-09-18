package com.lucasdias.catanddogsearcher.navigator

import com.lucasdias.android_core.navigator.ActivityNavigator
import com.lucasdias.android_core.navigator.Navigator

class NavigatorImpl(
    activityNavigator: ActivityNavigator
) : Navigator,
    ActivityNavigator by activityNavigator
