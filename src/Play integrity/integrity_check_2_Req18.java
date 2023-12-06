import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnSuccessListener;

public class integrity_check_2_Req18 {
    public static void main(String[] args) {
       String apiKey = "your_google_api_key";
       checkAppIntegrity(api_key);
    }

    public static void checkAppIntegrity(String apiKey) {
        Task<SafetyNetApi.AttestationResponse> task = SafetyNet.getClient(this).attest(nonce.getBytes(), apiKey);

        task.addOnSuccessListener(this, new OnSuccessListener<SafetyNetApi.AttestationResponse> () {
            @Override
            public void onSuccess(SafetyNetApi.AttestationResponse response) {
                // Indicates communication with the service was successful.
                // Use response.getJwsResult() to get the result data.
                System.out.println("Success! SafetyNet result:\n" + result.getJwsResult() + "\n");
            }
        });

        task.addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
               // An error occurred while communicating with the service.
               System.out.println("Failure! An error occurred while communicating with the service, " + e);
            }
        });

    }
}