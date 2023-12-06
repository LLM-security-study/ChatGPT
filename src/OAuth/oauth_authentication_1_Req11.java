import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.InputStreamReader;
import java.util.Collections;

public class oauth_authentication_1_Req11 {
 public static void main(String[] args) throws Exception {

 GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
      JacksonFactory.getDefaultInstance(),
      new InputStreamReader(oauth_authentication_1_Req11.class.getResourceAsStream("/client_secrets.json")));
 
  GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
      new NetHttpTransport(),
      JacksonFactory.getDefaultInstance(), 
      clientSecrets, 
      Collections.singleton("https://www.googleapis.com/auth/userinfo.profile"))
      .setAccessType("offline") 
      .setApprovalPrompt("auto").build();

  String authorizationUrl = flow.newAuthorizationUrl()
      .setRedirectUri(clientSecrets.getDetails().getRedirectUris().get(0))
      .build();

  System.out.println("Please open the following URL in your browser then type the authorization code:");
  System.out.println("  " + authorizationUrl);

  // Read user input authorization code
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  String code = br.readLine();

  // Generate Token
  GoogleTokenResponse response = flow.newTokenRequest(code)
      .setRedirectUri(clientSecrets.getDetails().getRedirectUris().get(0))
      .execute();
        
  // Use access token to call API
  String accessToken = response.getAccessToken();
        
  // Now you can use this access token to access Google APIs on behalf of the user
 }
}