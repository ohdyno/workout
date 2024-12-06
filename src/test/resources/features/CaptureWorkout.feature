Feature: Capturing a Workout
  A Workout is made up of a list of Exercises and Rests.

  Background:
    Given the system is set up for testing

  Scenario: Planning a new Workout

  Scenario: Starting a planned Workout

  Scenario: Starting an ad hoc Workout

  Scenario: Record a Weighted Set without an ongoing Workout
    A Weighted Set is a set that has an exercise done with weights for a number of repetitions.

    Given no ongoing workout
    And a weighted set
    When recording the set
    Then an ad hoc workout is started
    And the set is recorded for the workout

  Scenario: Record a Duration Set without an ongoing Workout
    A Duration Set is a set that has an exercise done with or without weights for a length of time.

  Scenario: Record a Weighted Set with an ongoing Workout

  Scenario: Record a Duration Set with an ongoing Workout

  Scenario: Ending a Workout
