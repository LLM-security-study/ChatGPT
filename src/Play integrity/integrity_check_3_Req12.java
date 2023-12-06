import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class integrity_check_3_Req12Activity extends AppCompatActivity {

    private static final String TAG = integrity_check_3_Req12Activity.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SafetyNet.getClient(this).attest("nonce".getBytes(), "YOUR_API_KEY")
            .addOnSuccessListener(this,
                new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                    @Override
                    public void onSuccess(SafetyNetApi.AttestationResponse response) {
                        // Indicates communication with the service was successful.
                        // Use response.getJwsResult() to get the result data.
                        Log.d(TAG, "Success! SafetyNet result:\n" + response.getJwsResult());
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
                        Log.d(TAG, "Unknown type of error, Google Play services could be outdated or SafetyNet Attestation failed.");
                    }
                }
            });

    }
}