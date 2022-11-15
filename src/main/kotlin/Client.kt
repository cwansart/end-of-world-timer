import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.*
import kotlinx.html.dom.append
import org.w3c.dom.Node
import kotlin.js.Date
import kotlin.math.roundToInt

const val ONE_SECOND = 1_000

const val MILLISECONDS_TO_SECONDS = 1_000
const val NO_DIVISION = 1
const val SECONDS_TO_MINUTES = 60
const val SECONDS_TO_HOURS = 3_600
const val SECONDS_TO_DAYS = 86_400
const val SECONDS_TO_WEEKS = 604_800

const val END_OF_TIME = "Fri, 23 Dec 2022 16:00:00 GMT+1"

val endOfTime = Date(END_OF_TIME)
val endOfTimeSeconds = Date.parse(END_OF_TIME)

val secondsDivider = mutableMapOf(
    "seconds" to NO_DIVISION,
    "minutes" to SECONDS_TO_MINUTES,
    "hours" to SECONDS_TO_HOURS,
    "days" to SECONDS_TO_DAYS,
    "weeks" to SECONDS_TO_WEEKS
)

fun main() {
    window.onload = {
        document.querySelector("#container")?.addContents()
        setRemaining()

        window.setInterval({
            setRemaining()
        }, ONE_SECOND)
    }
}

fun setRemaining() {
    val now = Date.now()
    val remainingSeconds = ((endOfTimeSeconds - now) / MILLISECONDS_TO_SECONDS).roundToInt()

    secondsDivider.forEach { (unit, divider) ->
        val remainingTime = if (remainingSeconds > 0) remainingSeconds / divider else 0
        document.querySelector("#$unit")?.textContent = remainingTime.toLocaleString()
    }
}

fun Int.toLocaleString(): String {
    @Suppress("UNUSED_VARIABLE")
    val data = this
    return js("data.toLocaleString()") as String
}

fun Node.addContents() {
    append {
        h1 {
            +"When will be the end of time?"
        }
        div {
            +"The end of time is on ${endOfTime.toDateString()} at ${endOfTime.toTimeString()}"
        }
    }

    secondsDivider.keys.forEach {
        append {
            div {
                classes = setOf("line")
                +"${it.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }} remaining: "
                span {
                    id = it
                }
            }
        }
    }
}