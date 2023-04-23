package it.revolution.mygarage.domain.services

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

private var nowDate: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

@OptIn(DelicateCoroutinesApi::class)
internal fun startTimer() {
    /**
     * TODO USE TEST ONLY!
     */
    GlobalScope.launch {
        while (true) {
            delay(1000)
            nowDate = nowDate.plus(1, DateTimeUnit.DAY)
        }
    }
}

internal fun nowDate(): LocalDate = nowDate