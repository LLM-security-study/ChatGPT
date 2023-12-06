import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;

// The program enables Single-Sign-On (SSO) using Google accounts for a web application.
public class oauth_authentication_3_Req27 { 
    private static final String REDIRECT_URI = "<INSERT_REDIRECT_URI>";
    private static final String CLIENT_ID = "<INSERT_CLIENT_ID>";
    private static final String CLIENT_SECRET = "<INSERT_CLIENT_SECRET>";
    
    public static void main(String[] args) throws Exception {
        GoogleClientSecrets secrets = GoogleClientSecrets.load(
                JacksonFactory.getDefaultInstance(), new FileReader("client_secrets.json"));
    
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(), 
                JacksonFactory.getDefaultInstance(), 
                secrets, 
                Collections.singleton("openid profile email"))
                .setAccessType("offline").build();
           
        // generate the authorization URL
        String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
        System.out.println("Please open the following URL in your browser then type " +
                         "the authorization code:");
        System.out.println("  " + url);
        
        // Wait for the authorization code
        System.out.println("Enter authorization code:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String code = br.readLine();
     
        // exchange the authorization code for an access token
        GoogleAuthorizationCodeTokenRequest tokenRequest = flow.newTokenRequest(code);  
        tokenRequest.setRedirectUri(REDIRECT_URI); 
        GoogleTokenResponse tokenResponse = tokenRequest.execute();
        
        // now you can use the tokenResponse to get more info about the user, or as credentials
        // to authenticate further API calls.
    } 
}