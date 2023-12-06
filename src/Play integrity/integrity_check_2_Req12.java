import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnSuccessListener;

public class integrity_check_2_Req12Activity extends Activity {

    private static final String TAG = "SafetyNetTest";
    // Replace this with your own API key
    private static final String API_KEY = "Your_API_Key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SafetyNet.getClient(this).attest("Nonces_data".getBytes(), API_KEY)
                .addOnSuccessListener(this, new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                    @Override
                    public void onSuccess(SafetyNetApi.AttestationResponse response) {
                        // Indicate communication with the service was successful.
                        // Use response.getJwsResult() to get the result data.
                        Log.d(TAG, "Success! SafetyNet result:\n" + response.getJwsResult() + "\n");
                        // Ideally, we would send this result to our server for verification, but 
                        // just log it here for demonstration.
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // An error occurred while communicating with the service.
                        if (e instanceof ApiException) {
                            // An error with the Google Play Services API contains some additional details.
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