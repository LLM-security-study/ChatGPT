import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.safetynet.SafetyNetClient;

public class integrity_check_3_Req18 {

    // Replace with your valid API Key
    private static final String API_KEY = "YOUR_API_KEY";
    
    public static void main(String[] args) {
        requestSafetyNetCheck();
    }

    private static void requestSafetyNetCheck() {
        SafetyNetClient client = SafetyNet.getClient(context);

        client.attest(nonce, API_KEY)
                .addOnSuccessListener(new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                    @Override
                    public void onSuccess(SafetyNetApi.AttestationResponse response) {
                        // Indicates communication with the service was successful.
                        // Use response.getJwsResult() to get the result data.
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // An error occurred while communicating with the service.
                        e.printStackTrace();
                    }
                });
    }

    // nonce is an arbitrary number that client adds to request which server can check in responses.
    // It's for preventing client request replay and must be generate for each request.
    // This function is an example of random 32-byte(256-bit) nonce generator.
    private static byte[] generateOneTimeRequestNonce() {
        byte[] nonce = new byte[32];
        new SecureRandom().nextBytes(nonce);
        return nonce;
    }
}