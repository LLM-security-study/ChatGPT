import androidx.annotation.NonNull;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.safetynet.SafetyNetClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class integrity_check_1_Req18 {

    public static void main(String[] args) {
        final SafetyNetClient client = SafetyNet.getClient(integrity_check_1_Req18.this);

        client.listHarmfulApps()
            .addOnSuccessListener(new OnSuccessListener<SafetyNetApi.HarmfulAppsResponse>() {
                   @Override
                   public void onSuccess(SafetyNetApi.HarmfulAppsResponse response) {
                        if (response.getHarmfulAppsList().isEmpty()) {
                           System.out.println("No known potentially harmful apps are installed.");
                        } else {
                           System.out.println("Potentially harmful apps are installed!");
                           // Here you could extract apkPackageName and compare it with known good hash values 
                           // for your app. You can use apkSha256 or apkCertificateDigestSha256 fields to 
                           // check the integrity. If they do not match with expected value, it is likely that 
                           // app binary has been modified.
                        }
                   }
               })
               .addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                      if (e instanceof ApiException) {
                          ApiException apiException = (ApiException) e;
                          System.out.println("Error: " + apiException.getStatusCode());
                      } else {
                          System.out.println("Unknown error: " + e.getMessage());
                      }
                   }
               });
    }
}