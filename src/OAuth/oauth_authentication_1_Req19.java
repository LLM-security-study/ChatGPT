   // Import necessary Google libraries
   import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
   import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
   import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
   import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
   import com.google.api.client.http.javanet.NetHttpTransport;
   import com.google.api.client.json.jackson2.JacksonFactory;

   import java.io.IOException;
   import java.io.InputStreamReader;
   import java.util.Collections;

	// Get the necessary credentials from Google Cloud Console
	public static final String CLIENT_ID = "xxxxxxxxx Client ID xxxxxxxxx";
	public static final String CLIENT_SECRET = "xxxx Client Secret xxxxx";
	public static final String REDIRECT_URI = "xxxx Redirect URI xxxxx";
   
    public class oauth_authentication_1_Req19 {

        private static final NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport();
        private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
        // Begin with the Google Authorization Code Flow
        private static GoogleAuthorizationCodeFlow flow = null;
       
        public static void main(String[] args) throws IOException {
            
            // Load client secrets
            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
                new InputStreamReader(oauth_authentication_1_Req19.class.getResourceAsStream("/client_secrets.json")));
    
            // Build flow and trigger user authorization request.
            flow = new GoogleAuthorizationCodeFlow.Builder(
                    HTTP_TRANSPORT, JSON_FACTORY, clientSecrets,
                    Collections.singleton("openid"))
                .setAccessType("online")
                .setApprovalPrompt("auto").build();
    
            String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
            // At this point, we'll redirect our user to the url
            // From there, Google will redirect the user back to REDIRECT_URI with a "code" parameter
            
            // This "code" param can be exchanged for an access token
            String code = "xxx Code Returned by Google xxx";
            GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
            
            // From the response, we can retrieve the user's ID Token and get their info
            String userId = response.parseIdToken().getPayload().getSubject();
            System.out.println("Logged in user: " + userId);
        }
    }
    