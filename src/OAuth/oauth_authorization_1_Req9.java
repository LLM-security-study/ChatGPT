// Import the necessary libraries
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.DriveScopes;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class oauth_authorization_1_Req9 {
    // Define the required scopes
    private static final java.util.Collection<String> SCOPES = DriveScopes.all();

    public static void main(String args[]) throws Exception {
        // Load client secrets
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
            JacksonFactory.getDefaultInstance(), 
            new InputStreamReader(oauth_authorization_1_Req9.class.getResourceAsStream("/client_secrets.json"))
        );

        // This allows the API to call back into your application for code exchange.
        String redirectUri = "Add your redirect URI here";
        
        // Build the authorization flow
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
            GoogleNetHttpTransport.newTrustedTransport(),
            JacksonFactory.getDefaultInstance(), 
            clientSecrets, 
            SCOPES)
            .setAccessType("offline")
            .setApprovalPrompt("force")
            .build();
            
        // Generate the URL to which we will direct users.
        String url = flow.newAuthorizationUrl()
            .setRedirectUri(redirectUri)
            .build();
        
        System.out.println("Please open the following URL in a web browser then type the authorization code:\n" + url);

        // Wait for the authorization code.
        System.out.println("Enter authorization code:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String code = br.readLine();
        
        // Exchange the auth code for access token
        GoogleTokenResponse response = flow.exchangeCodeForToken(code);
        
        // Create a credential object using the credential factory and setup the HTTP transport
        GoogleCredential credential = new GoogleCredential.Builder()
            .setTransport(httpTransport)
            .setJsonFactory(JSON_FACTORY)
            .setClientSecrets(clientSecrets)
            .build()
            .setFromTokenResponse(response);
            
        // Now you can use 'credential' to access Google APIs
        System.out.println("Access token: " + credential.getAccessToken());
    }
}