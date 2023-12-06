import com.google.android.gms.safetynet.SafeBrowsingClient;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class integrity_check_2_Req23 {
    public static void main(String[] args) {
        String nonceData = "Sample nonce"; // A nonce that we obtain from the server.
        byte[] nonce = nonceData.getBytes();
        
        SafetyNet.getClient(this).attest(nonce, API_KEY)
            .addOnSuccessListener(this,
                new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                    @Override
                    public void onSuccess(SafetyNetApi.AttestationResponse response) {
                        // Indicates communication with the service was successful.
                        // Use response.getJwsResult() to get the result data.
                        System.out.println("Success! SafetyNet result:\n" + response.getJwsResult());
                    }
                })
            .addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // An error occurred while communicating with the service.
                    System.out.println("Failed to communicate with SafetyNet API.");
                }
            });
    }
}