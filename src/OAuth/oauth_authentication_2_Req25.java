/**
 * The main class to establish a Single-Sign-On service for a web application.
 * It uses Google accounts for user authentication via Google OAuth2 service.
 */
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

class oauth_authentication_2_Req25 {

    private static final String CLIENT_ID = "<YOUR_CLIENT_ID>";
    private static final List<String> SCOPES = Arrays.asList("https://www.googleapis.com/auth/userinfo.profile"); //Specify the scope
    private static final String CALLBACK_URI = "<YOUR_CALLBACK_URI>";
    
    private static GoogleAuthorizationCodeFlow flow;

    public static void main(String arg[]) {
        flow = new GoogleAuthorizationCodeFlow.Builder(new NetHttpTransport(),
                                                        JacksonFactory.getDefaultInstance(),
                                                        CLIENT_ID,
                                                        "<YOUR_CLIENT_SECRET>",
                                                        SCOPES)
                                                        .setAccessType("online") 
                                                        .setApprovalPrompt("auto").build();
        // Redirect user to login and authorize
        GoogleAuthorizationCodeRequestUrl url = flow.newAuthorizationUrl();
        url.setRedirectUri(CALLBACK_URI);
        String authorize_url = url.build();
        // open in your browser the authorize_url
    }
}