// Import Google OAuth Client Library
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

public class oauth_authentication_3_Req8 {
    private static final String REDIRECT_URI = "your-redirect-uri";
    private static final String CLIENT_ID = "your-client-id";
    private static final String CLIENT_SECRET ="your-client-secret";
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList("https://www.googleapis.com/auth/userinfo.profile");

    // Method to initiate authentication request
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        GoogleClientSecrets.Details web = new GoogleClientSecrets.Details();
        web.setClientId(CLIENT_ID);
        web.setClientSecret(CLIENT_SECRET);
        GoogleClientSecrets clientSecrets = new GoogleClientSecrets().setWeb(web);

        // Use the Google Authorization Code Flow
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public static void main(String... args) throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Credential credential = getCredentials(HTTP_TRANSPORT);
        // Print the credential details for the signing-in user
        System.out.println("User ID: " + credential.getUserId());
    }
}