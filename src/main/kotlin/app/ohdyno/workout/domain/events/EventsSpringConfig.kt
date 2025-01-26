package app.ohdyno.workout.domain.events

import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import me.xingzhou.projects.simple.event.store.Event
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EventsSpringConfig {
  @Bean
  fun createPolymorphicEvents(): SerializersModule {
    return SerializersModule { polymorphic(Event::class) { subclass(TypeAEvent::class) } }
  }
}
