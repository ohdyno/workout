package app.ohdyno.workout.spring.configs

import javax.sql.DataSource
import me.xingzhou.projects.simple.event.store.dependencies.eventstorage.ForEventStorage
import me.xingzhou.projects.simple.event.store.dependencies.eventstorage.adapters.postgres.ForEventStorage
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EventStorageConfig {
  @Bean
  fun createAdapterForEventStorage(dataSource: DataSource): ForEventStorage {
    return ForEventStorage(dataSource = dataSource)
  }
}
