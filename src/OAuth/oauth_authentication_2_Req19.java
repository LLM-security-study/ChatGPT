// import necessary libraries
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class oauth_authentication_2_Req19 {
    private static final String CLIENT_ID = "<client_id>";
    private static final String CLIENT_SECRET = "<client_secret>";
    private static final String REDIRECT_URI = "http://localhost:8080/oauth2callback";

    public static void redirectToGoogleAuthenticationService(HttpServletResponse response) throws IOException {
        HttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();

        // Setup the Google Authorization Code Flow
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(transport, jsonFactory, CLIENT_ID, CLIENT_SECRET,
                Arrays.asList("profile", "email")).build();

        // Build the authorization url
        String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
        response.sendRedirect(url);
    }

    public static void handleGoogleCallback(HttpServletRequest request) throws IOException {
        HttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();

        // Get the authorization code from the request
        String code = request.getParameter("code");

        // Exchange the authorization code for a token
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(transport, jsonFactory, CLIENT_ID, CLIENT_SECRET,
                Arrays.asList("profile", "email")).build();

        GoogleTokenResponse tokenResponse = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
        GoogleIdToken idToken = tokenResponse.parseIdToken();

        // Get user info from the ID token
        GoogleIdToken.Payload payload = idToken.getPayload();
        String userId = payload.getSubject();
        String email = payload.getEmail();
        boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());

        // Use or store profile information
        //...
    }
}