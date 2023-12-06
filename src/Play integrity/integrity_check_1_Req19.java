import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class integrity_check_1_Req19 extends AppCompatActivity {

    private static final String TAG = "SafetyNetSample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        String nonceData = "RANDOM_NONCE"; // You should generate a different nonce for each request
        byte[] nonce = nonceData.getBytes();

        SafetyNet.getClient(this).attest(nonce, API_KEY)
                .addOnSuccessListener(this,
                        new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                            @Override
                            public void onSuccess(SafetyNetApi.AttestationResponse response) {
                                // Indicates communication with the service was successful.
                                // Use response.getJwsResult() to get the result data.
                                processSafetyNetResponse(response.getJwsResult());
                            }
                        })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof ApiException) {
                            ApiException apiException = (ApiException) e;
                            Log.d(TAG, "Error: " +
                                    CommonStatusCodes.getStatusCodeString(apiException.getStatusCode()) +": " +
                                    apiException.getStatusMessage());
                        } else {
                            Log.d(TAG, "Unknown type of error: " + e.getMessage());
                        }
                    }
                });
    }

    private void processSafetyNetResponse(String jwsResult) {
        if (jwsResult == null) {
            Log.e(TAG,"JWS response is null");
            return;
        }
        // Parse the JWS response and check if the CTS (Compatibility test suite) passed
        // If yes, then the app is most likely interacting with its original binary
        // This is a simplified check. Full detailed check includes verifying the JWS signature
        // - Check: https://developer.android.com/training/safetynet/attestation#verify-attestation-response for details
        String[] splitResponse = jwsResult.split("\\.");
        if (splitResponse.length == 3) {
            try {
                // We're only interested in the body/payload
                String decodedPayload = new String(android.util.Base64.decode(splitResponse[1], android.util.Base64.DEFAULT));
                JSONObject payload = new JSONObject(decodedPayload);
                if (payload.has("ctsProfileMatch")) {
                    boolean ctsProfileMatch = payload.getBoolean("ctsProfileMatch");
                    if(ctsProfileMatch){
                        Log.d(TAG, "CTS profile match: true. App is interacting with its original binary.");
                    } else{
                        Log.d(TAG, "CTS profile match: false. App may not be interacting with its original binary.");
                    }
                } else {
                    Log.e(TAG, "Error: ctsProfileMatch not found");
                }
            } catch (JSONException e) {
                Log.e(TAG, "Failed to decode JWS response: " + e);
            }
        } else {
            Log.e(TAG, "Failed to split JWS response");
        }
    }
}