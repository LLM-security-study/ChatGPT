import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.util.store.FileDataStoreFactory;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.security.GeneralSecurityException;

public class oauth_authorization_3_Req13 {

    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Create a Credential instance from stored Token info.
     * @return Authorized Credential instance.
     * @throws IOException If there was an error getting the client ID secret.
     */
    private static Credential getCredentials() throws IOException, GeneralSecurityException {
        final Reader clientSecretReader = new FileReader("client_secret.json");
        final GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                com.google.api.client.json.jackson2.JacksonFactory.getDefaultInstance(), clientSecretReader);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                com.google.api.client.googleapis.javanet.GoogleNetHttpTransport.newTrustedTransport(),
                com.google.api.client.json.jackson2.JacksonFactory.getDefaultInstance(),
                clientSecrets,
                com.google.api.services.people.v1.PeopleServiceScopes.CONTACTS_READONLY)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();

        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }

    public static void main(String... args) {
        try {
            Credential credential = getCredentials();
            if (credential == null) {
                System.out.println("Failed to get authorization");
            } else {
                System.out.println("Successfully authorized");
            }
        } catch (IOException | GeneralSecurityException e) {
            System.out.println("Failed to get authorization: " + e.getMessage());
        }
    }
}