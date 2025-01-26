package app.ohdyno.workout.event.store.adapters

import app.ohdyno.workout.event.store.adapters.eventserializer.KotlinXSerializationAdapter
import javax.sql.DataSource
import kotlinx.serialization.modules.SerializersModule
import me.xingzhou.projects.simple.event.store.dependencies.eventserializer.ForEventSerializer
import me.xingzhou.projects.simple.event.store.dependencies.eventstorage.ForEventStorage
import me.xingzhou.projects.simple.event.store.dependencies.eventstorage.adapters.postgres.ForEventStorage
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringConfig {
  @Bean
  fun createAdapterForEventStorage(dataSource: DataSource): ForEventStorage {
    return ForEventStorage(dataSource = dataSource)
  }

  @Bean
  fun createAdapterForEventSerializer(serializersModule: SerializersModule): ForEventSerializer {
    return KotlinXSerializationAdapter(serializersModule)
  }
}
