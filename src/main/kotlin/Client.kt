import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.*
import kotlinx.html.dom.append
import org.w3c.dom.Node
import kotlin.js.Date
import kotlin.math.roundToInt

const val ONE_SECOND = 1_000
const val END_OF_TIME = "Fri, 23 Dec 2022 16:00:00 GMT+1"

val endOfTime = Date(END_OF_TIME)
val endOfTimeSeconds = Date.parse(END_OF_TIME)

val secondsDivider = mutableMapOf(
    "seconds" to 1,
    "minutes" to 60,
    "hours" to 3600,
    "days" to 86_400,
    "weeks" to 604_800
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
    val remainingSeconds = ((endOfTimeSeconds - now) / 1000).roundToInt()

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