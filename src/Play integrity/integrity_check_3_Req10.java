import android.util.Log;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import androidx.annotation.NonNull;

public class integrity_check_3_Req10 {

    private static final String TAG = "SafetyNetSample";

    public static void main(String[] args) {
        sendSafetyNetRequest();
    }

    private static void sendSafetyNetRequest() {
        long nonce = generateNonce(); // Implement this method to generate a nonce

        SafetyNet.getClient(this).attest(nonce, API_KEY)
            .addOnSuccessListener(this,
                new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                    @Override
                    public void onSuccess(SafetyNetApi.AttestationResponse response) {
                        // Indicates communication with the service was successful.
                        // Use response.getJwsResult() to get the result data.
                        Log.d(TAG, "Success! SafetyNet result:\n" + response.getJwsResult() + "\n");
                        boolean isValidSignature = verifySafetyNetResponse(response.getJwsResult()); 
                        // You need to implement this method to verify the JWS message
                        if (isValidSignature) {
                            Log.d(TAG, "Signature is valid");
                        } else {
                            Log.e(TAG, "Signature is invalid");
                        }
                    }
                })
            .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // An error occurred while communicating with the service.
                        if (e instanceof ApiException) {
                            // An error occurred when communicating with the service.
                            ApiException apiException = (ApiException) e;
                            Log.e(TAG, "SafetyNet ApiException " + apiException.getStatusCode() + ": " + apiException.getMessage());
                        } else {
                            // An error occurred while executing the API call even before initiating a call to the service.
                            Log.e(TAG, "SafetyNet error: " + e.getMessage());
                        }
                    }
                });
    }
}