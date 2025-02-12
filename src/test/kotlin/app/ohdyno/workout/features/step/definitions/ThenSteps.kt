package app.ohdyno.workout.features.step.definitions

import app.ohdyno.workout.domain.events.TypeAEvent
import app.ohdyno.workout.features.SpecificationContext
import io.cucumber.java.en.And
import io.cucumber.java.en.Then
import kotlin.test.assertContains
import me.xingzhou.projects.simple.event.store.EventId
import me.xingzhou.projects.simple.event.store.EventStore
import me.xingzhou.projects.simple.event.store.OccurredOn
import me.xingzhou.projects.simple.event.store.StreamName
import me.xingzhou.projects.simple.event.store.commands.CreateStream
import me.xingzhou.projects.simple.event.store.dependencies.ExecutionContext
import me.xingzhou.projects.simple.event.store.results.EventStoreResult
import org.htmlunit.html.HtmlElement

class ThenSteps(val specificationContext: SpecificationContext) {

  @Then("an ad hoc workout is started") fun anAdHocWorkoutIsStarted() {}

  @And("the set is recorded for the workout") fun theSetIsRecordedForTheWorkout() {}

  @Then("an empty catalog is displayed")
  fun anEmptyCatalogIsDisplayed() {
    val content =
        specificationContext.page.getHtmlElementById<HtmlElement>("exercise-catalog").textContent
    assertContains(content, "No Exercises")
  }

  @Then("the catalog shows all exercises that have been added")
  fun theCatalogShowsAllExercisesThatHaveBeenAdded() {
    val event = TypeAEvent()
    val executionContext =
        ExecutionContext(
            command =
                CreateStream(
                    streamName = StreamName("foo"),
                    event = event,
                    eventId = EventId(event.id),
                    occurredOn = OccurredOn.now()),
            forEventSerialization = specificationContext.forEventSerializer,
            forEventStorage = specificationContext.forEventStorage)
    val result = EventStore().handle(executionContext)

    result as EventStoreResult.ForCreateStream
  }
}
