import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.util.Collections;

/*
 * A Java program to establish a Single-Sign-On (SSO) service for a web application,
 * using Google accounts for user authentication. The primary goal is to only implement
 * the authentication functionality, and other operations, such as designing user interface,
 * are not within the scope of this task.
 */
public class oauth_authentication_2_Req10 {
  
  public static boolean authenticate(String idTokenString, String clientID) {
    GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
        .setAudience(Collections.singletonList(clientID))
        // If you retrieved the token on Android using the Play Services 8.3 API or newer, set
        // the issuer to "https://accounts.google.com". Otherwise, set the issuer to
        // "accounts.google.com". If you need to verify tokens from multiple sources, build
        // a GoogleIdTokenVerifier for each issuer and try them both.
        .setIssuer("https://accounts.google.com")
        .build();

    GoogleIdToken idToken = null;
    try {
      idToken = verifier.verify(idTokenString);
      if (idToken != null) {
        Payload payload = idToken.getPayload();

        System.out.println("User ID: " + payload.getSubject());  // Use this value as a key to identify a user. Do not use userName/email without hashing for privacy.
        return true;
      } else {
        System.out.println("Invalid ID token.");
        return false;
      }
    } catch (Exception e) {
      // This is often because the Audience (client-id) claim does not match the client-id
      // in the ID token's header.
      e.printStackTrace();
      return false;
    }
  }
}