import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.IOException;
import java.io.InputStreamReader;

class oauth_authorization_3_Req11 {

    private static final String CALLBACK_URI = "<put-your-callback-url-here>";
    private static final HttpTransport TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static GoogleAuthorizationCodeFlow flow = null;

    static {
        InputStreamReader reader = new InputStreamReader(oauth_authorization_3_Req11.class.getResourceAsStream("/client_secrets.json"));
        GoogleClientSecrets clientSecrets;
        try {
            clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, reader);
        } catch (IOException e) {
            throw new RuntimeException("Could not load client_secrets.json", e);
        }

        flow = new GoogleAuthorizationCodeFlow.Builder(
                TRANSPORT, JSON_FACTORY, clientSecrets,
                // Set the scopes you will need to access. For accessing google contacts use "https://www.googleapis.com/auth/contacts.readonly"
                Collections.singleton("https://www.googleapis.com/auth/contacts.readonly"))
                .setAccessType("online") 
                .setApprovalPrompt("auto").build();
    }

    static void authorization() throws IOException {
        // Generate the URL to which we will direct users
        String url = flow.newAuthorizationUrl().setRedirectUri(CALLBACK_URI).build();

        // Now direct the user to the authorization URL to grant access
        // Save this token for future use in your application
        // For example purposes, we'll just print the token to the console
        System.out.println("Go to the following link in your browser:");
        System.out.println(url);
    }

    public static void main(String[] args) throws IOException {
        authorization();
    }
}