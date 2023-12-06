import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

public class oauth_authentication_1_Req26 {

    public static void main(String[] args) throws Exception {

        GoogleClientSecretsDetails googleClientSecretsDetails = new GoogleClientSecretsDetails("client_id", "client_secret", "redirect_uri");

        GoogleClientSecrets clientSecrets = new GoogleClientSecrets();

        GoogleClientSecrets.Details web = new GoogleClientSecrets.Details()
            .setClientId(googleClientSecretsDetails.getClientId())
            .setClientSecret(googleClientSecretsDetails.getClientSecret())
            .setRedirectUris(Collections.singletonList(googleClientSecretsDetails.getRedirectUri()));

        clientSecrets.setWeb(web);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(),
                JacksonFactory.getDefaultInstance(), clientSecrets,
                Collections.singleton("https://www.googleapis.com/auth/userinfo.profile"))
                .setAccessType("offline").build();

        // Step_1 : Authorize the user. Open in your web browser
        String url = flow.newAuthorizationUrl().setRedirectUri(googleClientSecretsDetails.getRedirectUri()).build();
        System.out.println("Please open the following address in your browser");
        System.out.println("  " + url);

        // Ask_Code : Display authorization code from redirect page
        System.out.println("Enter authorization code:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String code = br.readLine();

        // Step_2 : Exchange, Exchange authorization code for access and refresh tokens
        Credential credential = flow.newTokenRequest(code).setRedirectUri(googleClientSecretsDetails.getRedirectUri()).execute();

        System.out.println("Access token: " + credential.getAccessToken());
        }
}