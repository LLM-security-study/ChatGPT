import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi.AttestationResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import org.json.JSONObject;

public class integrity_check_3_Req28 extends AppCompatActivity {

    private static final String TAG = "SafetyNetSample";
    private static final String API_KEY = "YOUR_API_KEY";
    private TextView mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mResult = findViewById(R.id.result_text);

        SafetyNet.getClient(this).attest("nonce".getBytes(), API_KEY)
                .addOnSuccessListener(this, new OnSuccessListener<AttestationResponse>() {
                    @Override
                    public void onSuccess(AttestationResponse attestationResponse) {
                        // Perform the security checks as per your need.
                        String result = attestationResponse.getJwsResult();
                        try {
                            String payload = result.split("\\.")[1];
                            String prettyPayload = new JSONObject(payload).toString(4);
                            Log.d(TAG, prettyPayload);
                            mResult.setText(prettyPayload);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                })

                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Error occurred: ", e);
                    }
                });
    }
}