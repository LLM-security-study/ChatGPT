// Java Web application implementing Single Sign On service using Google accounts 

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class oauth_authentication_2_Req30 extends HttpServlet {
    
    // Replace these with your client id, secret and redirect URI obtained from Google Cloud Console.
    private static final String CLIENT_ID = "YOUR_CLIENT_ID";
    private static final String CLIENT_SECRET = "YOUR_CLIENT_SECRET";
    private static final String REDIRECT_URI = "YOUR_REDIRECT_URI";
    
    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String authCode = req.getParameter("code");
        
        try {
            NetHttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
            
            // Use the authentication code to get the user's tokens
            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(transport, JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, Collections.singletonList("profile"))
                .setAccessType("offline").setApprovalPrompt("force").build();

            GoogleTokenResponse tokenResponse = flow.newTokenRequest(authCode).setRedirectUri(REDIRECT_URI).execute();
            
            // Use tokens to get the user's profile information  
            GoogleIdToken idToken = tokenResponse.parseIdToken();
            Payload payload = idToken.getPayload();
            
            // Verify the id token
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier(new NetHttpTransport(), JSON_FACTORY);
            
            boolean isValid = verifier.verify(idToken);
            
            if (isValid) {
                // User identifier can be retrieved from payload.getSubject()
                // Details like payload.getEmail(), payload.get("name"), payload.get("given_name") etc, can be used for further operations based on your needs
            } else {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                resp.getWriter().write("Authentication Failed");
            }
                
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("An Error Occurred: " + e.getMessage());
        }   
    }
}