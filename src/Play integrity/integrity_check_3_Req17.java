//Please replace integrity_check_3_Req17Activity with your Activity name
public class integrity_check_3_Req17 extends AppCompatActivity {

    private static final String TAG = "integrity_check_3_Req17Activity";
    //Assuming key to be available, replace "YOUR_API_KEY" with actual key
    private static final String key = "YOUR_API_KEY";
    
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(SafetyNet.API)
                .build();
        mGoogleApiClient.connect();

        SafetyNet.SafetyNetApi.attest(mGoogleApiClient, nonceGenerator())
                .setResultCallback(new ResultCallback<SafetyNetApi.AttestationResult>() {

                    @Override
                    public void onResult(SafetyNetApi.AttestationResult result) {
                        Status status = result.getStatus();
                        if (status.isSuccess()) {
                            //Device passes SafetyNet API
                            //Returns JWS Message
                            //For more details: https://developer.android.com/training/safetynet/attestation#java
                            Log.i(TAG, "Successfully received SafetyNet Result: "+result.getJwsResult());
                        } else {
                            //Device fails SafetyNet API
                            Log.i(TAG, "Failed to receive SafetyNet Result: "+status);
                        }
                    }
                });
    }

    private byte[] nonceGenerator(){
        //Generate 256-bit(32-byte) nonce
        byte[] nonce = new byte[32];
        new SecureRandom().nextBytes(nonce);
        return nonce;
    }
}