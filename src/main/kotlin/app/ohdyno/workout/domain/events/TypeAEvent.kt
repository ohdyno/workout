package app.ohdyno.workout.domain.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.xingzhou.projects.simple.event.store.Event

@Serializable
@SerialName("type-a-event")
class TypeAEvent(val id: String = "type-a-event-id") : Event
