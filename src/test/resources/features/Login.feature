Feature: Login functionality

  Scenario: Valid login to OrangeHRM
    Given I navigate to the OrangeHRM login page "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    When I enter username "Admin" and password "admin123"
    Then I should be redirected to the dashboard
