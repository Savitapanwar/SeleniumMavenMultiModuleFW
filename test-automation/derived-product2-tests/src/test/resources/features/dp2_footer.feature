Feature: DP2 Home Page Slides

  @DP2
  Scenario: Verify the Hyperlinks in the Footer Section of the DP2 Home Page
    Given I am on the DP2 home page
    When I scroll down to the footer of the DP2 home page and get all the links present in the footer section
    Then I verify if there are any duplicate links present in the footer section of the DP2 home page
    Then I store the links to the CSV file with the name "DP2_Footer_Links.csv"
