// Import required libraries
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

// Class Description: This class is responsible for Google Single-Sign-On Authentication.
public class oauth_authentication_1_Req17 {
    
    private static final String REDIRECT_URI = "Enter redirect URI here";
    private static final String CLIENT_ID = "Enter client ID here";
    private static final String CLIENT_SECRET = "Enter client secret here";
    
    public static void main(String[] args) throws IOException {
        // Load client secrets
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                JacksonFactory.getDefaultInstance(), new FileReader("path_to_client_secret.json")
        );
    
        // Build flow 
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(),
                JacksonFactory.getDefaultInstance(),
                clientSecrets,
                Arrays.asList("https://www.googleapis.com/auth/userinfo.profile")
        ).setAccessType("offline")
                .setApprovalPrompt("auto").build();
    
        // Build authentication URL
        String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
        System.out.println("Please open the following URL in your browser then type the authorization code:");
        System.out.println("  " + url);
    
        // Enter authorization code here.
        Credential credential = flow.newTokenRequest("Enter authorization code here")
                .setRedirectUri(REDIRECT_URI).execute();
    }
}