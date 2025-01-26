package app.ohdyno.workout

import app.ohdyno.workout.domain.events.TypeAEvent
import me.xingzhou.projects.simple.event.store.EventStore
import me.xingzhou.projects.simple.event.store.OccurredOn
import me.xingzhou.projects.simple.event.store.StreamName
import me.xingzhou.projects.simple.event.store.commands.CreateStream
import me.xingzhou.projects.simple.event.store.dependencies.ExecutionContext
import me.xingzhou.projects.simple.event.store.dependencies.eventserializer.ForEventSerializer
import me.xingzhou.projects.simple.event.store.dependencies.eventstorage.ForEventStorage
import me.xingzhou.projects.simple.event.store.results.EventStoreResult
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(properties = ["spring.datasource.url=jdbc:tc:postgresql:17.2:///test"])
class DatasourceSetupTest {

  @Autowired private lateinit var forEventStorage: ForEventStorage

  @Autowired private lateinit var forEventSerializer: ForEventSerializer

  @Test
  fun dataSourceIsSetupCorrect() {
    val executionContext =
        ExecutionContext(
            command =
                CreateStream(
                    streamName = StreamName("foo"),
                    event = TypeAEvent(),
                    occurredOn = OccurredOn.now()),
            forEventSerialization = forEventSerializer,
            forEventStorage = forEventStorage)
    val result = EventStore().handle(executionContext)

    result as EventStoreResult.ForCreateStream
  }
}
