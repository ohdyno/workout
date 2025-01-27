package app.ohdyno.workout.features.step.definitions

import app.ohdyno.workout.features.SpecificationContext
import io.cucumber.java.en.When
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

class WhenSteps(val mockMvc: MockMvc, val testContext: SpecificationContext) {

  @When("recording the set") fun recordingTheSet() {}

  @When("displaying the exercise catalog")
  fun displayingTheExerciseCatalog() {
    testContext.resultActionsDsl = mockMvc.get("/")
  }
}
