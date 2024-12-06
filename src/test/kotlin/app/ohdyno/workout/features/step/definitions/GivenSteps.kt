package app.ohdyno.workout.features.step.definitions

import io.cucumber.java.en.And
import io.cucumber.java.en.Given

class GivenSteps {

  @Given("the system is set up for testing") fun theSystemIsSetUpForTesting() {}

  @Given("no ongoing workout") fun noOngoingWorkout() {}

  @And("a weighted set") fun aWeightedSet() {}
}
