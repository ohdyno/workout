package db.migration.postgresql

import me.xingzhou.projects.simple.event.store.dependencies.eventstorage.adapters.postgres.setupDatabase
import org.flywaydb.core.api.migration.BaseJavaMigration
import org.flywaydb.core.api.migration.Context

@Suppress("unused") // Used by flyway for database migrations
class V1__SetupEventStoreSchema : BaseJavaMigration() {
  override fun migrate(context: Context) {
    setupDatabase(context.connection)
  }
}
