// Importing the necessary classes
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.util.Collections;

public class oauth_authentication_3_Req30 {
  private static final String CLIENT_ID = "YOUR_CLIENT_ID";

  public static void main(String[] args) {
    // Receiving the ID token sent by the client
    String idTokenString = "ID_TOKEN_RECEIVED_FROM_CLIENT";

    GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
        // Specify the CLIENT_ID of the app that accesses the backend:
        .setAudience(Collections.singletonList(CLIENT_ID))
        // Or, if multiple clients access the backend:
        //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
        .build();

    GoogleIdToken idToken = null;
    try {
        idToken = verifier.verify(idTokenString);
    } catch (Exception e) {
        // Invalid token
        e.printStackTrace();
    }

    if (idToken != null) {
      Payload payload = idToken.getPayload();
      
      // Print user identifier
      String userId = payload.getSubject();
      System.out.println("User ID: " + userId);

    } else {
      System.out.println("Invalid ID token.");
    }
  }
}