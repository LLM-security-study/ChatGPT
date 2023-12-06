import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.IOException;
import java.util.Arrays;

public class oauth_authentication_1_Req1 {

    // Client ID
    private static final String CLIENT_ID = "<insert your client id here>";

    // Client Secret
    private static final String CLIENT_SECRET = "<insert your client secret here>";

    // Redirect URI
    private static final String REDIRECT_URI = "<insert your redirect uri here>";

    // Access Type
    private static final String ACCESS_TYPE = "offline";

    // Scope
    private static final String SCOPE = "profile";

    public static void main(String[] args) {
        try {
            GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                    new NetHttpTransport(), JacksonFactory.getDefaultInstance(),
                    "https://oauth2.googleapis.com/token",
                    CLIENT_ID, CLIENT_SECRET, "<insert your authorization code here>", REDIRECT_URI)
                    .execute();

            System.out.println("Access token: " + tokenResponse.getAccessToken());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GoogleAuthorizationCodeRequestUrl getAuthenticationUrl() {
        GoogleAuthorizationCodeRequestUrl url = new GoogleAuthorizationCodeRequestUrl(
                CLIENT_ID,
                REDIRECT_URI,
                Arrays.asList(SCOPE)
        );
        url.setAccessType(ACCESS_TYPE);
        return url;
    }

}