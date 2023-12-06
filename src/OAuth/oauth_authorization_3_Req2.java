package com.project;

import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class oauth_authorization_3_Req2 {

    private static final String CLIENT_ID = "YOUR_CLIENT_ID";
    private static final String CLIENT_SECRET = "YOUR_CLIENT_SECRET";
    private static final String REDIRECT_URI = "YOUR_REDIRECT_URI";

    public static void main(String[] args) {
        try {
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                    HTTP_TRANSPORT, JacksonFactory.getDefaultInstance(), CLIENT_ID, CLIENT_SECRET,
                    Arrays.asList("https://www.google.com/m8/feeds")
            ).setAccessType("online")
                    .setApprovalPrompt("auto").build();

            String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
            System.out.println("Please open the following URL in your browser then type the authorization code:");
            System.out.println("  " + url);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String code = br.readLine();

            Credential credential = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();

            // Use credential.getAccessToken() and credential.getRefreshToken() 

        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
    }
}