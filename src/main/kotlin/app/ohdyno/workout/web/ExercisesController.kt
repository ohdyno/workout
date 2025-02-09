package app.ohdyno.workout.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/exercises")
class ExercisesController {
  @GetMapping("")
  fun getCatalog(): String {
    return "pages/exercises/index"
  }
}
