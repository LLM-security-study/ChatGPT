import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.util.Collections;

public class oauth_authentication_1_Req29 {

    private static final String GOOGLE_CLIENT_ID = "YOUR_CLIENT_ID";

    public static void main(String[] args) {
        
        String idTokenString = "TOKEN_FROM_FRONT_END";
        
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
            // Specify the CLIENT_ID of the app that accesses the backend:
            .setAudience(Collections.singletonList(GOOGLE_CLIENT_ID))
            // Or, if multiple clients access the backend:
            //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
            .build();

        try {
            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken != null) {
                Payload payload = idToken.getPayload();

                // Get profile information from payload
                String userId = payload.getSubject();
                String email = payload.getEmail();
                boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
                String name = (String) payload.get("name");
                String pictureUrl = (String) payload.get("picture");
                String locale = (String) payload.get("locale");

                System.out.println("User ID: " + userId);
                System.out.println("Email: " + email);
                System.out.println("Email verified: " + emailVerified);
                System.out.println("Name: " + name);
                System.out.println("Picture Url: " + pictureUrl);
                System.out.println("Locale: " + locale);

            } else {
                System.out.println("Invalid ID token.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}