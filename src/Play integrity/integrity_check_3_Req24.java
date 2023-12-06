import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.safetynet.SafetyNetClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class integrity_check_3_Req24 extends AppCompatActivity {

    private static final String TAG = "integrity_check_3_Req24Activity";
    private static final String SAFETYNET_API_KEY = "Your_API_Key"; // Replace with your actual SafetyNet API Key

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCheckSafetyNet = findViewById(R.id.btnCheckSafetyNet);
        final TextView txtSafetyNetStatus = findViewById(R.id.txtSafetyNetStatus);

        btnCheckSafetyNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SafetyNetClient client = SafetyNet.getClient(integrity_check_3_Req24.this);

                client.attest("nonce".getBytes(), SAFETYNET_API_KEY)
                        .addOnSuccessListener(integrity_check_3_Req24.this,
                                new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                                    @Override
                                    public void onSuccess(SafetyNetApi.AttestationResponse response) {
                                        // Indicates communication with the service was successful.
                                        // Use response.getJwsResult() to get the result data.
                                        txtSafetyNetStatus.setText("Device passed SafetyNet checks.");
                                        Log.d(TAG, "SafetyNet check succeeded.");
                                    }
                                })
                        .addOnFailureListener(integrity_check_3_Req24.this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // An error occurred while communicating with the service.
                                if (e instanceof ApiException) {
                                    // The SafetyNet Attest API has specific error codes: you can handle each.
                                    ApiException apiException = (ApiException) e;
                                    txtSafetyNetStatus.setText("SafetyNet check failed.");
                                    Log.d(TAG, "Error: " +
                                            CommonStatusCodes.getStatusCodeString(apiException.getStatusCode()) +
                                            ": " + apiException.getStatusMessage());
                                } else {
                                    // A different, unknown type of error occurred.
                                    Log.d(TAG, "Unknown type of error: " + e.getMessage());
                                }
                            }
                        });
            }
        });
    }
}