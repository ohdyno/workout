rootProject.name = "workout"

plugins { id("org.danilopianini.gradle-pre-commit-git-hooks") version "2.0.15" }

gitHooks {
  preCommit { tasks("spotlessApply") }
  commitMsg { conventionalCommits { defaultTypes() } }
  createHooks()
}
