// Required dependencies
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver.Builder;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.DriveScopes;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class oauth_authorization_1_Req8{
    // Specify the access scope
    private static final List<String> SCOPES = Arrays.asList(DriveScopes.DRIVE_FILE);

    //  Directory to store user credentials
    private static final java.io.File CREDENTIALS_FOLDER = new java.io.File(System.getProperty("user.home"), ".credentials/java-google-drive-authorization");

    private static  final String CLIENT_SECRET_FILE_NAME = "client_secret.json"; 

    public static void main(String[] args) {
        requestAuthorization();
    }

    private static Credential requestAuthorization() {
        try {
            // Building new HTTP Transport
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

            // Loading client secrets
            InputStream in = oauth_authorization_1_Req8.class.getResourceAsStream(CLIENT_SECRET_FILE_NAME);
            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                    JacksonFactory.getDefaultInstance(), new InputStreamReader(in));

            // Creating flow and triggering user authorization request
            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                    HTTP_TRANSPORT, JacksonFactory.getDefaultInstance(), clientSecrets, SCOPES)
                    .setDataStoreFactory(new FileDataStoreFactory(CREDENTIALS_FOLDER))
                    .setAccessType("offline")
                    .build();
            return new AuthorizationCodeInstalledApp(flow, new Builder().setPort(8888).build()).authorize("user");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}