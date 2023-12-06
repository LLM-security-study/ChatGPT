import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

public class oauth_authentication_1_Req6 {
    private static final String CLIENT_ID = "your Google client_id";
    
    public static void main(String[] args) {
        String idTokenString = "token obtained from user sign-in";
        
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
            .setAudience(Collections.singletonList(CLIENT_ID))
            .build();

        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");

            // Use or store profile information
            // ...

        } else {
            System.out.println("Invalid ID token.");
        }
    }
}