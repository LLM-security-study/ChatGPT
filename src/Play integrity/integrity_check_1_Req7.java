import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnSuccessListener;

import java.security.SecureRandom;

public class integrity_check_1_Req7 {

    public static void main(String[] args) {
        // Generate a random nonce
        byte[] nonce = new byte[32];
        new SecureRandom().nextBytes(nonce);

        // Call the SafetyNet API
        SafetyNet.getClient(this).attest(nonce, "<YOUR_API_KEY>")
            .addOnSuccessListener(this,
                new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                    @Override
                    public void onSuccess(SafetyNetApi.AttestationResponse response) {
                        // Indicates communication with the service was successful.
                        // Use response.getJwsResult() to get the result data.
                        String jwsResult = response.getJwsResult();

                        // Verify the JWS result, check the CTS Profile Match and Basic Integrity
                        validateSafetyNetResponse(jwsResult);
                    }
                });
    }

    private void validateSafetyNetResponse(String jwsResult) {
        // Normally the JWS response would be validated on the server side 
        // This is because to decode a JWS message you need to encode your app's public key into it 
        // Never expose your private key in the app's code
        // TODO: Implement JWS message validation
    }
}