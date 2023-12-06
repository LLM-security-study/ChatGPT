class fingerprint_auth_3_Req20 {
    
    private Context mContext;

    private FingerprintManagerCompat fingerprintManagerCompat;

    public fingerprint_auth_3_Req20(Context mContext) {
        this.mContext = mContext;
        fingerprintManagerCompat = FingerprintManagerCompat.from(mContext);
    }

    public boolean checkFingerprintSupport() {
        if (!fingerprintManagerCompat.isHardwareDetected()) {
            return false;
        } else if (!fingerprintManagerCompat.hasEnrolledFingerprints()) {
            return false;
        }
        return true;
    }
}