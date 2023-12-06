import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.util.Log;

public class integrity_check_3_Req14 extends AppCompatActivity {

  // Your API key here
  private static final String SAFETYNET_API_KEY = "Your API Key Here";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    SafetyNet.getClient(this).attest(nonce(), SAFETYNET_API_KEY)
      .addOnCompleteListener(new OnCompleteListener<SafetyNetApi.AttestationResponse>() {
        @Override
        public void onComplete(@NonNull Task<SafetyNetApi.AttestationResponse> task) {
          if (task.isSuccessful()) {
            // The SafetyNet Attestation succeeded.
            Log.d("SAFETYNET", "Success! The device is safe.");
          } else {
            // An error occurred while communicating with the service.
            ApiException apiException = (ApiException) task.getException();
            Log.d("SAFETYNET", "Error: " + apiException.getMessage());
          }
        }
      });
  }
  
  // Function creating nonce
  public byte[] nonce() {
    // Random 24-byte nonce.
    byte[] nonce = new byte[24];
    new SecureRandom().nextBytes(nonce);
    return nonce;
  }
}