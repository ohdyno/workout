package app.ohdyno.workout.features

import me.xingzhou.projects.simple.event.store.dependencies.eventserializer.ForEventSerializer
import me.xingzhou.projects.simple.event.store.dependencies.eventstorage.ForEventStorage
import org.springframework.test.web.servlet.ResultActionsDsl

class SpecificationContext(
    val forEventStorage: ForEventStorage,
    val forEventSerializer: ForEventSerializer
) {
  lateinit var resultActionsDsl: ResultActionsDsl
}
