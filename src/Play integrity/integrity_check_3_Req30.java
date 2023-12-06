import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class integrity_check_3_Req30 extends AppCompatActivity {
    private static final String TAG = "SafetyNetSample";
    private static final String API_KEY = "YOUR_ACTUAL_API_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new SafetyNetCheck().execute("");
    }

    private class SafetyNetCheck extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            SafetyNet.getClient(integrity_check_3_Req30.this).attest("".getBytes(), API_KEY)
                .addOnSuccessListener(integrity_check_3_Req30.this,
                    new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                        @Override
                        public void onSuccess(SafetyNetApi.AttestationResponse response) {
                            Log.d(TAG, "Success! SafetyNet result:\n" + response.getJwsResult());
                        }
                    })
                .addOnFailureListener(integrity_check_3_Req30.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof ApiException) {
                            ApiException apiException = (ApiException) e;
                            Log.e(TAG, "Error: " +
                                CommonStatusCodes.getStatusCodeString(apiException.getStatusCode()));
                        } else {
                            Log.e(TAG, "Error: " + e.getMessage());
                        }
                    }
                });
        }
    }
}