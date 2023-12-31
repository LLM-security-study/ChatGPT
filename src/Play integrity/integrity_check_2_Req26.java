import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.androidcheck.AndroidCheck;
import com.google.api.services.androidcheck.model.AndroidCheckInfo;
import com.google.api.services.androidcheck.model.DeviceAttestationRequest;

public class integrity_check_2_Req26 {
    private static final String PACKAGE_NAME = "com.yourorg.yourapp";
    private static final String API_KEY = "your api key";
    private static final String ERROR_MESSAGE = "Device verification failed.";

    public static void main(String[] args) {
        try {
            JsonFactory jsonFactory = new JacksonFactory();
            AndroidCheck checkService = new AndroidCheck.Builder(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, null)
                .setApplicationName(PACKAGE_NAME)
                .build();

            DeviceAttestationRequest request = new DeviceAttestationRequest();
            AndroidCheckInfo androidCheckInfo = checkService.androidCheck(API_KEY).setRequestBody(request).execute();

            if (androidCheckInfo.getError() != null) {
                System.out.println(ERROR_MESSAGE);
                return;
            }

            if (androidCheckInfo.isBasicIntegrity()) {
                System.out.println("Device has passed basic integrity verification.");
            } else {
                System.out.println(ERROR_MESSAGE);
            }

            if (androidCheckInfo.isCertifiedDevice()) {
                System.out.println("Device is certified by Google.");
            } else {
                System.out.println(ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}