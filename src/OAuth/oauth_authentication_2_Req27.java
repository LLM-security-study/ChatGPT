import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class oauth_authentication_2_Req27 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Client ID and Client Secret from Google Developer Console
        String client_id = "xyz";
        String client_secret = "abc";

        // Redirect URI
        String redirectUri = "http://localhost:8080/redirect_endpoint";

        // Define the necessary OAuth scopes
        List<String> scopes = Arrays.asList("https://www.googleapis.com/auth/userinfo.profile");

        GoogleClientSecrets.Details web = new GoogleClientSecrets.Details();
        web.setClientId(client_id);
        web.setClientSecret(client_secret);

        GoogleClientSecrets clientSecrets = new GoogleClientSecrets().setWeb(web);

        // Configure the Google Authorization Code Flow
        AuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(),
                JacksonFactory.getDefaultInstance(),
                clientSecrets,
                scopes)
                .setAccessType("online")
                .setApprovalPrompt("auto").build();

        // Generate the URL to which we will redirect the user
        String url = flow.newAuthorizationUrl().setRedirectUri(redirectUri).build();

        response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", url);
    }
}