import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class oauth_authorization_1_Req20 {
    private static final String APPLICATION_NAME = "Google Drive API Java Application";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    private static final String CLIENT_SECRETS = "/client_secret.json";
    private static final String CREDENTIALS_STORE = "credentialDatastore";
    private static final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

    public oauth_authorization_1_Req20() throws GeneralSecurityException, IOException {
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new FileReader(CLIENT_SECRETS));
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets,
                Collections.singletonList(DriveScopes.DRIVE_FILE))
                .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
       Credential credential = flow.loadCredential(USER_IDENTIFIER_KEY);
       if (credential != null &&
                ((credential.getRefreshToken() != null) ||
                        (credential.getExpiresInSeconds() != null && credential.getExpiresInSeconds() > 60))) {
            return credential;
        } else {
           // if the above condition is not met, then you need to receive  new authorization code from the user.
        }
    }

    public static void main(String... args) throws IOException, GeneralSecurityException {
        // Build flow and trigger user authorization request.
        new oauth_authorization_1_Req20();
    }
}