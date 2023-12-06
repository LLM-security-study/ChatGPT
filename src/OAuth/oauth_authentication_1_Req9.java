// This is a Java program to integrate Google Single-Sign-On for user authentication into a web application.
// The purpose of this program is to focus exclusively on implementation of the authentication function.
// Requirements such as designing of the user interface will not be covered.

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;

public class oauth_authentication_1_Req9 {

    private static final String REDIRECT_URI = "your_redirect_url";
    private static final String CLIENT_ID = "your_client_id";
    private static final String CLIENT_SECRET = "your_client_secret";
    private static final String USER_ID = "user_id";

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        GoogleClientSecrets clientSecrets =
        GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(new FileInputStream("client_secrets.json")));
        
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets,
        Collections.singletonList("https://www.googleapis.com/auth/userinfo.profile"))
        .setAccessType("offline")
        .setApprovalPrompt("force")
        .build();

        Credential credential = flow.loadCredential(USER_ID);
        if (credential != null && credential.getAccessToken() != null) {
        System.out.println("You are authenticated!");
        } else {
        System.out.println("You are not authenticated!");
        }
    }
}