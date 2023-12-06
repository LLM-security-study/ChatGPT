import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.safetynet.SafetyNetApi.AttestationResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import android.util.Log;

public class integrity_check_2_Req4 {
    private static final String TAG = "IntegrityVerification";

    public static void main(String[] args) {
        // The call to the SafetyNet API goes on the main thread.
        SafetyNet.getClient(this).attest(nonce, API_KEY)
                .addOnSuccessListener(this,
                        new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                            @Override
                            public void onSuccess(AttestationResponse response) {
                                // Indicates communication with the service was successful.
                                // Use response.getJwsResult() to get the result data.
                                Log.d(TAG, "Success! SafetyNet result:\n" + response.getJwsResult()+ "\n");

                                if (verifySafetyNetResponse(response.getJwsResult())) {
                                    // The SafetyNet API response has a valid signature and the device has passed
                                    // integrity checks.
                                    Log.d(TAG, "Passed SafetyNet API integrity check.");
                                } else {
                                    // The device failed the integrity check.
                                    Log.e(TAG, "Failed SafetyNet API integrity check.");
                                }
                            }
                        })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // An error occurred while communicating with the service.
                        if (e instanceof ApiException) {
                            ApiException apiException = (ApiException) e;
                            Log.d(TAG, "Error: " +
                                    CommonStatusCodes.getStatusCodeString(apiException.getStatusCode()) + ": " +
                                    apiException.getStatusMessage());
                        } else {
                            Log.d(TAG, "Error: " + e.getMessage());
                        }
                    }
                });
    }
    
    
    private boolean verifySafetyNetResponse(String response) {
        // Make believe method that would actually do some validation of the response
        return true;
    }

}