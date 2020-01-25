Feature: add to memo

  Scenario: add to memo one item from category page
    Given I am on the category page
    When I choose random item page
    And I choose random item from first item page
    And I add an item to memo
    And I go to memo
    Then The memo page contains chosen item
    Then Counter in header changed

  Scenario: add to memo two items from category page
    Given I am on the category page
    When I choose random item page
    And I choose "2" random items from first item page
    And I add selected items to memo
    Then Counter in header is "2"
    And I go to memo
    Then The memo page contains chosen items

