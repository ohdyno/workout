package app.ohdyno.workout.features

import me.xingzhou.projects.simple.event.store.dependencies.eventserializer.ForEventSerializer
import me.xingzhou.projects.simple.event.store.dependencies.eventstorage.ForEventStorage
import org.htmlunit.WebClient
import org.htmlunit.html.HtmlPage
import org.springframework.test.web.servlet.ResultActionsDsl

class SpecificationContext(
    val forEventStorage: ForEventStorage,
    val forEventSerializer: ForEventSerializer
) {
  lateinit var page: HtmlPage
  lateinit var mockMvcResult: ResultActionsDsl
  lateinit var webClient: WebClient
}
