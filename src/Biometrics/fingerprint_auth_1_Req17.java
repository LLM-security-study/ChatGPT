public class fingerprint_auth_1_Req17Activity extends AppCompatActivity {
    // Other code...
 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
        FingerprintHandler fingerprintHandler = new FingerprintHandler(this);
        fingerprintHandler.startAuth(fingerprintManager, null);
    }
}