import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnSuccessListener;

public class integrity_check_3_Req25 {

    public void checkIntegrity() {
        String nonceData = "A random value to avoid replay attacks";   // Generate a random nonce.
        byte[] nonce = nonceData.getBytes();

        SafetyNet.getClient(this).attest(nonce, API_KEY)
                .addOnSuccessListener(this,
                        new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                            @Override
                            public void onSuccess(SafetyNetApi.AttestationResponse response) {
                                // Use response.getJwsResult() to get the result data.
                            }
                        })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // An error occurred while communicating with the SafetyNet API.
                    }
                });
    }
}