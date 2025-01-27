package app.ohdyno.workout.features

import me.xingzhou.projects.simple.event.store.dependencies.eventstorage.ForEventStorage
import me.xingzhou.projects.simple.event.store.dependencies.eventstorage.adapters.inmemory.ForEventStorage
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class TestBeanConfiguration {
  @Bean
  fun createAdapterForEventStorage(): ForEventStorage {
    return ForEventStorage()
  }
}
