package app.ohdyno.workout.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ViewController {
  @GetMapping("/about")
  fun about(): String {
    return "pages/about"
  }
}
