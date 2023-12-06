/*
This is a Java program to establish a Single-Sign-On (SSO) service for a web application. 
Google accounts are used for user authentication.
*/

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class oauth_authentication_2_Req6 {

    private static final String CLIENT_SECRETS_JSON_RESOURCE = "/client_secrets.json";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";
    
    private static Credential authenticate() throws Exception {
        InputStream in = oauth_authentication_2_Req6.class.getResourceAsStream(CLIENT_SECRETS_JSON_RESOURCE);
        
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
    
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
            new NetHttpTransport(), JSON_FACTORY, clientSecrets, Collections.singletonList("https://www.googleapis.com/auth/userinfo.profile"))
            .setAccessType("online")
            .setApprovalPrompt("auto").build();
            
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }
    
    public static void main(String[] args) {
        try {
            Credential credential = authenticate();
            System.out.println("Successfully authenticated with token: " + credential.getAccessToken());
        } catch (Exception ex) {
            System.err.println("Failed to authenticate user: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}