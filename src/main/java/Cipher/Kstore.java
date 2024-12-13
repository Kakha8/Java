package Cipher;

import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;

public class Kstore {
    // Method to create a JCEKS Keystore file
    public static void createKeyStoreFile(String keystorePath, String keystorePassword) throws Exception {
        // Create a new KeyStore instance of type "JCEKS"
        KeyStore keyStore = KeyStore.getInstance("JCEKS");

        // Initialize the KeyStore (it will be empty initially)
        keyStore.load(null, keystorePassword.toCharArray());

        // Save the KeyStore to the specified path
        try (FileOutputStream fos = new FileOutputStream(keystorePath)) {
            keyStore.store(fos, keystorePassword.toCharArray());
        }

        System.out.println("KeyStore created at: " + keystorePath);
    }

    // Method to load a Keystore from a file
    public static KeyStore loadKeystore(String keystorePath, String keystorePassword) throws Exception {
        FileInputStream fis = new FileInputStream(keystorePath);
        KeyStore keyStore = KeyStore.getInstance("JCEKS"); // Use JCEKS for SecretKey
        keyStore.load(fis, keystorePassword.toCharArray());
        fis.close();
        return keyStore;
    }

    // Save AES key into the Keystore
    public static void saveKeyToKeystore(KeyStore keyStore, String alias, SecretKey secretKey, String keystorePassword) throws Exception {
        KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(secretKey);
        KeyStore.ProtectionParameter protectionParam = new KeyStore.PasswordProtection(keystorePassword.toCharArray());
        keyStore.setEntry(alias, secretKeyEntry, protectionParam);
        System.out.println("SecretKey stored in KeyStore under alias: " + alias);
    }

    // Save the Keystore to a file
    public static void saveKeystoreToFile(KeyStore keyStore, String keystorePath, String keystorePassword) throws Exception {
        try (FileOutputStream fos = new FileOutputStream(keystorePath)) {
            keyStore.store(fos, keystorePassword.toCharArray());
            System.out.println("KeyStore saved at: " + keystorePath);
        }
    }


}
