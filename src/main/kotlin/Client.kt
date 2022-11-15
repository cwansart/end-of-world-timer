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

val endOfTime = Date("2022-12-23 16:00:00")
val endOfTimeSeconds = Date.parse("2022-12-23 16:00:00")

fun main() {
    window.onload = {
        document.body?.sayHello()
        setRemaining()

        window.setInterval({
            setRemaining()
        }, 1000)
    }
}

fun setRemaining() {
    val secondsSpan = document.querySelector("#seconds")
    val minutesSpan = document.querySelector("#minutes")
    val hoursSpan = document.querySelector("#hours")
    val daysSpan = document.querySelector("#days")
    val weeksSpan = document.querySelector("#weeks")

    val now = Date.now()
    val seconds = ((endOfTimeSeconds - now) / 1000).roundToInt()
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24
    val weeks = hours / 168

    secondsSpan?.textContent = seconds.toString()
    minutesSpan?.textContent = minutes.toString()
    hoursSpan?.textContent = hours.toString()
    daysSpan?.textContent = days.toString()
    weeksSpan?.textContent = weeks.toString()
}

fun Node.sayHello() {
    append {
        h1 {
            +"When will be the end of time?"
        }
        div {
            +"The end of time is on ${endOfTime.toUTCString()}"
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