// Program to enable Single-Sign-On (SSO) using Google accounts for a web application.

// Need to include dependency for Google API Client and Google OAUTH Client
// Example using Maven:
/*
<dependencies>
  <dependency>
    <groupId>com.google.api-client</groupId>
    <artifactId>google-api-client</artifactId>
    <version>1.23.0</version>
  </dependency>
  <dependency>
    <groupId>com.google.oauth-client</groupId>
    <artifactId>google-oauth-client-jetty</artifactId>
    <version>1.23.0</version>
  </dependency>
</dependencies>
*/

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.InputStreamReader;
import java.util.Collections;

public class oauth_authentication_3_Req15 {
    
    private static final String REDIRECT_URI = "INSERT REDIRECT URI HERE";
    private static final String CLIENT_ID = "INSERT CLIENT ID HERE";
    private static final String CLIENT_SECRET = "INSERT CLIENT SECRET HERE";
    
    public static void main(String[] args) {
        GoogleClientSecrets clientSecrets = new GoogleClientSecrets();
        clientSecrets.getWeb()
            .setClientId(CLIENT_ID)
            .setClientSecret(CLIENT_SECRET)
            .setRedirectUris(Collections.singletonList(REDIRECT_URI));
        
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
            new NetHttpTransport(), 
            JacksonFactory.getDefaultInstance(), 
            clientSecrets, 
            Collections.singletonList("openid")
        ).setAccessType("offline").setApprovalPrompt("force").build();
        
        // Show authorization URL to user ...
        // Example:
        // String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
        // System.out.println("Please open the following URL in your browser: " + url);
        
        // Get authorization code from redirect URL ...
        // Example:
        // String code = ... // code from redirect URL
        // GoogleTokenResponse tokenResponse = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
        
        // Use token to access user's Google account.
    }
}