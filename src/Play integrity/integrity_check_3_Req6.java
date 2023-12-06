import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnSuccessListener;

public class integrity_check_3_Req6 {

    public static void main(String[] args) {

        SafetyNet.getClient(this).attest("SAMPLE_NONCE".getBytes(), "YOUR_API_KEY")
                .addOnSuccessListener(this,
                        new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                            @Override
                            public void onSuccess(SafetyNetApi.AttestationResponse response) {
                                // Use response.getJwsResult() to get result of integrity check
                                String jwsResult = response.getJwsResult();
                                System.out.println("Integrity check result: " + jwsResult);
                                
                                //TODO: Add your code to parse the jwsResult and make decision based on that
                            }
                        });
    }
}