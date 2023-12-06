/*
This is the main class for integrating Google Single-Sign-On for user authentication into a web application.
*/

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class oauth_authentication_1_Req4 extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String CLIENT_ID = "<replace-with-your-client-id>";
  private static final String CLIENT_SECRET = "<replace-with-your-client-secret>";
  private static final String REDIRECT_URI = "<replace-with-your-redirect-uri>";

  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    String authCode = request.getParameter("code");
    GoogleTokenResponse tokenResponse =
        new GoogleAuthorizationCodeTokenRequest(
            new NetHttpTransport(),
            JacksonFactory.getDefaultInstance(),
            "https://oauth2.googleapis.com/token",
            CLIENT_ID,
            CLIENT_SECRET,
            authCode,
            REDIRECT_URI)  
        .execute();

    String accessToken = tokenResponse.getAccessToken();

    final Credential credential = new Credential(BearerToken.authorizationHeaderAccessMethod())
        .setAccessToken(accessToken);
    GoogleIdToken idToken = tokenResponse.parseIdToken();
    String email = idToken.getPayload().getEmail();

    response.setContentType("text/html");
    response.getWriter().print("Hello, " + email + "!");
  }
}