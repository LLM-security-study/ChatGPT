// This Java program is used to establish a Single-Sign-On (SSO) service for a web application 
// It uses Google accounts for user authentication.
// The application has been already registered with the Google service provider 
// and the necessary information (e.g., redirect URI, client ID, client secret) has been obtained.

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class oauth_authentication_2_Req20 extends HttpServlet {
    // Client ID
    private static final String CLIENT_ID = "Replace this with your client id";
    
    // Client Secret
    private static final String CLIENT_SECRET = "Replace this with your client secret";

    // Redirect URI
    private static final String REDIRECT_URI = "Replace this with your redirect URI";

    // Access scope
    private static final String SCOPE = "email profile openid";

    // Authorization Endpoint
    private static final String AUTHORIZATION_ENDPOINT =
            "https://accounts.google.com/o/oauth2/v2/auth";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
	    String authUrl = AUTHORIZATION_ENDPOINT 
    						+ "?client_id=" + CLIENT_ID 
    						+ "&response_type=code"
    						+ "&scope=" + SCOPE
							+ "&redirect_uri=" + REDIRECT_URI
							+ "&state=thiscanbeanything";

        resp.sendRedirect(authUrl);
    }
}