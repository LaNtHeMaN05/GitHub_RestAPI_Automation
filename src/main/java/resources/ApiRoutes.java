package resources;

public enum ApiRoutes {
	
	
	CreateReposiory("user/repos"),
	UpdateRepository("repos/"),
	DeleteRepository("repos/"),
	GetRepositoryTopics("repos/MagicJohnCons/World/topics"),
	ReplaceAllTopics("repos/MagicJohnCons/World/topics"),
	EnableVulnerability("repos/"),
	CheckVulnerability("repos/"),
	DisableVulnerability("repos/");
	
	String apiRoute;

	ApiRoutes(String apiRoute) {
		
		this.apiRoute=apiRoute;
		
	}
	
	public String getApiRoute() {
		return apiRoute;
	}
	
}
