import com.google.android.gms.safetyne.SafetyNet;
import com.google.android.gms.safetyne.SafetyNetApi;
import com.google.android.gms.safetyne.SafetyNetClient;
import com.google.android.gms.safetyne.AttestationResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.common.api.ApiException;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class integrity_check_3_Req23 extends AppCompatActivity {

    public static final String API_KEY = "your_api_key_here";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String nonceData = "Random String"; // Replace it with your own nonce data
        byte[] nonce = nonceData.getBytes();

        SafetyNetClient client = SafetyNet.getClient(this);
        client.attest(nonce, API_KEY)
            .addOnSuccessListener(this,
                new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                    @Override
                    public void onSuccess(SafetyNetApi.AttestationResponse response) {
                        // Indicates communication with the service was successful.
                        // Use response.getJwsResult() to get the result data.
                        Log.d("integrity_check_3_Req23Activity", "Success! SafetyNet result:\n" + response.getJwsResult() + "\n");
                    }
                })
            .addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // An error occurred while communicating with the service.
                    if (e instanceof ApiException) {
                        ApiException apiException = (ApiException) e;
                        Log.d("integrity_check_3_Req23Activity", "Error: " +
                                CommonStatusCodes.getStatusCodeString(apiException.getStatusCode()) + ": " +
                                apiException.getStatusMessage());
                    } else {
                        Log.d("integrity_check_3_Req23Activity", "Error: " + e.getMessage());
                    }
                }
            });
    }
}