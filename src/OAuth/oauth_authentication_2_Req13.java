/*
   This program is developed to establish a Single-Sign-On (SSO) service for a web application, 
   using Google accounts for user authentication. 
   The primary goal is to only implement the authentication functionality. 
*/

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class oauth_authentication_2_Req13 {

    private static final String REDIRECT_URI = "insert your redirect URI here";
    private static final String CLIENT_ID = "insert your client ID here";
    private static final String CLIENT_SECRET = "insert your client secret here";

    public static void main(String[] args) throws IOException {

        GoogleClientSecrets.Details web = new GoogleClientSecrets.Details();
        web.setClientId(CLIENT_ID);
        web.setClientSecret(CLIENT_SECRET);
        web.setRedirectUris(Arrays.asList(REDIRECT_URI));

        GoogleClientSecrets clientSecrets = new GoogleClientSecrets().setWeb(web);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(),
                JacksonFactory.getDefaultInstance(),
                clientSecrets,
                Arrays.asList("insert your scope here")
        ).build();

        new AuthorizationCodeInstalledApp(
            flow,
            new LocalServerReceiver()
        ).authorize("user");

      }
}