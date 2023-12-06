/* 
This Java program enables Single Sign-On (SSO) using Google accounts for a web application.
This focuses solely on implementing the authentication functionality.
*/

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

public class oauth_authentication_3_Req22 {

    private static final String CLIENT_ID = "CLIENT_ID";
    private static final String CLIENT_SECRET = "CLIENT_SECRET";
    private static final String REDIRECT_URI = "REDIRECT_URI";
    
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private static final List<String> SCOPES = Collections.singletonList("https://www.googleapis.com/auth/userinfo.profile");

    public static void main(String... args) throws Exception {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, SCOPES)
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential newCredential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

        GoogleIdToken idToken = newCredential.getAccessToken();

        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            System.out.println("User ID: " + payload.getSubject());
        } else {
            System.out.println("Invalid ID token.");
        }
    }
}