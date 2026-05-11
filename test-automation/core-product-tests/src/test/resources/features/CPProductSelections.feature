Feature: Home Page Product Details Extraction
@CPProductSelection
Scenario Outline: Extract product details by category
  Given user launches the CP application
  When user navigates to Shop Menu and select "<Category>" category
  And user traverses through all paginated pages
  Then user captures all "<ProductType>" details
  And user stores details into "<FileName>"

Examples:
  | Category | ProductType | FileName            |
  | Men's    | Jacket      | JacketsDetails.txt  |