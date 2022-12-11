package ru.karpenko.dzen.presentation.util

import android.content.Context
import android.content.res.Configuration

const val CARDS_ON_LANDSCAPE_ORIENTATION = 5
const val CARDS_ON_PORTRAIT_ORIENTATION = 3

fun Context.isLandscapeOrientation() =
    Configuration.ORIENTATION_LANDSCAPE == resources.configuration.orientation

