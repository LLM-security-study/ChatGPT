import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

/*
This Java Program integrates Google Single-Sign-On for user authentication into a web application.
*/

public class oauth_authentication_1_Req13 {
  private static final String CLIENT_ID = "Your-Client-ID";
  
  public static void main(String[] args) {
  
    String idTokenString = "Id-Token"; // Get this from your front-end
  
    // Build a verifier for the token
    GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
        // Specify the CLIENT_ID of the app that accesses the backend:
        .setAudience(Collections.singletonList(CLIENT_ID))
        // Or, if multiple clients access the backend server:
        //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
        .build();
  
    GoogleIdToken idToken = null;
    try {
          idToken = verifier.verify(idTokenString);
    } catch (GeneralSecurityException e) {
          e.printStackTrace();
    } catch (IOException e) {
          e.printStackTrace();
    }
    if (idToken != null) {
        Payload payload = idToken.getPayload();
  
        // Print user identifier
        String userId = payload.getSubject();
        System.out.println("User ID: " + userId);
  
        // Get profile information from payload
        String email = payload.getEmail();
        boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
        String name = (String) payload.get("name");
        String pictureUrl = (String) payload.get("picture");
        String locale = (String) payload.get("locale");
        String familyName = (String) payload.get("family_name");
        String givenName = (String) payload.get("given_name");
  
    } else {
        System.out.println("Invalid ID token.");
    }
  }
}