import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class integrity_check_1_Req30 extends AppCompatActivity {

    private static final String TAG = "NetMessage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assume that API key is available.
        String API_KEY = "YOUR_API_KEY";

        SafetyNet.getClient(this).attest(API_KEY.getBytes(), API_KEY)
          .addOnSuccessListener(this,
              new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                  @Override
                  public void onSuccess(SafetyNetApi.AttestationResponse response) {
                      // Indicates communication with the service was successful.
                      // Use response.getJwsResult() to get the result data.
                  Log.i(TAG, "Success! Result is: " +
                        response.getJwsResult());
                  }
              })
          .addOnFailureListener(this, new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
                // An error occurred while communicating with the service.
                Log.e(TAG, "Error during SafetyNet Attest: ", e);
             }
          });
    }
}