// Import necessary libraries
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// oauth_authentication_2_Req29 class
public class oauth_authentication_2_Req29 extends HttpServlet {

    // Initialize necessary attributes
    static final String CLIENT_ID = "<your-client-id>";
    static final String CLIENT_SECRET = "<your-client-secret>";
    static final String REDIRECT_URI = "<your-redirect-uri>";

    static final NetHttpTransport TRANSPORT = new NetHttpTransport();
    static final GsonFactory JSON_FACTORY = new GsonFactory();

    AuthorizationCodeFlow flow; 

    // Initialize the Authorization Code Flow in the Servlet's init method
    @Override
    public void init() throws ServletException {
        flow = new AuthorizationCodeFlow.Builder(
            TRANSPORT, JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, 
            Collections.singleton("https://www.googleapis.com/auth/userinfo.profile")
            )
            .setAccessType("offline")
            .build();
    }

    // GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        if (code != null){
            // Get the response after user successfully signs in
            AuthorizationCodeResponseUrl responseUrl = new AuthorizationCodeResponseUrl(req.getQueryString());
            String authorizationCode = responseUrl.getCode(); 

            if (responseUrl.getError() != null){
                // User denied access
            } else if (authorizationCode != null){
                // User granted access
                String redirectUri = req.getRequestURL().toString();
                Credential credential = flow.newTokenRequest(authorizationCode).setRedirectUri(redirectUri).execute();
                // Now you can use credential.getAccessToken() and credential.getRefreshToken() to authenticate requests to Google Services
            } 
        } else {
            // Redirect the user to the authorization page
            GenericUrl url = flow.newAuthorizationUrl();
            url.setRedirectUri(REDIRECT_URI);
            url.set("access_type", "offline");  
            url.set("prompt", "consent"); 
            resp.sendRedirect(url.toString());
        }
    }
}