/*
 * This is a Java program to integrate Google Single-Sign-On for user authentication into a web application.
 * Note: The objective is to exclusively implement the authentication functionality, excluding other tasks.
 * The application has been already registered with the Google API and has obtained the necessary details.
 */

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class oauth_authentication_1_Req18 extends HttpServlet {

    // replace these with your own values
    private static final String CLIENT_SECRET_FILE = "/path/to/client/secret";
    private static final List<String> SCOPES = Arrays.asList("https://www.googleapis.com/auth/userinfo.profile");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GoogleClientSecrets secrets = GoogleClientSecrets.load(
                JacksonFactory.getDefaultInstance(),
                new InputStreamReader(oauth_authentication_1_Req18.class.getResourceAsStream(CLIENT_SECRET_FILE)));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(),
                JacksonFactory.getDefaultInstance(),
                secrets,
                SCOPES
        ).build();

        String url = flow.newAuthorizationUrl()
                .setRedirectUri("your-redirect-uri")
                .build();

        resp.sendRedirect(url);
    }
}