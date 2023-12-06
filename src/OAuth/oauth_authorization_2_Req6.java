import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;

public class oauth_authorization_2_Req6 {

    private static final String APPLICATION_NAME = "Google Calendar API Java Client";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	
    // Enter your own Client ID and Secret here
    private static final String CLIENT_ID = "Your Client ID here";
    private static final String CLIENT_SECRET = "Your Client Secret here";

    private static final java.io.File DATA_STORE_DIR = new java.io.File(
        System.getProperty("user.home"), ".credentials/" + APPLICATION_NAME);

    private static FileDataStoreFactory DATA_STORE_FACTORY;

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        GoogleClientSecrets.Details web = new GoogleClientSecrets.Details();
        web.setClientId(CLIENT_ID);
        web.setClientSecret(CLIENT_SECRET);
        
        GoogleClientSecrets clientSecrets = new GoogleClientSecrets().setWeb(web);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
            GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, clientSecrets,
            Collections.singleton("https://www.googleapis.com/auth/calendar"))
            .setDataStoreFactory(DATA_STORE_FACTORY)
            .setAccessType("offline")
            .build();
        
        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");

        System.out.println("Credentials saved to: " + DATA_STORE_DIR.getAbsolutePath());
    }

    static {
        try {
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }
}