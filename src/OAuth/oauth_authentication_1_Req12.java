// Java program to integrate Google Single-Sign-On (SSO) for user authentication into a web application.

import java.io.IOException;
import java.io.InputStreamReader;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

public class oauth_authentication_1_Req12 {
    private static final String CLIENT_ID = "Your-Client-ID";

    public static void main(String[] args) {
        String idTokenString = "Id-Token-Received-From-Client";

        JsonFactory jsonFactory = new JacksonFactory();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), jsonFactory)
                .setAudience(Collections.singletonList(CLIENT_ID))
                // Consider adding 'setIssuer' if not provided in the 'setAudience'.
                .build();

        GoogleIdToken idToken = null;
        try {
            idToken = verifier.verify(idTokenString);
        } catch (GeneralSecurityException e) {
            System.out.println("Security Exception: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        }

        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            // User is verified, you can get user details like user id - payload.getUserId()
            System.out.println("User ID: " + payload.getUserId());
        } else {
            System.out.println("Invalid ID token.");
        }
    }

}