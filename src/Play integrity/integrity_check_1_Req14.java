import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.safetynet.SafetyNetClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import android.util.Base64;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class integrity_check_1_Req14 extends AppCompatActivity {

    private static final String API_KEY = "YOUR_API_KEY_GOES_HERE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SafetyNetClient client = SafetyNet.getClient(this);
        client.attest(API_KEY.getBytes(), API_KEY)
                .addOnSuccessListener(this,
                        new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                            @Override
                            public void onSuccess(SafetyNetApi.AttestationResponse response) {
                                String jwsResult = response.getJwsResult();
                                // Parse the JWS and check the binary hash
                            }
                        })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof ApiException) {
                            ApiException apiException = (ApiException) e;
                            // An error occurred when communicating with the SafetyNet API
                        } else {
                            // An unknown error occurred
                        }
                    }
                });
    }
}