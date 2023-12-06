/*
* This program implements Single Sign-On (SSO) using Google accounts for a web application.
* The primary focus is on implementing the authentication functionality.
* Application has been already registered with the service provider and necessary information 
* (e.g., redirect URI, client ID, client secret) has been obtained.
*/

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.FileReader;
import java.io.IOException;

public class oauth_authentication_3_Req29 extends HttpServlet {

  private static GoogleAuthorizationCodeFlow flow;
  private static final String CLIENT_SECRET_FILE = "/path/to/client_secret.json";
  
  static {
      try {
          GoogleClientSecrets secrets = GoogleClientSecrets.load(
                  JacksonFactory.getDefaultInstance(), new FileReader(CLIENT_SECRET_FILE));
          flow = new GoogleAuthorizationCodeFlow.Builder(
                  new NetHttpTransport(), JacksonFactory.getDefaultInstance(), secrets, 
                  Collections.singletonList("https://www.googleapis.com/auth/userinfo.profile"))
                  .setAccessType("offline").setApprovalPrompt("force").build();
      } catch (IOException e) {
          throw new RuntimeException(e);
      }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws IOException {
    String code = request.getParameter("code");
    if (code != null) {
      Credential credential = flow.newTokenRequest(code).setRedirectUri("YOUR_REDIRECT_URI")
        .execute();
      request.getSession().setAttribute("google-credential", credential);
      response.sendRedirect("YOUR_SUCCESS_LANDING_PAGE");
      return;
    }
    
    response.sendRedirect(flow.newAuthorizationUrl().setRedirectUri("YOUR_REDIRECT_URI")
      .build());
  }
}