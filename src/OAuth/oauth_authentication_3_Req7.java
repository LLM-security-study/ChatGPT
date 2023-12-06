import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

/*
 * This java program will demonstrate the authentication
 * functionality of Single-Sign-On (SSO) using Google accounts
 * for a web application.
 */
public class oauth_authentication_3_Req7 {
    private static final String CLIENT_ID = "<Enter your client ID>";
    
    // Receives the ID Token from client
    public void authenticate(String idTokenString) throws GeneralSecurityException, IOException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
            // Specify the CLIENT_ID of the app that accesses the backend:
  	        .setAudience(Collections.singletonList(CLIENT_ID))
            // Or, if multiple clients access the backend:
            //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
            .build();

        // Verify the idToken
        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken != null) {
            Payload payload = idToken.getPayload();
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);
            // You can also access the user's email
            String email = payload.getEmail();
            System.out.println("User email: " + email);
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            if (emailVerified) { 
                System.out.println("Email verified: " + emailVerified);
            }
        } else {
            System.out.println("Invalid ID token.");
        }
    }

    public static void main(String []args) throws GeneralSecurityException, IOException{
        oauth_authentication_3_Req7 googleSSO=new oauth_authentication_3_Req7();
        // Replace this string with the actual token passed from client
        String realToken="<Enter the real token>";
        googleSSO.authenticate(realToken);
    }
}