package com.example.androidintegrity;

import android.os.Bundle;
import android.util.Base64;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class integrity_check_3_Req11 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SafetyNet.getClient(this).attest("NONCE".getBytes(), "YOUR_API_KEY_HERE")
                .addOnSuccessListener(this,
                        new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                            @Override
                            public void onSuccess(SafetyNetApi.AttestationResponse response) {
                                String result = response.getJwsResult();

                                Toast.makeText(getApplicationContext(), "Device integrity confirmed", Toast.LENGTH_SHORT).show();
                            }
                        })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        if (e instanceof ApiException) {
                            ApiException apiException = (ApiException) e;
                            int statusCode = apiException.getStatusCode();
                            switch (statusCode) {
                                case CommonStatusCodes.API_NOT_CONNECTED:
                                    Toast.makeText(getApplicationContext(), "API not connected", Toast.LENGTH_SHORT).show();
                                    break;
                                case CommonStatusCodes.ERROR:
                                    Toast.makeText(getApplicationContext(), "An unknown error occurred", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Device integrity check failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}