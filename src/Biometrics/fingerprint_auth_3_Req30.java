public class fingerprint_auth_3_Req30 {
    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;
    
    @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
        
        if (!keyguardManager.isKeyguardSecure()) {
            Toast.makeText(this, "Lock screen security not enabled in Settings", Toast.LENGTH_LONG).show();
            return;
        }
        
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Fingerprint authentication permission not enabled", Toast.LENGTH_LONG).show();
            return;
        }
        
        if (!fingerprintManager.hasEnrolledFingerprints()) {
            Toast.makeText(this, "Register at least one fingerprint in Settings", Toast.LENGTH_LONG).show();
            return;
        }
        
        generateKey();
        
        if (cipherInit()) {
            FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
            FingerprintHandler helper = new FingerprintHandler(this);
            helper.startAuth(fingerprintManager, cryptoObject);
        }
    }
    
    //remaining code here
}