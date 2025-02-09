package app.ohdyno.workout.features.step.definitions

import app.ohdyno.workout.features.SpecificationContext
import io.cucumber.java.en.When
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

class WhenSteps(val mockMvc: MockMvc, val specificationContext: SpecificationContext) {

  @When("recording the set") fun recordingTheSet() {}

  @When("displaying the exercise catalog")
  fun displayingTheExerciseCatalog() {
    specificationContext.page =
        specificationContext.webClient.getPage("http://localhost/exercises/")
    specificationContext.mockMvcResult = mockMvc.get("/exercises/")
  }
}
