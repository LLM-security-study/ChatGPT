import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.safetyne.SafetyNet;
import com.google.android.gms.safetyne.SafetyNetClient;
import com.google.android.gms.safetyne.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class integrity_check_2_Req20 extends Activity{
    /* Replace with your own API KEY */
    public static final String SAFETYNET_API_KEY = "YOUR_API_KEY_HERE";
    private TextView tvAppIntegrity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvAppIntegrity = (TextView) findViewById(R.id.tv_app_integrity);

        /* Request Test */
        SafetyNetClient client = SafetyNet.getClient(this);
        client.attest("SampleNonce".getBytes(), SAFETYNET_API_KEY)
                .addOnSuccessListener(this,new OnSuccessListener<SafetyNetApi.AttestationResponse>(){
                    @Override
                    public void onSuccess(SafetyNetApi.AttestationResponse response) {
                        /* Indicates communication with the service was successful.
                           Use response.getJwsResult() to get the result data. */
                        boolean valid = validateSafetyNetResponse(response.getJwsResult());
                        if (valid){
                            tvAppIntegrity.setText("The app is legitimate and it hasn't been tampered with.");
                        } else {
                            tvAppIntegrity.setText("The app integrity is compromised or it may have been tampered with!");
                        }
                    }
                }).addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        /* An error occurred while communicating with the service. */
                        tvAppIntegrity.setText("An error occurred: " + e.getMessage());
                   }
               });
     }


    /* Validate the JWS Message and check its payload for app integrity */
    private boolean validateSafetyNetResponse(String jwsResult){
        //TODO: Implement this method with JWS message validation and integrity check...
        /* Ideally you should not perform the JWS validation on the client side, 
           send this to the server side and validate response with JWS's integrity and authenticity there. */

        return false;  
     }
}