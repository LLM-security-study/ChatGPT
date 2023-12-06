import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow.Builder;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

/**
 * Implements Google SSO authentication for a web application.
 * Exclusively implements the authentication functionality.
 */
public class oauth_authentication_1_Req16 {

  // Replace these with the actual details from your Google API Console
  private static final String CLIENT_ID = "<YOUR_CLIENT_ID>";
  private static final String CLIENT_SECRET = "<YOUR_CLIENT_SECRET>";
  private static final String REDIRECT_URI = "<YOUR_REDIRECT_URI>";
  
  public static void main(String... args) throws Exception {
    // Building the Google Authorization Code Flow.
    GoogleAuthorizationCodeFlow flow = new Builder(
        new NetHttpTransport(), 
        JacksonFactory.getDefaultInstance(), 
        CLIENT_ID, 
        CLIENT_SECRET, 
        Arrays.asList("profile", "email"))
        .setAccessType("offline")
        .setApprovalPrompt("force")
        .build();

    // Generate the Google Authentication URL
    String url = flow.newAuthorizationUrl()
      .setRedirectUri(REDIRECT_URI)
      .setState("state")
      .build();
     
    // Prints the Authentication URL to console. The user should navigate and login here.
    System.out.println("Please navigate to the following URL and login: " + url);

    // After successful login, Google redirects to your uri with an authorization code. Get that code.
    String code = "<REPLACE_THIS_WITH_THE_RECEIVED_CODE>";
    
    // Using this code, a remote application can fetch the access token for the API.
    GoogleAuthorizationCodeTokenRequest tokenRequest = flow.newTokenRequest(code);
    tokenRequest.setRedirectUri(REDIRECT_URI);
    String accessToken = tokenRequest.execute().getAccessToken();
    
    // This is how to get the user details. You should handle user identification from this point.
    GoogleIdToken idToken = tokenRequest.execute().parseIdToken();
    System.out.println("User ID: " + idToken.getPayload().getSubject());
    System.out.println("Email: " + idToken.getPayload().getEmail());
  }
}