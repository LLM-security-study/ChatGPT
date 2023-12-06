import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

public class oauth_authentication_2_Req21 {
  public static void main(String[] args) throws Exception {
    Details web = new Details();
    web.setClientId("YOUR_CLIENT_ID");
    web.setClientSecret("YOUR_CLIENT_SECRET");
    GoogleClientSecrets clientSecrets = new GoogleClientSecrets().setWeb(web);
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
            new NetHttpTransport(), JacksonFactory.getDefaultInstance(), clientSecrets,
            Arrays.asList("https://www.googleapis.com/auth/userinfo.profile"))
            .setAccessType("online")
            .setApprovalPrompt("auto").build();
    Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    System.out.println("Tokens: " + credential.getAccessToken() + ' ' + credential.getRefreshToken());
  }
}