import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import android.util.Log;

public class integrity_check_2_Req29 {

    public static void main(String[] args) {

        String nonceData = "Safety Net Sample: " + System.currentTimeMillis();
        byte[] nonce = nonceData.getBytes();

        Task<SafetyNetApi.AttestationResponse> attestTask = SafetyNet.getClient(this).attest(nonce, API_KEY);

        attestTask.addOnSuccessListener(new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
            @Override
            public void onSuccess(SafetyNetApi.AttestationResponse response) {
                // Indicates communication with the service was successful.
                // Use response.getJwsResult() to get the result data.
                Log.d("My App", "SafetyNet result: "+ response.getJwsResult());
            }

            public void onFailure(Exception e) {
                // An error occurred while communicating with the service.
                Log.d("My App", "Error occurred "+ e.getMessage());
            }
        });
    }
}