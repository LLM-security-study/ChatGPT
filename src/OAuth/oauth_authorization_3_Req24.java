import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.util.store.FileDataStoreFactory;

import java.io.InputStreamReader;
import java.util.Collections;

public class oauth_authorization_3_Req24 {
    private static final String APPLICATION_NAME = "Social Networking Application";
    private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"), ".store/contacts_sample");
    private static FileDataStoreFactory dataStoreFactory;

    public static void main(String[] args) {
        try {
            Credential credential = authorize();
            //continue with application logic
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Credential authorize() throws Exception {
        // Load client secrets.
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(oauth_authorization_3_Req24.class.getResourceAsStream("/client_secrets.json")));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets,
                Collections.singleton("https://www.googleapis.com/auth/contacts.readonly"))
                .setDataStoreFactory(dataStoreFactory)
                .setAccessType("offline")
                .build();

        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
        System.out.println("Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }
}