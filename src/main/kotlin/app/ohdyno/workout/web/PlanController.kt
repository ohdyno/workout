package app.ohdyno.workout.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/plans")
class PlanController() {
  @GetMapping("/bb-upper-lower")
  fun bbUpperLower(): String {
    return "pages/plans/bb-upper-lower"
  }
}
