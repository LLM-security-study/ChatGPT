// Java program to integrate Google Sign-On for user authentication into a web application

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

public class oauth_authentication_1_Req24 extends HttpServlet{

    private static final String CLIENT_SECRET_FILE = "/path/to/your/client_secret.json";
    private static final Collection<String> SCOPES = Arrays.asList("email", "profile");
    private static final String CALLBACK_URI = "http://localhost:8080/oauth2callback";

    private GoogleAuthorizationCodeFlow initializeFlow() throws IOException {
        return new GoogleAuthorizationCodeFlow.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(),
                GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), 
                        new FileReader(CLIENT_SECRET_FILE)), SCOPES)
                .setAccessType("online")
                .setApprovalPrompt("auto").build();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Credential credential = initializeFlow().loadCredential("user");
        if (credential != null && credential.getAccessToken() != null) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().print("User is authenticated!");
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().print("User is not authenticated!");
        }
    }
}