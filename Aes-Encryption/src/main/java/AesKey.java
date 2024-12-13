import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class AesKey {
    public static SecretKey generateAESKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256); // AES-256
        return keyGen.generateKey();
    }
}
