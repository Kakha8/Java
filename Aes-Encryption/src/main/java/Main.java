import Cipher.AesKey;
import Cipher.Kstore;

import javax.crypto.SecretKey;
import java.security.KeyStore;

public class Main {
    public static void main(String[] args) {
        try {
            // Keystore details
            String keystorePath = "myKeystore.jceks";
            String keystorePassword = "changeit";
            String alias = "aesKeyAlias";


            // Create Keystore
            Kstore.createKeyStoreFile(keystorePath, keystorePassword);

            // Load Keystore
            KeyStore keyStore = Kstore.loadKeystore(keystorePath, keystorePassword);

            // Generate SecretKey
            SecretKey secretKey = AesKey.generateAESKey();

            // Store SecretKey in Keystore
            Kstore.saveKeyToKeystore(keyStore, alias, secretKey, keystorePassword);

            // Save Updated Keystore to File
            Kstore.saveKeystoreToFile(keyStore, keystorePath, keystorePassword);

            System.out.println("Keystore created and SecretKey stored successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}