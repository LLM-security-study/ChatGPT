import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnSuccessListener;

public class integrity_check_3_Req15 {

    public static void main(String ... args) {
        // The nonce should be at least 16 bytes in length.
        byte[] nonce = generateNonce(); // implement this according to your needs

        // This represents your project's (client's) API key.
        String myApiKey = getMyApiKey(); // implement this according to your needs

        SafetyNet.getClient(context).attest(nonce, myApiKey)
                .addOnSuccessListener(new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                    @Override
                    public void onSuccess(SafetyNetApi.AttestationResponse response) {
                        // The SafetyNet Attestation API returned a response successfully
                        String result = response.getJwsResult();
                        // Use the result to assess the device's integrity
                        // Make sure to send this to your server for further verification 
                    }
                });
    }

}
