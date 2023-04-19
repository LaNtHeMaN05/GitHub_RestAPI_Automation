package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.GitHub_create_update_Repository;
import resources.ApiRoutes;
import resources.TestData_Serialization;
import resources.Utils;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

public class githubStepDefinitions extends Utils {

	RequestSpecification request;
	Response response;
	String responseString;
	static String repoName;
	TestData_Serialization tds = new TestData_Serialization();
	GitHub_create_update_Repository gcur = new GitHub_create_update_Repository();

	// Given for Create Repository for authenticated user
	@Given("I want to create a new repository")
	public void i_want_to_create_a_new_repository() throws IOException {

		request = given().spec(GitHub_ReqSpecFunction()).body(tds.setData_GitHub_CreateRepository()).log().all();

	}

	@When("The user hits {string} with {string} request")
	public void the_user_hits_with_request(String ApiType, String RequestType) {
		
		ApiRoutes ar=ApiRoutes.valueOf(ApiType);
		
		if (ApiType.equalsIgnoreCase( "CreateReposiory")) {
			response = request.when().post(ar.getApiRoute());
		}
		else if (ApiType.equalsIgnoreCase("UpdateRepository")) {
			response = request.when().patch(ar.getApiRoute() + github_Owner + repoName);
		}
		
		else if (ApiType.equalsIgnoreCase("DeleteRepository")) {
			response = request.when().delete(ar.getApiRoute() + github_Owner + repoName );
		}
		else if (ApiType.equalsIgnoreCase("GetRepositoryTopics")) {
			response = request.when().get(ar.getApiRoute());
		}
		else if(ApiType.equalsIgnoreCase("ReplaceAllTopics")) {
			response=request.when().put(ar.getApiRoute());
		}
		else if(ApiType.equalsIgnoreCase("EnableVulnerability")) {
			response=request.when().put(ar.getApiRoute()+github_Owner+repoName+"/vulnerability-alerts");
		}
		else if(ApiType.equalsIgnoreCase("CheckVulnerability")) {
			response=request.when().get(ar.getApiRoute()+github_Owner+repoName+"/vulnerability-alerts");
		}
		else if(ApiType.equalsIgnoreCase("DisableVulnerability")) {
			response=request.when().delete(ar.getApiRoute()+github_Owner+repoName+"/vulnerability-alerts");
		}

	}

	@Then("validate if the status code is {string}")
	public void validate_if_the_status_code_is(String RequiredResponseCode) {
		int statusCode = Integer.parseInt(RequiredResponseCode);
		responseString = response.then().assertThat().statusCode(statusCode).extract().asString();
	}

	@Then("{string} field in the response to match {string}")
	public void field_in_the_response_to_match(String ResponseTagName, String RequiredResponseValue) {
		JsonPath js = new JsonPath(responseString);
		String ActualResponseValue = js.get(ResponseTagName);
		repoName = js.get("name");
		assertEquals(ActualResponseValue, RequiredResponseValue);

	}

	// Given for updating existing repository
	@Given("I want user to update the existing repository")
	public void i_want_user_to_update_the_existing_repository() throws IOException {

		request = given().spec(GitHub_ReqSpecFunction()).body(tds.setData_GitHub_UpdateRepository()).log().all();

	}

	// Given for deleting a repository
	@Given("I want user to delete the repository")
	public void i_want_user_to_delete_the_repository() throws IOException {
		request = given().spec(GitHub_ReqSpecFunction()).log().all();
	}
	// Given for getting all the repository topics
	@Given("I want user to get all the repository topics")
	public void i_want_user_to_get_all_the_repository_topics() throws IOException {
		request = given().spec(GitHub_ReqSpecFunction()).log().all();

	}
	
	//Given for replacing all the repository topics
	@Given("I want user to replace all the repository topics")
	public void i_want_user_to_replace_all_the_repository_topics() throws IOException {
		request=given().spec(GitHub_ReqSpecFunction()).body(tds.setData_GitHub_replace_all_Topics()).log().all();
	}
	
	//Given for enabling vulnerability for a repo
	@Given("I want user to enable vulnerability alert")
	public void i_want_user_to_enable_vulnerability_alert() throws IOException {
		request=given().spec(GitHub_ReqSpecFunction()).log().all();
	}
	
	//Given for checking vulnerability alerts for a repo
	@Given("I want user to check vulnerability alerts for a repo")
	public void i_want_user_to_check_vulnerability_alerts_for_a_repo() throws IOException {
		request=given().spec(GitHub_ReqSpecFunction()).log().all();
	}
	
	//Given for deleting vulnerability alerts for a repo
	@Given("I want user to disable vulnerability alert")
	public void i_want_user_to_disable_vulnerability_alert() throws IOException {
		request=given().spec(GitHub_ReqSpecFunction()).log().all();
	}

	
}
