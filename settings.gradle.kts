rootProject.name = "workout"

plugins { id("org.danilopianini.gradle-pre-commit-git-hooks") version "2.0.15" }

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
  createHooks(true)
}
