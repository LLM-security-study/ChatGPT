import java.io.IOException;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;

public class integrity_check_2_Req21 {
    public static void main(String[] args) {
        try {
            HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            HttpRequestFactory requestFactory = httpTransport.createRequestFactory(
            (HttpRequest request) -> {
                request.setParser(new JsonObjectParser(new JacksonFactory()));
            });

            GenericUrl url = new GenericUrl(String.format(
                    "https://www.googleapis.com/androidcheck/v1/attestations/check?key=%s",
                    "apiKeyHere" // Replace with your API key
            ));

            JsonObject requestBody = new JsonObject();
            requestBody.put("signedAttestation", "<jws-signature>"); // replace <jws-signature> with your signed attestation
            HttpRequest request = requestFactory.buildPostRequest(url, new JsonHttpContent(new JacksonFactory(), requestBody));
            String rawResponse = request.execute().parseAsString();
            System.out.println(rawResponse);

        } catch (IOException | GeneralSecurityException e) {
            System.out.println(e.getMessage());
        }
    }
}