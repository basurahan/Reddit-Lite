package link.limecode.reddit.lite.util

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun getCurrentDateTime(): String {
    return Clock.System.now().toLocalDateTime(TimeZone.UTC).toString()
}