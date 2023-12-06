import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class integrity_check_3_Req2 extends AppCompatActivity {
    private static final String TAG = "SafetyNetSample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String nonceData = "Sample SafetyNet Nonce";
        byte[] nonce = nonceData.getBytes();

        SafetyNet.getClient(this).attest(nonce, "<insert your API key here>")
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
                            // An error with the Google Play services API contains some additional details.
                            ApiException apiException = (ApiException) e;

                            // You can retrieve the status code using the
                            // apiException.getStatusCode() method.
                        } else {
                            // A different, unknown type of error occurred.
                            Log.e(TAG, "Error: " + e.getMessage());
                        }
                    }
                });
    }
}