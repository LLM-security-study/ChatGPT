// Importing necessary classes
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;

import java.io.FileReader;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class oauth_authentication_3_Req10 {

    private static final String CLIENT_SECRET_FILE = "/path/to/client_secret.json";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    public static void main(String... args) throws IOException, GeneralSecurityException {
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        // Load client secrets.
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new FileReader(CLIENT_SECRET_FILE));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JSON_FACTORY, clientSecrets, Collections.singleton("https://www.googleapis.com/auth/userinfo.profile"))
                .setAccessType("offline")
                .setApprovalPrompt("force")
                .build();

        //Replace with your actual redirect URI
        String redirectUri = "https://localhost:8080";
        String url = flow.newAuthorizationUrl().setRedirectUri(redirectUri).build();
        System.out.println("Please open the following URL in your browser then type the authorization code:");
        System.out.println("  " + url);

        // Read code entered by user.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String code = br.readLine();

        // Generate Credential using retrieved code.
        TokenResponse response = flow.newTokenRequest(code).setRedirectUri(redirectUri).execute();
        Credential credential = flow.createAndStoreCredential(response, "userID");

        // Fetch Access Token
        String accessToken = credential.getAccessToken();

        // Fetch Refresh Token
        String refreshToken = credential.getRefreshToken();

        // Print tokens
        System.out.println("Access Token: " + accessToken + ", Refresh Token: " + refreshToken);  
    }
}