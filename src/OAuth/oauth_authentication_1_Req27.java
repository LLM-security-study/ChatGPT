import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class oauth_authentication_1_Req27 {
    private static final String CLIENT_ID = "YOUR_CLIENT_ID";
    private static final String CLIENT_SECRET = "YOUR_CLIENT_SECRET";
    private static final String REDIRECT_URI = "YOUR_REDIRECT_URI";

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
            new InputStreamReader(oauth_authentication_1_Req27.class.getResourceAsStream("/client_secrets.json")));
        
        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
            httpTransport, JSON_FACTORY, clientSecrets, Collections.singleton("https://www.googleapis.com/auth/userinfo.profile"))
            .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
            .setAccessType("offline")
            .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, JSON_FACTORY)
            .setAudience(Collections.singletonList(CLIENT_ID))
            .build();

        TokenResponse tokenResponse = flow.newTokenRequest(credential.getRefreshToken()).setRedirectUri(REDIRECT_URI).execute();

        String idTokenString = tokenResponse.getIdToken();

        Payload payload = verifier.verify(idTokenString).getPayload();
        
        // Get profile information from payload
        String userId = payload.getSubject();
        String email = payload.getEmail();
        boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
        String name = (String) payload.get("name");
        String pictureUrl = (String) payload.get("picture");
        String locale = (String) payload.get("locale");
                
    }
}