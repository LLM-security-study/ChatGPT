import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

public class oauth_authentication_3_Req19 extends HttpServlet {

    // client id, client secret and redirect uri should come from your application registration
    private final String clientId = "yourClientId";
    private final String clientSecret = "yourClientSecret";
    private final String redirectUri = "yourRegisteredRedirectUri";

    private GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
            new NetHttpTransport(),
            JacksonFactory.getDefaultInstance(),
            clientId,
            clientSecret,
            java.util.Arrays.asList("email", "profile")
    ).setAccessType("online")
     .setApprovalPrompt("auto").build();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String code = request.getParameter("code");

        if(code != null){
            try{
                GoogleTokenResponse tokenResponse = flow
                        .newTokenRequest(code)
                        .setRedirectUri(redirectUri)
                        .execute();

                String accessToken = tokenResponse.getAccessToken();
                // Now that you have the token, you can set it in your session or something.
                System.out.println("Token: " + accessToken);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            // Redirect the user to the authorization page
            String url = flow.newAuthorizationUrl().setRedirectUri(redirectUri).build();
            response.sendRedirect(url);
        }
    }    
}