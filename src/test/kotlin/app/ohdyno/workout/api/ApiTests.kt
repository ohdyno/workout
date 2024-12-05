package app.ohdyno.workout.api

import kotlin.test.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
class ApiTests {
  @Autowired lateinit var mvc: MockMvc

  @Test
  fun sayHello() {
    mvc.get("/").andExpect { status { isOk() } }
  }
}
