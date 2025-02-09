rootProject.name = "workout"

plugins { id("org.danilopianini.gradle-pre-commit-git-hooks") version "2.0.18" }

if (System.getenv("DOKKU_APP_TYPE") == null) {
  gitHooks {
    preCommit {
      from {
        """
              ./gradlew spotlessApply versionCatalogFormat
              git add --update
          """
            .trimIndent()
      }
    }

    commitMsg { conventionalCommits { defaultTypes() } }
    createHooks(true)
  }
}
