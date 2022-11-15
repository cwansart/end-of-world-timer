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

fun main() {
    window.onload = {
        document.body?.addContents()
        setRemaining()

        window.setInterval({
            setRemaining()
        }, ONE_SECOND)
    }
}

fun setRemaining() {
    val secondsSpan = document.querySelector("#seconds")
    val minutesSpan = document.querySelector("#minutes")
    val hoursSpan = document.querySelector("#hours")
    val daysSpan = document.querySelector("#days")
    val weeksSpan = document.querySelector("#weeks")

    val now = Date.now()
    val diff = endOfTimeSeconds - now
    val seconds = if (diff > 0) (diff / 1000).roundToInt() else 0
    val minutes = if (diff > 0) seconds / 60 else 0
    val hours = if (diff > 0) minutes / 60 else 0
    val days = if (diff > 0) hours / 24 else 0
    val weeks = if (diff > 0) hours / 168 else 0

    secondsSpan?.textContent = seconds.toString()
    minutesSpan?.textContent = minutes.toString()
    hoursSpan?.textContent = hours.toString()
    daysSpan?.textContent = days.toString()
    weeksSpan?.textContent = weeks.toString()
}

fun Node.addContents() {
    append {
        h1 {
            +"When will be the end of time?"
        }
        div {
            +"The end of time is on ${endOfTime.toDateString()} at ${endOfTime.toTimeString()}"
        }
        div {
            +"Seconds remaining: "
            span {
                id = "seconds"
            }
        }
        div {
            +"Minutes remaining: "
            span {
                id = "minutes"
            }
        }
        div {
            +"Hours remaining: "
            span {
                id = "hours"
            }
        }
        div {
            +"Days remaining: "
            span {
                id = "days"
            }
        }
        div {
            +"Weeks remaining: "
            span {
                id = "weeks"
            }
        }
    }
}