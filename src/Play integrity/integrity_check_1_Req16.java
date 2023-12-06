import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class integrity_check_1_Req16 {
    private static final String TAG = "IntegrityCheck";

    public static void main(String[] args) {
        String nonceData = "Safety Net Sample: " + System.currentTimeMillis();
        byte[] nonce = nonceData.getBytes();

        SafetyNet.getClient(this).attest(nonce, "<Your API key here>")
            .addOnSuccessListener(this,
                new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                    @Override
                    public void onSuccess(SafetyNetApi.AttestationResponse response) {
                        // Indicates communication with the service was successful.
                        // Use response.getJwsResult() to get the result data.
                        Log.d(TAG, "Success! SafetyNet result:\n" + 
                                   response.getJwsResult() + "\n");
                    }
                })
            .addOnFailureListener(this, 
                new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // An error occurred while communicating with the service.
                        if (e instanceof ApiException) {
                            // An error with the Google Play services API contains some 
                               // additional details.
                            ApiException apiException = (ApiException) e;

                            Log.d(TAG, "Error: " + 
                                       CommonStatusCodes.getStatusCodeString(apiException.getStatusCode()) 
                                       + ": " + apiException.getStatusMessage());
                        } else {
                            // Unknown type of error has occurred.
                            Log.d(TAG, "Error: " + e.getMessage());
                        }
                    }
                });
    }
}