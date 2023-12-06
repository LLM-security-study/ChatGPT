import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import android.util.Log;

public class integrity_check_1_Req24 extends AppCompatActivity {

    // Assume there is an available key
    private String API_KEY = "YOUR_API_KEY_HERE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SafetyNet.getClient(this).attest(API_KEY.getBytes(), API_KEY)
                .addOnSuccessListener(this,
                        new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                            @Override
                            public void onSuccess(SafetyNetApi.AttestationResponse response) {
                                // Indicates communication with the service was successful.
                                // Use response.getJwsResult() to get the result data.
                                Log.d("MyApp", "SafetyNet check success: " + response.getJwsResult());
                            }
                        })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // An error has occurred. Check if it's because of a known issue using ApiException.getStatusCode().
                        if (e instanceof ApiException) {
                            ApiException apiException = (ApiException) e;
                            // Handle API errors using apiException.getStatusCode()
                            Log.d("MyApp","Failure: " + apiException.getStatusCode());
                        } else {
                            // Unknown type of error has occurred.
                            Log.d("MyApp","Failure");
                        }
                    }
                });
    }
}