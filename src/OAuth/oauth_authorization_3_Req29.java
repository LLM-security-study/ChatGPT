import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Reader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

public class oauth_authorization_3_Req29 {
    private static final String APPLICATION_NAME = "Social Networking Application";
    private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"), ".credentials/" + APPLICATION_NAME);

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    
    // This would typically include .profile and .email
    // In this case, we just need access to the contacts
    private static final List<String> SCOPES = Arrays.asList("https://www.googleapis.com/auth/contacts.readonly");
    
    public static void main(String[] args) throws IOException, GeneralSecurityException {
        // Load client secrets.
        Reader clientSecretReader = new InputStreamReader(oauth_authorization_3_Req29.class.getResourceAsStream("/client_secret.json"));
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, clientSecretReader);

        // Build the local server and bind the port.
        LocalServerReceiver localReceiver = new LocalServerReceiver.Builder().setPort(9000).build();

        // Start the authorization process.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                clientSecrets,
                SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(DATA_STORE_DIR))
                .setAccessType("offline")
                .build();

        new AuthorizationCodeInstalledApp(flow, localReceiver).authorize("user");
    }
}
