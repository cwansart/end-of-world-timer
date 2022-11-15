import kotlinx.browser.document
import kotlin.test.Test
import kotlin.test.assertContains

class TestClient {
    @Test
    fun testSayHello() {
        val container = document.createElement("div")
        container.addContents()
        container.textContent?.let {
            assertContains(it, "Seconds remaining")
            assertContains(it, "Minutes remaining")
            assertContains(it, "Hours remaining")
            assertContains(it, "Days remaining")
            assertContains(it, "Weeks remaining")
        }
    }
} 