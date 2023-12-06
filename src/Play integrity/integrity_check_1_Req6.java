import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnSuccessListener;

public class integrity_check_1_Req6 {
    public static void main(String[] args) {
     
        String nonceData = "Random-Data-Can-Be-Anything"; //It's a random data to be associated with the request

        byte[] nonce = nonceData.getBytes();

        SafetyNet.getClient(this).attest(nonce, "<Your-API-Key>")
                .addOnSuccessListener(this, new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                    @Override
                    public void onSuccess(SafetyNetApi.AttestationResponse response) {
                        // Use the response. You may want to send it to your server for further process.
                        String jwsResult = response.getJwsResult();

                        // This string contains a cryptographic signature that you can use to verify the
                        // authenticity of the payload. The signature is located at the end of the string,
                        // following the second period (.) character and you use it to confirm that 
                        // the data was sent by Google Play services.
                        // You decode it like this:
                        //  Signature = JWS-SignatureURL-Safe decode(split(JWS-Result, ".")[2]) 
                        //
                        // Now, if it doesn't pass the verification, potentially this is not a genuine app.
                        }
                });
    }
}