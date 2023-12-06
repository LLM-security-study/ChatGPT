import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;
import android.util.Log;
import android.support.annotation.NonNull;
import android.app.Activity;

public class integrity_check_2_Req16 extends Activity {
    private final String API_KEY = "your_api_key_here";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendSafetyNetRequest();
    }

    private void sendSafetyNetRequest() {
        SafetyNet.getClient(this).attest("nonce value".getBytes(), API_KEY)
                .addOnCompleteListener(new OnCompleteListener<SafetyNetApi.AttestationResponse>() {
                    @Override
                    public void onComplete(@NonNull Task<SafetyNetApi.AttestationResponse> task) {
                        if (task.isSuccessful()) {
                            SafetyNetApi.AttestationResponse result = task.getResult();
                            String attestationResult = result.getJwsResult();
                            // Now verify the attestation result
                            Log.i("SafetyNet Sample", "Result is " + attestationResult);
                        } else {
                            // An error occurred while communicating with the SafetyNet API
                            Log.e("SafetyNet Sample", "Error occurred while communicating with API " + task.getException());
                        }
                    }
                });
    }
}