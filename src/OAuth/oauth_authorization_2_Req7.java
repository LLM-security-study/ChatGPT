// Import relevant Google Authentication libraries
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;

public class oauth_authorization_2_Req7 {
  // Replace these values with your client id and client secret
  private static final String CLIENT_ID = "YOUR_CLIENT_ID";
  private static final String CLIENT_SECRET = "YOUR_CLIENT_SECRET";

  // File to store user credentials
  private static final java.io.File DATA_STORE_DIR =
      new java.io.File(System.getProperty("user.home"), ".credentials/google-calendar-events");

  private static GoogleClientSecrets clientSecrets = null;
  
  public static void main(String... args) throws IOException, GeneralSecurityException {
    Credential credential = authorize();
    // TODO: Use this credential object wherever required.
  }

  private static Credential authorize() throws IOException, GeneralSecurityException {
    GoogleClientSecrets.Details web = new GoogleClientSecrets.Details();
    web.setClientId(CLIENT_ID);
    web.setClientSecret(CLIENT_SECRET);
    clientSecrets = new GoogleClientSecrets().setWeb(web);

    GoogleAuthorizationCodeFlow flow = 
        new GoogleAuthorizationCodeFlow.Builder(
            GoogleNetHttpTransport.newTrustedTransport(), 
            JacksonFactory.getDefaultInstance(), 
            clientSecrets, 
            Collections.singletonList("https://www.googleapis.com/auth/calendar"))
        .setDataStoreFactory(new FileDataStoreFactory(DATA_STORE_DIR))
        .setAccessType("offline")
        .build();
        
    return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
  }
}