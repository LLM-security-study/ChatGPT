import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;

public class oauth_authorization_1_Req14 {
    
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private static final String CLIENT_ID = "";    // Add your Client ID
    private static final String CLIENT_SECRET = "";   // Add your Client Secret
    private static final String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, Arrays.asList("https://www.googleapis.com/auth/drive.file"))
                        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                        .setAccessType("offline")
                        .build();
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }

    public static void main(String[] args) throws IOException {
        final NetHttpTransport HTTP_TRANSPORT = new com.google.api.client.http.javanet.NetHttpTransport();
        Credential credential = getCredentials(HTTP_TRANSPORT);
        System.out.println("Authorization was successful");
    }
}