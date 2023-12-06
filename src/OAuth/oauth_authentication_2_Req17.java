/*
This Java program establishes a Single-Sign-On (SSO) service for a web application, using Google accounts for user authentication.
*/

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class oauth_authentication_2_Req17 {
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String CLIENT_SECRETS = "/client_secrets.json";

    // Replace with your redirect URI
    private static final String REDIRECT_URI = "your_redirect_uri";

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = oauth_authentication_2_Req17.class.getResourceAsStream(CLIENT_SECRETS);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CLIENT_SECRETS);
        }

        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, Collections.singletonList("https://www.googleapis.com/auth/userinfo.profile"))
                        .setRedirectUri(REDIRECT_URI)
                        .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public static void main(String... args) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        Credential credential = getCredentials(HTTP_TRANSPORT);
        
        System.out.println("User ID: " + credential.getAccessToken()); //This is a sample operation demonstrating the return of user id after successful authentication
    }
}