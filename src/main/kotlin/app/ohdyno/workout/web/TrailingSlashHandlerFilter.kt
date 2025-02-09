package app.ohdyno.workout.web

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.io.IOException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.filter.UrlHandlerFilter

@Component
class TrailingSlashHandlerFilter : OncePerRequestFilter() {
  /** Redirect all urls with a trailing slash to ones without a trailing slash. */
  @Throws(ServletException::class, IOException::class)
  override fun doFilterInternal(
      request: HttpServletRequest,
      response: HttpServletResponse,
      filterChain: FilterChain
  ) {
    val filter =
        UrlHandlerFilter.trailingSlashHandler("/**").redirect(HttpStatus.PERMANENT_REDIRECT).build()

    filter.doFilter(request, response, filterChain)
  }
}
