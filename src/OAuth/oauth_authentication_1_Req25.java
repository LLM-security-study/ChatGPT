// Import necessary packages
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.MemoryDataStoreFactory;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Collection;

public class oauth_authentication_1_Req25 {

    private static final String CLIENT_ID = "YOUR_CLIENT_ID";
    private static final String CLIENT_SECRET = "YOUR_CLIENT_SECRET";
    private static final String REDIRECT_URI = "YOUR_REDIRECT_URI";
    private static final Collection<String> SCOPES = Arrays.asList("openid", "profile", "email");

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static NetHttpTransport httpTransport;

    public static void main(String args[]) throws Exception {
        httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        // Load client secrets
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, 
                new Reader() {
                    public int read(char[] cbuf, int off, int len) {
                        return 0;
                    }
                    public void close() {
                    }
                });

        // Build flow and trigger user authorization request
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new MemoryDataStoreFactory())
                .setAccessType("offline")
                .build();

        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
        System.out.println("Credentials saved to " + "tokens.json");
    }
}