package ru.dengap.testyoucan.core

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksState

abstract class MvRxViewModel<S : MavericksState>(initialState: S) : MavericksViewModel<S>(initialState)
