import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class integrity_check_2_Req6Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String nonceData = "SampleRequestNonce";
        byte[] nonce = nonceData.getBytes();

        SafetyNet.getClient(this).attest(nonce, "<YOUR_API_KEY>")
                .addOnSuccessListener(this,
                        new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                            @Override
                            public void onSuccess(SafetyNetApi.AttestationResponse response) {
                                // Use response.getJwsResult() to get the result data.
                                Log.d("integrity_check_2_Req6Activity", "Success! SafetyNet result:\n" + response.getJwsResult() + "\n");
                            }
                        })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        // An error occurred while communicating with the SafetyNet Api
                        Log.d("integrity_check_2_Req6Activity", "Oops, an error occurred while communicating with the SafetyNet Api");
                        if (e instanceof ApiException) {
                            ApiException apiException = (ApiException) e;
                            Log.d("integrity_check_2_Req6Activity", "ApiException: " + apiException.getStatusCode());
                        } else {
                            Log.d("integrity_check_2_Req6Activity", "Other Exception: " + e.getMessage());
                        }
                    }
                });
    }
}