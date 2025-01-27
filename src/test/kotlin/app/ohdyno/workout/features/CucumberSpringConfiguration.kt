package app.ohdyno.workout.features

import app.ohdyno.workout.WorkoutApplication
import app.ohdyno.workout.spring.configs.EventSerializerConfig
import io.cucumber.spring.CucumberContextConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.ContextConfiguration

@CucumberContextConfiguration
@WebMvcTest
@ContextConfiguration(
    classes =
        [
            WorkoutApplication::class,
            SpecificationContext::class,
            TestBeanConfiguration::class,
            EventSerializerConfig::class])
class CucumberSpringConfiguration {}
