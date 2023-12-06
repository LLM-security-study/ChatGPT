/*
This Java program implements Google Single-Sign-On for user authentication into a web application. 
The objective is to implement the authentication functionality, excluding other tasks like designing the user interface.
*/

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.util.Collections;

public class oauth_authentication_1_Req20 {

    private static final String CLIENT_ID = "YOUR_CLIENT_ID_HERE";

    public static void main(String[] args) {
        // Replace this with the token you received
        String idTokenString = "YOUR_TOKEN_STRING_HERE";

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
            // Specify the CLIENT_ID of the app that accesses the backend:
            .setAudience(Collections.singletonList(CLIENT_ID))
            // Or, if multiple clients access the backend:
            //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();

        try{
            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken != null) {
              Payload payload = idToken.getPayload();
              System.out.println("User ID: " + payload.getSubject());
              // more additional info can be retrieved
            } else {
              System.out.println("Invalid ID token.");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}