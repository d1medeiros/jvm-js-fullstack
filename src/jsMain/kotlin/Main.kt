import kotlinx.browser.document
import pages.DailyList
import pages.DelayedList
import pages.DefaultList
import react.*
import react.dom.client.createRoot
import react.router.Route
import react.router.Routes
import react.router.dom.BrowserRouter

fun main() {
    val container = document.getElementById("root") ?: error("Couldn't find container!")
    createRoot(container).render(MRouter.create())
}

val MRouter = VFC {
    BrowserRouter {
        Routes {
            Route {
                this.index = true
                this.element = createElement(DailyList)
            }
            Route {
                this.path = "/default"
                this.element = createElement(DefaultList)
            }
            Route {
                this.path = "/delayed"
                this.element = createElement(DelayedList)
            }
        }
    }
}