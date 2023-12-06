import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/*
* This program enables Single Sign-On (SSO) using Google accounts for a web application.
*/
public class oauth_authentication_3_Req12 {
    private static final String CLIENT_SECRET_FILE = "path_to_client_secret.json"; // Replace with the path of your JSON secret file
    private static final List<String> SCOPES = Arrays.asList("email", "profile");
    private static final String APPLICATION_NAME = "Your-Application-Name"; // Replace with your application name
    private static final String TOKENS_DIRECTORY = "tokens_directory_path"; // Replace with your tokens directory path

    public static void main(String[] args) throws Exception {
        HttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

        // Load client secrets.
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(
                new FileInputStream(CLIENT_SECRET_FILE)));
        
        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY)))
                .setAccessType("offline")
                .build();

        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");

        // At this point, the user has been authenticated using Google SSO
        System.out.println("Successfully authenticated with Google SSO!");
    }
}