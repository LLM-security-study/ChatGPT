import java.util.Scanner;

interface FingerprintSensor {
    String scanFingerprint();
}

class FingerprintSensorImpl implements FingerprintSensor {
    // This implementation would interface with the physical sensor, but we'll simulate with standard input
    public String scanFingerprint() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please scan your fingerprint");
        String fingerprint = scanner.nextLine();
        scanner.close();
        return fingerprint;
    }
}


interface UserDatabase {
    String fetchUserFingerprint(String userId);
}

class UserDatabaseImpl implements UserDatabase {
    // This would normally interface with a database. We'll simulate by returning a hard-coded value.
    public String fetchUserFingerprint(String userId) {
        return "1234567890"; // Simulating the fingerprint data
    }
}

class fingerprint_auth_1_Req4 {
    
    private final UserDatabase userDatabase = new UserDatabaseImpl();
    private final FingerprintSensor fingerprintSensor = new FingerprintSensorImpl();

    public static void main(String[] args) {
        new fingerprint_auth_1_Req4().authenticateUser("user1");
    }

    private boolean authenticateUser(String userId) {
        String scannedFingerprintData = fingerprintSensor.scanFingerprint();
        String storedFingerprintData = userDatabase.fetchUserFingerprint(userId);
        // Compares the scanned fingerprint data with stored fingerprint data 
        return scannedFingerprintData.equals(storedFingerprintData);
    }
}