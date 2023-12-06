/*
Program to Implement Google SSO for a web application
*/

package com.myapp.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.util.Collections;

public class oauth_authentication_3_Req24 {
    
    static final String CLIENT_ID = "Your Google Client ID here";
    
    public static void main(String[] args) {
        String idTokenString = "Token received from the client";
        
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
            new NetHttpTransport(), JacksonFactory.getDefaultInstance())
            .setAudience(Collections.singletonList(CLIENT_ID))
            .build();

        try {
            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();

                // Print user identifier 
                String userId = payload.getSubject();
                System.out.println("User ID: " + userId);

                // Get profile information from payload
                String email = payload.getEmail();
                boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
                String name = (String) payload.get("name");

                // Use or store profile information
                // ...

            } else {
                System.out.println("Invalid ID token.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}