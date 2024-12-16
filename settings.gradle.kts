rootProject.name = "workout"

plugins { id("org.danilopianini.gradle-pre-commit-git-hooks") version "2.0.17" }

if (System.getenv("DOKKU_APP_TYPE") == null) {
  gitHooks {
    preCommit {
      from {
        """
              ./gradlew spotlessApply
              git add --update
          """
            .trimIndent()
      }
    }

    commitMsg { conventionalCommits { defaultTypes() } }
    createHooks()
  }
}
