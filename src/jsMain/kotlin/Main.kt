import kotlinx.browser.document
import pages.DailyList
import pages.DefaultList
import pages.DelayedList
import pages.FinishedList
import react.VFC
import react.create
import react.dom.client.createRoot
import react.router.Route
import react.router.Routes
import react.router.dom.BrowserRouter

fun main() {
    val container = document.getElementById("root")
        ?: error("Couldn't find container!")
    createRoot(container).render(MRouter.create())
}

val MRouter = VFC {
    BrowserRouter {
        basename = "/tdahelper"
        Routes {
            Route {
                path = Page.DAILY.index
                element = DailyList.create()
            }
            Route {
                path = Page.DEFAULT.index
                element = DefaultList.create()
            }
            Route {
                path = Page.DELAYED.index
                element = DelayedList.create()
            }
            Route {
                path = Page.FINISHED.index
                element = FinishedList.create()
            }
        }
    }
}