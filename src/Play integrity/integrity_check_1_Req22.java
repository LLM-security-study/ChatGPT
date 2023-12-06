package com.example.checkapk;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import android.os.Bundle;
import android.util.Log;

public class integrity_check_1_Req22Activity extends AppCompatActivity {

    private static final String TAG = "integrity_check_1_Req22Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verifyAppIntegrity();
    }

    private void verifyAppIntegrity() {
        SafetyNet.getClient(this).attest("nonce".getBytes(), "YOUR_API_KEY")
                .addOnSuccessListener(this,
                        new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                            @Override
                            public void onSuccess(SafetyNetApi.AttestationResponse response) {
                                if (validateSafetyNetResponse(response)) {
                                    Log.d(TAG, "SafetyNet check passed!");
                                } else {
                                    Log.d(TAG, "SafetyNet check failed!");
                                }
                            }
                        })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof ApiException) {
                            ApiException apiException = (ApiException) e;
                            Log.d(TAG, "SafetyNet Api Error: " + CommonStatusCodes.getStatusCodeString(apiException.getStatusCode()));
                        } else {
                            Log.d(TAG, "Unknown SafetyNet error: " + e.getMessage());
                        }
                    }
                });
    }

    private boolean validateSafetyNetResponse(SafetyNetApi.AttestationResponse response) {
        // Here you could use Google's online SafetyNet API to validate the response (https://developer.android.com/training/safetynet/attestation#validate-attestation-response),
        // or parse and validate the JWT signature response locally, but this may be less secure
        // This function is not detailed in this example for simplicity.
    }
}