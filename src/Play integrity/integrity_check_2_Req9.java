import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class integrity_check_2_Req9 {
    private static final String TAG = "integrity_check_2_Req9Activity";
    protected String mResult;

    public void verifyAppIntegrity() {
        if (isGooglePlayServicesAvailable()) {
            SafetyNet.getClient(this).attest("nonce".getBytes(), "API_KEY")
                        .addOnSuccessListener(this,
                            new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                                @Override
                                public void onSuccess(SafetyNetApi.AttestationResponse response) {
                                    // Indicates communication with the service was successful.
                                    // Use response.getJwsResult() to get the result data.
                                    mResult = response.getJwsResult();
                                    Log.d(TAG, "Success! SafetyNet result:\n" + mResult + "\n");
                                }
                            })
                            .addOnFailureListener(this, new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // An error occurred while communicating with the service.
                                    if (e instanceof ApiException) {
                                        ApiException apiException = (ApiException) e;
                                        Log.d(TAG, "Error: " +
                                                    CommonStatusCodes.getStatusCodeString(apiException.getStatusCode()) + ": " +
                                                    apiException.getStatusMessage());
                                    } else {
                                        // A different, unknown type of error occurred.
                                        Log.d(TAG, "Error: " + e.getMessage());
                                    }
                                }
                            });
        }
    }

    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        ConnectionResult result = apiAvailability.isGooglePlayServicesAvailable(this);
        if (result.isSuccess()) {
            return true;
        } else if (apiAvailability.isUserResolvableError(result)) {
            showErrorDialog(result.getErrorCode());
        } else {
            Log.d(TAG, "Google Play Services is not available.");
        }
        return false;
    }

    private void showErrorDialog(int errorCode) {
        // Show an error dialog that Google Play Services is not available.
        // This is only called if isUserResolvableError returned true.
    }
}