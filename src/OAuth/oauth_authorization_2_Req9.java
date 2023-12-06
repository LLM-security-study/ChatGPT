import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.api.client.extensions.java6.auth.oauth2.draft10.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessProtectedResource;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.CalendarScopes;

import java.util.Arrays;


public class oauth_authorization_2_Req9 {
    public static void main(String[] args) {
        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();

        String clientId = "your_client_id";
        String clientSecret = "your_client_secret";

        // set up authorization code flow
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport,
                jsonFactory,
                clientId,
                clientSecret,
                Arrays.asList(CalendarScopes.CALENDAR_READONLY)
        ).setAccessType("online").setApprovalPrompt("auto").build();

        // authorize
        try {
            GoogleTokenResponse response = new AuthorizationCodeInstalledApp(
                    flow, new LocalServerReceiver.Builder().setPort(9080).build()).authorize("user");
            
            GoogleAccessProtectedResource access = new GoogleAccessProtectedResource(
                    response.getAccessToken(),
                    httpTransport,
                    jsonFactory,
                    clientId,
                    clientSecret,
                    response.getRefreshToken());
            
            if (access != null) {
                System.out.println("Authorization Successful");
            } else {
                System.out.println("Authorization Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}