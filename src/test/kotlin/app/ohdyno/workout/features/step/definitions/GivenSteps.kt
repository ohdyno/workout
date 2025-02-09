package app.ohdyno.workout.features.step.definitions

import app.ohdyno.workout.features.SpecificationContext
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder

class GivenSteps(
    private val specificationContext: SpecificationContext,
    private val mockMvc: MockMvc
) {

  @Given("the system is set up for testing")
  fun theSystemIsSetUpForTesting() {
    specificationContext.webClient =
        MockMvcWebClientBuilder.mockMvcSetup(mockMvc).build().apply {
          options.isCssEnabled = false
          options.isJavaScriptEnabled = false
        }
  }

  @Given("no ongoing workout") fun noOngoingWorkout() {}

  @And("a weighted set") fun aWeightedSet() {}

  @Given("the {string} has been added to the catalog")
  fun theHasBeenAddedToTheCatalog(exerciseName: String) {}
}
