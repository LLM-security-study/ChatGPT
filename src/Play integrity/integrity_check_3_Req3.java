import android.util.Log;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;

public class integrity_check_3_Req3 {
  private static final String TAG = "IntegrityCheck";

  public static void main(String[] args) {
    String nonceData = "Random Nonce Data";  // should be at least 16 bytes in length
    byte[] nonce = nonceData.getBytes();

  SafetyNet.getClient(this).attest(nonce, "<YOUR_API_KEY>")
    .addOnSuccessListener(this,
      new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
        @Override
        public void onSuccess(SafetyNetApi.AttestationResponse response) {
          // Indicates communication with the service was successful.
          // Use response.getJwsResult() to get the result data.
          Log.d(TAG, "Success! SafetyNet result:\n" + response.getJwsResult() + "\n");
        }
      })
    .addOnFailureListener(this, new OnFailureListener() {
      @Override
      public void onFailure(@NonNull Exception e) {
        // An error occurred while communicating with the service.
        if (e instanceof ApiException) {
          // An error with the Google Play services API contains some additional details.
          ApiException apiException = (ApiException) e;

          // You can retrieve the status code using the
          // apiException.getStatusCode() method.
        } else {
          // A different, unknown type of error occurred. Log it here.
          Log.d(TAG, "Unknown error occurred: " + e.getMessage());
        }
      }
    });
  }
}