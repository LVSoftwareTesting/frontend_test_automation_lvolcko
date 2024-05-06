Feature: Auth

  # Scenario is important because system has to allow sign in users
  # which are using correct credentials and user without this functionality
  # can not interact with web app and client is loosing money

  @Auth @Smoke
  Scenario Outline: Successful login and logout
    Given I am on the login-page
    When I enter valid '<username>' and '<password>'
    And I click on the 'Login' button
    Then I should be redirected to the home-page
    When I click on the 'Logout' button
    Then I should be redirected to the login-page

    Examples:
      | username                | password     |
      | standard_user           | secret_sauce |
      | problem_user            | secret_sauce |
      | performance_glitch_user | secret_sauce |
      | error_user              | secret_sauce |
      | visual_user             | secret_sauce |

  # Scenario is included because system can must not allow sign in to user
  # with wrong credentials or blocked user because unauthorized users or
  # blocked user are threat to system

  @Auth @Smoke
   Scenario Outline: Unsuccessful login attempt
     Given I am on the login-page
     When I enter invalid '<username>' and '<password>'
     And I click on the 'Login' button
     Then I should see an error '<message>'

     Examples:
       | username        | password     | message                                                                   |
       | locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out.                       |
       | test            | secret_sauce | Epic sadface: Username and password do not match any user in this service |