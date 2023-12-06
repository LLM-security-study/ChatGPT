import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class integrity_check_1_Req2 {

    private void checkAppIntegrity() {
        String nonceData = "The data that you want to send to the server";

        SafetyNet.getClient(this).attest(nonceData.getBytes(), "<Your API Key>")
            .addOnSuccessListener(new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                @Override
                public void onSuccess(SafetyNetApi.AttestationResponse response) {
                    // Indicates communication with the service was successful.
                    // Use response.getJwsResult() to get the result data.
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // An error occurred while communicating with the service.
                }
            });
    }

    public static void main(String[] args) {
        integrity_check_1_Req2 main = new integrity_check_1_Req2();
        main.checkAppIntegrity();
    }
}