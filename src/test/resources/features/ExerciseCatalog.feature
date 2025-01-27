Feature: Exercise Catalog

  Background:
    Given the system is set up for testing

  Scenario: Displaying an empty exercise catalog
    When displaying the exercise catalog
    Then an empty catalog is displayed

  Scenario: Displaying the exercise catalog with exercises
    Given the "high-bar squat" has been added to the catalog
    When displaying the exercise catalog
    Then the catalog shows all exercises that have been added
