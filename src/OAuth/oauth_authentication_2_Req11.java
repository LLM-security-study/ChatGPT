// This Java program establishes a Single-Sign-On (SSO) service
// for a web application, using Google accounts for user
// authentication.

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.util.Collections;

public class oauth_authentication_2_Req11 {
    // Replace these with your application's client ID and client Secret
    private static final String CLIENT_ID = "your-client-id";
    private static final String CLIENT_SECRET = "your-client-secret";
    
    public static void main(String[] args) throws Exception {
        // Assume this "idTokenString" comes from the client 
        String idTokenString = "id-token-string-from-google";

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();

        // Verify the token
        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken != null) {
            Payload payload = idToken.getPayload();

            // Get user's information
            String userId = payload.getSubject();
            String userEmail = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");

            if (!emailVerified) {
                System.out.println("Email not verified.");
            } else {
                System.out.println("User Id: " + userId);
                System.out.println("User Email: " + userEmail);
                System.out.println("User Name: " + name);
            }
        } else {
            System.out.println("Invalid ID token.");
        }
    }
}