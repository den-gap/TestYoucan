package ru.dengap.testyoucan.ui

import android.util.Log
import com.airbnb.mvrx.MavericksState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.dengap.testyoucan.Lexical
import ru.dengap.testyoucan.LexicalCount
import ru.dengap.testyoucan.core.MvRxViewModel
import java.time.LocalDateTime

data class TimeState(
    val hh: Int = LocalDateTime.now().hour,
    val mm: Int = LocalDateTime.now().minute,
    val ss: Int = LocalDateTime.now().second
) : MavericksState

class TimeViewModel(state: TimeState) : MvRxViewModel<TimeState>(state) {
    init {
        Log.i("REFRESH_TIME", "first")
        viewModelScope.launch {
            while (true) {
                setState {
                    copy(
                        hh = LocalDateTime.now().hour,
                        mm = LocalDateTime.now().minute,
                        ss = LocalDateTime.now().second
                    )
                }
                Log.i("REFRESH_TIME", "Succeed circle")
                delay(1000)
            }
        }
    }

    fun curTimeText(state: TimeState): String {
        return "Текущее время: ${state.hh}:${state.mm}:${state.ss}"
    }

    val currentTimeText: String
        get() {
            var text = ""
            withState { state: TimeState ->
                text = "Текущее время: ${state.hh}:${state.mm}:${state.ss}"
            }
            Log.i("TOAST_TEXT", text)
            return text
        }

    fun hourToText(h: Int): String {
        return when (Lexical.lexiсalCount(h)) {
            LexicalCount.ONE -> "$h час"
            LexicalCount.SOME -> "$h часа"
            else -> "$h часов"
        }
    }

    fun minToText(m: Int): String {
        return when (Lexical.lexiсalCount(m)) {
            LexicalCount.ONE -> "$m минуты"
            LexicalCount.SOME -> "$m минуты"
            else -> "$m минут"
        }
    }

    fun secToText(s: Int): String {
        return when (Lexical.lexiсalCount(s)) {
            LexicalCount.ONE -> "$s секунда"
            LexicalCount.SOME -> "$s секунды"
            else -> "$s секунд"
        }
    }
}