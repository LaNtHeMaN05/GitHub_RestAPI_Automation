Feature: GitHub RestApis
	@CreateRepository @All
  Scenario: Create a repository for a user in GitHut
    Given I want to create a new repository
    When The user hits "CreateReposiory" with "POST" request
    Then validate if the status code is "201"
    And "homepage" field in the response to match "https://github.com"

	  @UpdateRepository @All
		Scenario: Update a created repository by the user
		Given I want user to update the existing repository
		When The user hits "UpdateRepository" with "PATCH" request
		Then validate if the status code is "200"
		And "homepage" field in the response to match "https://github.com"
		
		@EnableVulnerability @All
		Scenario: Enable Vulnerability alert for the repository
		Given I want user to enable vulnerability alert
		When The user hits "EnableVulnerability" with "PUT" request
		Then validate if the status code is "204"
		
		@CheckVulnerability @All
		Scenario: Check vulenerability for the repository
		Given I want user to check vulnerability alerts for a repo
		When The user hits "CheckVulnerability" with "GET" request
		Then validate if the status code is "204"
		
		
		@DisableVulnerability @All
		Scenario: Disable Vulnerability alert for the repository
		Given I want user to disable vulnerability alert
		When The user hits "DisableVulnerability" with "DELETE" request
		Then validate if the status code is "204"
		
		@DeleteRepository @All
		Scenario: Delete a repository for the user
		Given I want user to delete the repository
		When The user hits "DeleteRepository" with "DELETE" request
		Then validate if the status code is "204"
		
		@GetTopics @All
		Scenario: Get all the topics of a repository
		Given I want user to get all the repository topics
		When The user hits "GetRepositoryTopics" with "GET" request
		Then validate if the status code is "200"
		
		@ReplaceAllTopics @All
		Scenario: Replace all the topics of a repository
		Given I want user to replace all the repository topics
		When The user hits "ReplaceAllTopics" with "PUT" request
		Then validate if the status code is "200"
	