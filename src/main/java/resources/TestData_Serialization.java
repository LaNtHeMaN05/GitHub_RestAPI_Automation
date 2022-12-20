package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.GitHub_Replace_Topics;
import pojo.GitHub_create_update_Repository;

public class TestData_Serialization {
	
	public GitHub_create_update_Repository setData_GitHub_CreateRepository() {
		
		GitHub_create_update_Repository cr=new GitHub_create_update_Repository();
		cr.setName("ApiTest07");	
		cr.setDescription("Created via API Automation test");
		cr.setHomepage("https://github.com");
		cr.setIs_template(true);
		
		return cr;
	}
	
	public GitHub_create_update_Repository setData_GitHub_UpdateRepository() {
		GitHub_create_update_Repository cr=new GitHub_create_update_Repository();
		cr.setName("UpdatedApiTest07");	
		cr.setDescription("Updated via API Automation test");
		cr.setHomepage("https://github.com");
		
		return cr;
	}
	
	public GitHub_Replace_Topics setData_GitHub_replace_all_Topics() {
		GitHub_Replace_Topics grt=new GitHub_Replace_Topics();
		List<String> topicsList=new ArrayList<String>();
		topicsList.add("octocat");
		topicsList.add("atom");
		topicsList.add("electron");
		topicsList.add("api");
		grt.setNames(topicsList);
		return grt;
		
	}

}
