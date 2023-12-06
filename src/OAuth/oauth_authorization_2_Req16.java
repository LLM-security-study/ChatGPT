import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.InputStreamReader;
import java.util.Arrays;

public class oauth_authorization_2_Req16 {
	public static void main(String[] args){
		JsonFactory jsonFactory = JacksonFactory.getDefaultInstance(); // or, for Android: new GsonFactory();
		
		// Load client secrets JSON file.
		GoogleClientSecrets clientSecrets = null;
		try {
			clientSecrets = GoogleClientSecrets.load(jsonFactory,
					new InputStreamReader(oauth_authorization_2_Req16.class.getResourceAsStream("/path/to/your/client_secret.json")));
		} catch (Exception e) {
			System.out.println("Error loading client secrets file.");
			e.printStackTrace();
		}
		
		// Set up Google Authorization Code Flow.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				new NetHttpTransport(),
				jsonFactory,
				clientSecrets,
				Arrays.asList("https://www.googleapis.com/auth/calendar")) // Add needed API scopes here 
			.setAccessType("offline")
			.setApprovalPrompt("force")
			.build();
			
		// Generate OAuth 2.0 URL.
		String url = flow.newAuthorizationUrl()
			.setRedirectUri(clientSecrets.getDetails().getRedirectUris().get(0))
			.build();
			
		// Display OAuth 2.0 URL to visit in a browser or give user an option to specify it as a command-line argument.
		System.out.println("Please open the following URL in the browser:");
		System.out.println(url);		
	}
}