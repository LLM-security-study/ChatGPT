import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class integrity_check_3_Req1 extends AppCompatActivity {

    private static final String TAG = integrity_check_3_Req1.class.getSimpleName();
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonCheck = findViewById(R.id.buttonCheck);
        textViewResult = findViewById(R.id.textViewResult);

        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDeviceIntegrity();
            }
        });
    }

    private void checkDeviceIntegrity() {
        String nonceData = "Safety Net Sample: " + System.currentTimeMillis();
        byte[] nonce = nonceData.getBytes();

        Task<SafetyNetApi.AttestationResponse> attestTask = SafetyNet.getClient(this).attest(nonce, "API_KEY_HERE");

        attestTask.addOnSuccessListener(new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
            @Override
            public void onSuccess(SafetyNetApi.AttestationResponse attestationResponse) {
                String result = attestationResponse.getJwsResult();
                // Do something with result, like send it to your server for verification.
                textViewResult.setText("Result: " + result);
                Log.d(TAG, "Result: " + result);
            }
        });
    }
}