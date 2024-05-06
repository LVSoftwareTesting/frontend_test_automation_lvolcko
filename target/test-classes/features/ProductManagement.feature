 Feature: Product Management

   # Scenario is used because it is managing probably main function of web app
   # and if its not working then client is loosing money

   @ProductManagement @Smoke
   Scenario Outline: Add all products to cart and checkout
     Given I am logged in with valid '<username>' and '<password>'
     When I add all products to the cart
     Then I can see in cart badge same value as quantity of products listed on home-page
     And I click on cart button
     Then I am redirected to cart-page
     And I can see all products from home-page listed in the cart
     And I proceed to checkout and fill personal information: '<first_name>', '<last_name>', '<postal_code>'
     And I click on continue button
     Then I am redirected to checkout overview page
     And I verify the prices
     And I click on finish button
     Then I am redirected to checkout complete page
     And I click on back to home page
     Then cart is empty

    Examples:
    | username      | password     | first_name      | last_name     | postal_code |
    | standard_user | secret_sauce | d               | d             | d           |

     # User shouldn't be able fill personal informatin just with one character
     # mainly no in postal_code. There should be some regex as verification on
     # mentioned fields