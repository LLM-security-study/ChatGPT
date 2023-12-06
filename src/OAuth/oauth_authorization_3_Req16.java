import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessProtectedResource;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessTokenRequest.GoogleAuthorizationCodeGrant;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAuthorizationRequestUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class oauth_authorization_3_Req16 {
    private static final String CLIENT_ID = "YOUR_CLIENT_ID";
    private static final String CLIENT_SECRET = "YOUR_CLIENT_SECRET";
    private static final String REDIRECT_URI = "YOUR_REDIRECT_URI";

    public static void main(String[] args) throws IOException {
        String authUrl = new GoogleAuthorizationRequestUrl(CLIENT_ID, REDIRECT_URI, "https://www.google.com/m8/feeds/").build();

        System.out.println("Please open the following URL in your browser then type the authorization code:");
        System.out.println("  " + authUrl);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String code = br.readLine();

        AccessTokenResponse response = new GoogleAuthorizationCodeGrant(new NetHttpTransport(),
                new JacksonFactory(),
                CLIENT_ID,
                CLIENT_SECRET,
                code,
                REDIRECT_URI).execute();

        GoogleAccessProtectedResource access = new GoogleAccessProtectedResource(response.accessToken,
                new NetHttpTransport(),
                new JacksonFactory(),
                CLIENT_ID,
                CLIENT_SECRET,
                response.refreshToken);

        // The access object can now be used to initiate contact with Google APIs.
    }
}