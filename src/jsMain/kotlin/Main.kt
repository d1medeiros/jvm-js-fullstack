import kotlinx.browser.document
import pages.DailyList
import pages.DefaultList
import pages.DelayedList
import react.VFC
import react.create
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
        basename = "/tdahelper"
        Routes {
            Route {
                path = "/"
                element = DailyList.create()
            }
            Route {
                this.path = "/default"
                this.element = DefaultList.create()
            }
            Route {
                this.path = "/delayed"
                this.element = DelayedList.create()
            }
        }
    }
}