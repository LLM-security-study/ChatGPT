// This project aims to set up the authentication function of a web application 
// using Google Single Sign On (SSO) service.

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.util.Collections;

public class oauth_authentication_2_Req28 {

    private static final String CLIENT_ID = "your-google-client-id";
    private static final NetHttpTransport TRANSPORT = new NetHttpTransport();

    public static void main(String[] args) {
        String idTokenString = "id-token-string-obtained-from-client";

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(TRANSPORT, new JacksonFactory())
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();

        try {
            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();
                System.out.println("User ID: " + payload.getSubject());
            } else {
                System.out.println("Invalid ID token.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}