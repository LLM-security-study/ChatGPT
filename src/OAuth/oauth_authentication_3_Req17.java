// This Java program will enable Single-Sign-On (SSO) using Google accounts for a web application.
// The primary focus is solely on implementing the authentication functionality.

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

public class oauth_authentication_3_Req17 {
    private static final String CLIENT_ID = "your Client ID";
    private static final String CLIENT_SECRET = "your Client SECRET";
    private static final String REDIRECT_URI = "your Redirect URI";

    private static GoogleAuthorizationCodeFlow flow;

    public static void main(String[] args) {
        flow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(),
                JacksonFactory.getDefaultInstance(),
                CLIENT_ID, 
                CLIENT_SECRET, 
                java.util.Collections.singleton("https://www.googleapis.com/auth/userinfo.profile"))
                .setAccessType("offline")
                .setApprovalPrompt("force")
                .build();

        // redirect to the login url.
        System.out.println(getLoginUrl());
    }

    private static String getLoginUrl() {
        final GoogleAuthorizationCodeRequestUrl url = flow.newAuthorizationUrl();
        return url.setRedirectUri(REDIRECT_URI).setAccessType("offline").build();
    }
}