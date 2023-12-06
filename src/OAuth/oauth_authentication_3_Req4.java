// Import required libraries
import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.*;

public class oauth_authentication_3_Req4 {
  // Insert your clientID
  private static final String CLIENT_ID = "YOUR-CLIENT-ID";
  // Insert your clientSecret
  private static final String CLIENT_SECRET = "YOUR-CLIENT-SECRET";
  // Callback URI, as defined in the Google Cloud console
  private static final String CALLBACK_URI = "YOUR-CALLBACK-URI";
  
  public static void main(String[] args) {
    // Create a Google20 (Google OAuth2.0) service object
    OAuthService service = new ServiceBuilder()
                      .provider(Google20.class)
                      .apiKey(CLIENT_ID)
                      .apiSecret(CLIENT_SECRET)
                      .callback(CALLBACK_URI)
                      .build();
    
    Scanner in = new Scanner(System.in);

    System.out.println("=== Google's OAuth Workflow ===");
    System.out.println();

    // Obtain the Authorization URL
    System.out.println("Fetching the Authorization URL...");
    String authorizationUrl = service.getAuthorizationUrl(EMPTY_TOKEN);
    System.out.println("Got the Authorization URL!");

    System.out.println("Now go and authorize Scribe here:");
    System.out.println(authorizationUrl);
    System.out.println("And paste the authorization code here");
    System.out.print(">>");

    // Get the access token
    Verifier verifier = new Verifier(in.nextLine());
    Token accessToken = service.getAccessToken(EMPTY_TOKEN, verifier);
    
    // Use the access token to access the protected resources
    System.out.println("Now we're going to access a protected resource...");
    OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL, service);
    service.signRequest(accessToken, request);
    Response response = request.send();
    System.out.println("Got it! Lets see what we found...");

    System.out.println();
    System.out.println(response.getBody());

}