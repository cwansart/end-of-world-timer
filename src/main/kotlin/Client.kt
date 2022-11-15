import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.div
import kotlinx.html.dom.append
import kotlinx.html.h1
import kotlinx.html.id
import kotlinx.html.span
import org.w3c.dom.Node
import kotlin.js.Date
import kotlin.math.roundToInt

val endOfTime = Date("Fri, 23 Dec 2022 16:00:00 GMT+1")
val endOfTimeSeconds = Date.parse("Fri, 23 Dec 2022 16:00:00 GMT+1")

const val ONE_SECOND = 1_000

val remaining = mutableMapOf(
    "seconds" to 0,
    "minutes" to 0,
    "days" to 0,
    "hours" to 0,
    "weeks" to 0
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
    val diff = endOfTimeSeconds - now
    remaining["seconds"] = if (diff > 0) (diff / 1000).roundToInt() else 0
    remaining["minutes"] = calculateRemainingTime(diff, "seconds", 60)
    remaining["hours"] = calculateRemainingTime(diff, "minutes", 60)
    remaining["days"] = calculateRemainingTime(diff, "hours", 24)
    remaining["weeks"] = calculateRemainingTime(diff, "hours", 168)

    remaining.keys.forEach {
        val span = document.querySelector("#$it")
        span?.textContent = remaining[it].toString()
    }
}

fun calculateRemainingTime(diff: Double, unit: String, divider: Int) =
    if (diff > 0 && remaining[unit] != null) remaining[unit]!! / divider else 0

fun Node.addContents() {
    append {
        h1 {
            +"When will be the end of time?"
        }
        div {
            +"The end of time is on ${endOfTime.toDateString()} at ${endOfTime.toTimeString()}"
        }
    }

    remaining.keys.forEach {
        append {
            div {
                +"${it.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }} remaining: "
                span {
                    id = it
                }
            }
        }
    }
}