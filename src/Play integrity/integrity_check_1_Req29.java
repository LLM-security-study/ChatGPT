import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnFailureListener;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class integrity_check_1_Req29 extends AppCompatActivity {

    private static final String TAG = "SafetyNetSample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        performSafetyNetCheck();
    }

    private void performSafetyNetCheck() {
        SafetyNet.getClient(this).attest("nonce".getBytes(), "<YourAPIKey>")
                .addOnSuccessListener(this,
                        new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                            @Override
                            public void onSuccess(SafetyNetApi.AttestationResponse response) {
                                // Indicates communication with the service was successful.
                                // Parse response and check the device's compatibility.
                                String result = response.getJwsResult();
                                Log.d(TAG, "Success! SafetyNet result:\n" + result);
                                // TODO: Verify the JWS token and parse the payload JSON to check binary integrity.
                            }
                        })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG,"Failed to communicate with SafetyNet API");
                        e.printStackTrace();
                    }
                });
    }
}