import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.util.Collections;

public class oauth_authentication_2_Req23 {

  public static void main(String[] args) {
    String clientId = "Your-Client-ID";  // Replace with your client ID
    String idTokenString = "idTokenString";  // Replace with the token obtained from the Google OAuth process
    GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
        .setAudience(Collections.singletonList(clientId))
        .build();

    GoogleIdToken idToken = null;
    try {
      idToken = verifier.verify(idTokenString);
      if (idToken != null) {
        System.out.println("Successfully authenticated user.");
        // Add your own code to handle the user's sign in.
      } else {
        System.out.println("Unable to authenticate user.");
      }
    } catch (Exception e) {
      // Handle the exception as you see fit
    }
  }
}