Feature: DP1 Home Page Slides

  @DP1
  Scenario: Verify slides under Tickets menu on DP1 home page
    Given I am on the DP1 home page
    When I navigate to the Tickets menu slides and count the number of slides present
    Then I get the title and duration of each slide
