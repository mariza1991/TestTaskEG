Feature: add to memo

  Scenario: add to memo from category page
    Given I am on the category page
    When I choose random item
    And I add an item to memo
  #  And I go to memo
  # Then The memo page contains choosen item

  #Scenario: remove adding from memo page
  #  Given I am on the memo page
  #  When I choose random item
  #  And I remove item from memo
  #  Then The memo page doesn't contains removed item