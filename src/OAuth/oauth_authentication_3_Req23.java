import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
* This is a simple Java program to enable Single-Sign-On (SSO) 
* using Google accounts for a web application.
*/
public class oauth_authentication_3_Req23 {
    private static final String CLIENT_SECRET = "/client_secret.json";
    private static final String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";
    private static GoogleClientSecrets clientSecrets;
    private static HttpTransport httpTransport;
    private static FileDataStoreFactory dataStoreFactory;

    public static void main(String[] args) {
        try {
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            dataStoreFactory = new FileDataStoreFactory(new java.io.File(System.getProperty("user.home"), ".store/SSO"));

            clientSecrets = GoogleClientSecrets.load(
                JacksonFactory.getDefaultInstance(), new InputStreamReader(oauth_authentication_3_Req23.class.getResourceAsStream(CLIENT_SECRET)));

            AuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                    httpTransport, JacksonFactory.getDefaultInstance(), clientSecrets,
                    Arrays.asList("profile", "email"))
                    .setDataStoreFactory(dataStoreFactory).build();

            LocalServerReceiver receiver = new LocalServerReceiver.Builder().setHost("localhost").setPort(8181).build();
            Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}