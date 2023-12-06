import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.Task;

public class integrity_check_1_Req26 extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "SafetyNetResponse";
    GoogleApiClient googleApiClient;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        setContentView(textView);
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(SafetyNet.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        googleApiClient.connect();
    }

    private void sendSafetyNetRequest() {
        Log.i(TAG, "Sending SafetyNet API request.");

        SafetyNet.getClient(this).attest("sampleNonce".getBytes(), "<Your_API_Key>")
                .addOnCompleteListener(this, task -> handleResult(task));
    }

    private void handleResult(Task<SafetyNetApi.AttestationResponse> result) {
        try {
            final SafetyNetApi.AttestationResponse response = result.getResult(ApiException.class);
            final String jwsResult = response.getJwsResult();
            // JWS result can be decoded and verified according to your needs
            // For the sake of simplicity, I am just logging it here
            Log.d(TAG, "Result: " + jwsResult);
        } catch (ApiException e) {
            Log.e(TAG, "Error while getting SafetyNet Results: " + e.getMessage());
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "onConnected");
        sendSafetyNetRequest();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "onConnectionSuspended: "+i);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed");
    }
}