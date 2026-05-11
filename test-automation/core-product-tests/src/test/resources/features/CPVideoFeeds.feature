Feature: Home Page Product Details Extraction
@CPProductSelection
Scenario: Extract product details by category
  Given user launches the CP application
  When user navigates to menu and selects "News & Features" category
  Then user captures all "Video Feeds" details greater than 3d
  


