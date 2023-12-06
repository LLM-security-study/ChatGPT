import android.os.Bundle;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.safetynet.SafetyNetClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import android.app.Activity;
import android.util.Log;

public class integrity_check_2_Req22 extends Activity {
    private static final String TAG = "IntegrityVerification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SafetyNetClient client = SafetyNet.getClient(this);
        String nonceData = "Sample nonce";  
        byte[] nonce = nonceData.getBytes();

        client.attest(nonce, API_KEY)
                .addOnSuccessListener(this,
                        new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                            @Override
                            public void onSuccess(SafetyNetApi.AttestationResponse response) {
                                // Indicates communication with the service was successful.
                                // Use response.getJwsResult() to get the result data.
                                Log.d(TAG, "Success! SafetyNet result:\n" + response.getJwsResult() + "\n");
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
                            // A different, unknown type of error occurred.
                            Log.d(TAG, "Error: " + e.getMessage());
                        }
                    }
                });
    }
}