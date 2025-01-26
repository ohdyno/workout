package app.ohdyno.workout.web

import me.xingzhou.projects.simple.event.store.EventStore
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/plans")
class PlanController {
  @GetMapping("/bb-upper-lower")
  fun bbUpperLower(): String {
    EventStore()
    return "pages/plans/bb-upper-lower"
  }
}
