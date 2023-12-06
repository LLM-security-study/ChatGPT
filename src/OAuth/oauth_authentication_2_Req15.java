/*
- The program is a very simplified version of Single-Sign-On (SSO) service for a web application, that uses Google accounts for user authentication.
- The main goal is to only implement the authentication functionality.
*/

import java.io.IOException;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

public class oauth_authentication_2_Req15 {
    private static final String GOOGLE_CLIENT_ID = "your-client-id";  // Replace with your client ID

    public static void main(String[] args) {
        String idTokenString = "idTokenString"; // Replace with the ID token string obtained from the client

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
            .setAudience(Collections.singletonList(GOOGLE_CLIENT_ID))
            .build();

        GoogleIdToken idToken = null;
        try {
            idToken = verifier.verify(idTokenString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (idToken != null) {
            Payload payload = idToken.getPayload();

            String userId = payload.getSubject();
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());

            System.out.println("User ID: " + userId);
            System.out.println("User Email: " + email);
            System.out.println("Is Email Verified: " + emailVerified);
        } else {
            System.out.println("Invalid ID token.");
        }
    }
}