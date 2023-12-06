// Java program to integrate Google Single-Sign-On for user authentication into a web application

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

public class oauth_authentication_1_Req10 {

    private static final String CLIENT_ID = "your-client-id";
    private static final String CLIENT_SECRET = "your-client-secret";
    private static final String REDIRECT_URI = "your-redirect-uri";

    public static void main(String[] args) {
        try {
            // Create a new authorization code flow
            AuthorizationCodeFlow flow = new AuthorizationCodeFlow.Builder(
                    new NetHttpTransport(), 
                    JacksonFactory.getDefaultInstance(), 
                    CLIENT_ID, CLIENT_SECRET, 
                    Arrays.asList("openid", "profile", "email"))
                    .setAccessType("offline")
                    .setApprovalPrompt("force")
                    .build();

            // Build the authorization code request URL
            AuthorizationCodeRequestUrl authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI);
            System.out.println("Please open the following URL in your browser then type the authorization code:");
            System.out.println("  " + authorizationUrl.build());

            // Read the authorization code from standard input stream
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please input the authorization code:");
            String code = reader.readLine();

            // Create and authorize a new token
            TokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();

            // Use the token in your application
            System.out.println("Access token: " + response.getAccessToken());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}