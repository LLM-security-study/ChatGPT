import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.InputStreamReader;

/*
This program is a web application implementing Single-Sign-On (SSO) using Google accounts for user authentication.
*/

public class oauth_authentication_2_Req12 {
    private static final String REDIRECT_URI = "[Your_redirect_uri]";
    private static final String CLIENT_ID = "[Your_Client_ID]";
    private static final String CLIENT_SECRET = "[Your_Client_Secret]";
    private static final String TOKEN_SERVER_URL = "https://oauth2.googleapis.com/token";
    private static final String AUTHORIZATION_SERVER_URL = 
    "https://accounts.google.com/o/oauth2/auth";


    public static void main(String[] args) throws Exception {
        // Create the JSON factory
        JsonFactory jsonFactory = new JacksonFactory();

        // Create an instance of GoogleClientSecrets
        GoogleClientSecrets.Details details = new GoogleClientSecrets.Details();
        details.setClientId(CLIENT_ID);
        details.setClientSecret(CLIENT_SECRET);
        details.setTokenServerEncodedUrl(TOKEN_SERVER_URL);
        details.setAuthUri(AUTHORIZATION_SERVER_URL);

        GoogleClientSecrets clientSecrets = new GoogleClientSecrets();
        clientSecrets.setWeb(details);

        // Build the GoogleAuthorizationCodeFlow object.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
            new NetHttpTransport(), jsonFactory, clientSecrets, scopes)
            .setRedirectUri(REDIRECT_URI)
            .build();

        // Start the local server to listen for the authentication response.
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8000).build();

        //callback url
        String redirectUrl = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();

        // Print the login url to the console.
        System.out.println("Please open the following URL in your browser:");
        System.out.println(redirectUrl);
    }
}