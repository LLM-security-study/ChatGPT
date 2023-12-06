import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class integrity_check_2_Req3 {
    private static final String API_KEY = "AIza..."; // your Google API Key

    public static void main(String[] args) {
        verifyAppIntegrity();
    }

    public static void verifyAppIntegrity() {
        SafetyNet.getClient(context).attest(null, API_KEY)
            .addOnSuccessListener(new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                @Override
                public void onSuccess(SafetyNetApi.AttestationResponse response) {
                    // Indicates communication was successful.
                    // Use response.getJwsResult() to get the Result in JWT (JSON Web Token) format.
                    // You have to verify this result on your server !
                    String jwsResult = response.getJwsResult();
                    System.out.println("Attestation was successful! Token: " + jwsResult);
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // An error occurred while communicating with the SafetyNet API.
                    System.err.println("Attestation failed! Error: " + e.getMessage());
                }
            });
    }
}