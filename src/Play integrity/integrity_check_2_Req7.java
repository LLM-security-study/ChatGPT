import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class integrity_check_2_Req7 extends AppCompatActivity {
    private static final String TAG = "AppIntegrity";
    private static final String API_KEY = "<YOUR-API-KEY>";  // replace with your actual API key

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button checkIntegrityButton = findViewById(R.id.check_integrity_button);
        final TextView resultTextView = findViewById(R.id.result_text_view);

        checkIntegrityButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                SafetyNet.getClient(getApplicationContext()).attest(API_KEY, TAG).addOnSuccessListener(integrity_check_2_Req7.this,
                    new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                        @Override
                        public void onSuccess(SafetyNetApi.AttestationResponse response) {
                            // Indicate communication with the service was successful.
                            // Use response.getJwsResult() to get the result data.
                            resultTextView.setText("Success! JWS Result: " + response.getJwsResult());
                        }
                    }).addOnFailureListener(integrity_check_2_Req7.this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // An error occurred while communicating with the service.
                            if (e instanceof ApiException) {
                                ApiException apiException = (ApiException) e;
                                Log.d(TAG, "Error: " + apiException.getStatus().getErrorDetails());
                            } else {
                                // A different, unknown type of error occurred. 
                                Log.d(TAG, "Error: " + e.getMessage());
                            }
                        }
                    });
            }
        });
    }
}