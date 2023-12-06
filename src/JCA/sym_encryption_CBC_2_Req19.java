public class sym_encryption_CBC_2_Req19 {
    private static final String key = "aesEncryptionKey";
    private static final String initVector = "encryptionIntVec";

    public static void main(String[] args) {
        String originalString = "The data to be encrypted";
        System.out.println("Original string: " + originalString);

        String encryptedString = encrypt(originalString);
        System.out.println("Encrypted string: " + encryptedString);
    }