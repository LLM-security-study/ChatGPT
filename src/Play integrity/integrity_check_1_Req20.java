import android.os.AsyncTask;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.safetynet.SafetyNetApi.AttestationResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import java.nio.charset.StandardCharsets;

public class integrity_check_1_Req20 {

    final String nonceData = "Sample nonce data";
    byte[] nonce = nonceData.getBytes(StandardCharsets.UTF_8);

    //An example method that calls SafetyNet Attestation API
    public void callSafetyNetAttestationApi() {

        SafetyNet.getClient(this).attest(nonce, "<API_KEY>")
            .addOnSuccessListener(this,
                new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                    @Override
                    public void onSuccess(AttestationResponse response) {
                        // Indicates communication with the service was successful.
                        // Use response.getJwsResult() to get the result data.
                        verifySafetyNetResponse(response.getJwsResult());
                    }
                })
            .addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if (e instanceof ApiException) {
                        ApiException apiException = (ApiException) e;
                        LOG.error("SafetyNet Attestation API call failed with status: " +
                                  CommonStatusCodes.getStatusCodeString(
                                      apiException.getStatusCode()) + ": " +
                                  e.getMessage());
                    } else {
                        LOG.error("SafetyNet Attestation API call failed with: " +
                                  e.getMessage());
                    }
                }
            });
    }

    //An example method where the app sends the JWS response to its own server.
    private void verifySafetyNetResponse(String responseJws) {
            // In this example, the app sends the JWS result to its own server over a
            // secure connection for verification. This is implemented via an
            // AsyncTask class.
            new SendToServerTask().execute(responseJws);
        }
        
    private static class SendToServerTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... responseJws) {
            // This method is typically used to send the response to your server
            // for verification.
            //TODO: send responseJws to server for verification
            return responseJws[0];
        }
    }

    public static void main(String[] args) {
        new integrity_check_1_Req20().callSafetyNetAttestationApi();
    }
}