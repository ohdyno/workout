package app.ohdyno.workout.spring.configs

import app.ohdyno.workout.domain.events.TypeAEvent
import app.ohdyno.workout.event.store.adapters.eventserializer.KotlinXSerializationAdapter
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import me.xingzhou.projects.simple.event.store.Event
import me.xingzhou.projects.simple.event.store.dependencies.eventserializer.ForEventSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EventSerializerConfig {
  @Bean
  fun createAdapterForEventSerializer(): ForEventSerializer {
    return KotlinXSerializationAdapter(
        serializerModule =
            SerializersModule { polymorphic(Event::class) { subclass(TypeAEvent::class) } })
  }
}
