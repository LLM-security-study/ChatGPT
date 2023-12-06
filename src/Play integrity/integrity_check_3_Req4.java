import android.util.Log;
import android.app.Activity;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.safetynet.SafetyNetClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class integrity_check_3_Req4 extends Activity {
    private static final String TAG = "integrity_check_3_Req4Activity";
    
    private static final String API_KEY = "Place Your API Key Here";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SafetyNetClient client = SafetyNet.getClient(this);
        
        client.attest("Sample Nonce".getBytes(), API_KEY)
            .addOnSuccessListener(this, new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                @Override
                public void onSuccess(SafetyNetApi.AttestationResponse response) {
                    // Indicates communication with the service was successful.
                    // Use response.getJwsResult() to get Result.
                    Log.d(TAG, "Success! SafetyNet result:\n" + response.getJwsResult());
                }
            })
            .addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // An error occurred while communicating with the service.
                    if (e instanceof ApiException) {
                        ApiException apiException = (ApiException) e;
                        Log.d(TAG, "SafetyNet Error: " + CommonStatusCodes.getStatusCodeString(apiException.getStatusCode()) );
                    } else {
                        Log.d(TAG, "Unknown SafetyNet Error: " + e.getMessage());
                    }
                }
            });
    }
}