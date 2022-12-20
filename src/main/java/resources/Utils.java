package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification reqSpecGitHub;
	public String projectDirectory = System.getProperty("user.dir");
	public FileInputStream fis;
	public String bearerToken;
	public String baseURI;
	public String github_Owner;
	public PrintStream github_Logs;

	public RequestSpecification GitHub_ReqSpecFunction() throws IOException {

		fis = new FileInputStream(projectDirectory + "/src/main/java/data.properties");
		Properties prop = new Properties();
		prop.load(fis);
		baseURI = prop.getProperty("uri");
		github_Owner = prop.getProperty("owner");
		bearerToken = prop.getProperty("bearer_token");
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy HH_mm_ss");
		Date date = new Date();
		String dt = formatter.format(date);
		System.out.println(dt);

		if (reqSpecGitHub == null) {
			github_Logs = new PrintStream(new FileOutputStream("GitHubs_Logs_" + dt + "_.txt"));
			reqSpecGitHub = new RequestSpecBuilder().addHeader("Content-Type", "application/json")
					.addHeader("Authorization", "Bearer " + bearerToken)
					.addHeader("Accept", "application/vnd.github+json").addHeader("X-GitHub-Api-Version", "2022-11-28")
					.setBaseUri(baseURI).addFilter(RequestLoggingFilter.logRequestTo(github_Logs))
					.addFilter(ResponseLoggingFilter.logResponseTo(github_Logs)).build();
			return reqSpecGitHub;
		}
		return reqSpecGitHub;

	}

}
