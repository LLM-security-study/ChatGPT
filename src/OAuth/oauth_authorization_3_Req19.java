import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.people.v1.PeopleScopes;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class oauth_authorization_3_Req19 {
    private static final String CLIENT_SECRETS_RESOURCE = "client_secrets.json";
    private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"), ".store/people_sample");
    private static FileDataStoreFactory dataStoreFactory;
    private static HttpTransport httpTransport;
    private static final JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

    public static void main(String[] args) {
        try {
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);

             // Load client secrets.
            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory,
                    new InputStreamReader(oauth_authorization_3_Req19.class.getResourceAsStream(CLIENT_SECRETS_RESOURCE)));

            // Set up authorization code flow.
            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                    httpTransport, jsonFactory, clientSecrets,
                    Arrays.asList(PeopleScopes.CONTACTS_READONLY)).setDataStoreFactory(
                    dataStoreFactory).build();
            Credential credential = authorize(flow);

            // You can now use credential.getAccessToken() and credential.getRefreshToken() 
            // to access the protected resources on the user's behalf.
            
            // Rest of your code 

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Credential authorize(GoogleAuthorizationCodeFlow flow) throws IOException {
        // authorize and persist the user's credentials.
        // Replace USER_IDENTIFIER_KEY with a unique key to identify the user,
        // for example the user's ID or email address.
        String userIdentifierKey = "USER_IDENTIFIER_KEY";
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver())
                .authorize(userIdentifierKey);
    }
}