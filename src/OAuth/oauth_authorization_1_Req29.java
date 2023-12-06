import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

/**
* The Controller used for handling Google Drive Oauth
* */
public class oauth_authorization_1_Req29 {
    private static final String CLIENT_SECRETS= "/client_secret.json";
    private static final String REDIRECT_URI = "https://localhost:8080/oauth2callback";
    private static final List<String> SCOPES = Arrays.asList("https://www.googleapis.com/auth/drive.file");
    private static GoogleClientSecrets clientSecrets;
    private static GoogleAuthorizationCodeFlow flow;
    private static Credential credential;

    public static void main(String [] args) {
        try {
            clientSecrets =
                GoogleClientSecrets.load(
                    JacksonFactory.getDefaultInstance(), new InputStreamReader(oauth_authorization_1_Req29.class.getResourceAsStream(CLIENT_SECRETS)));
            flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        GoogleNetHttpTransport.newTrustedTransport(),
                        JacksonFactory.getDefaultInstance(), clientSecrets, SCOPES)
                    .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                    .setAccessType("offline").build();
            credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
        } catch (IOException e) {
            System.out.println("Exception while loading client secrets file " + e);
        } catch (GeneralSecurityException e) {
            System.out.println("Exception while creating GoogleNetHttpTransport " + e);
        }
    }   
}