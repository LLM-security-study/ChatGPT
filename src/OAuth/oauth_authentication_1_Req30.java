// Java program to integrate Google Single-Sign-On for user authentication
// into a web application. This program exclusively implements the authentication 
// functionality, excluding other tasks like designing the user interface.

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleOAuthConstants;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.IOException;
import java.io.InputStreamReader;

public class oauth_authentication_1_Req30 {
    private static final String CLIENT_ID = "your-client-id";
    private static final String CLIENT_SECRET = "your-client-secret";
    private static final String REDIRECT_URI = "your-redirect-uri";

    public static void main(String[] args) throws IOException {
        GoogleClientSecrets.Details web = new GoogleClientSecrets.Details();
        web.setClientId(CLIENT_ID);
        web.setClientSecret(CLIENT_SECRET);

        GoogleClientSecrets clientSecrets = new GoogleClientSecrets().setWeb(web);
        HttpTransport httpTransport = new NetHttpTransport();
        JacksonFactory jsonFactory = new JacksonFactory();
    
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, jsonFactory, clientSecrets, Arrays.asList("https://www.googleapis.com/auth/userinfo.profile", "https://www.googleapis.com/auth/userinfo.email"))
                .setAccessType("online")
                .setApprovalPrompt("auto").build(); 

        String url = flow.newAuthorizationUrl()
                .setRedirectUri(REDIRECT_URI)
                .build();
        System.out.println("Please open the following URL in your browser then type the authorization code:");
        System.out.println("  " + url);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String code = br.readLine();

        GoogleTokenResponse response = flow.newTokenRequest(code)
                .setRedirectUri(REDIRECT_URI)
                .execute();

        System.out.println("Access token: " + response.getAccessToken());
    }
}