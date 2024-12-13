import Cipher.AesEncryption;
import Cipher.AesKey;
import Cipher.Kstore;

import javax.crypto.SecretKey;
import java.io.File;
import java.security.KeyStore;

import static Cipher.AesFile.decryptFile;
import static Cipher.AesFile.encryptFile;

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

            String plain = "yle";
            byte[] iv = AesKey.generateIV();
            String encrypted = AesEncryption.encryptText(plain, secretKey, iv);

            System.out.println("Encrypted: " + encrypted);

            String decrypted = AesEncryption.decryptText(encrypted, secretKey, iv);

            System.out.println("Decrypted: " + decrypted);

            File inputFile = new File("example.txt");
            File encryptedFile = new File("example_encrypted.bin");
            File decryptedFile = new File("example_decrypted");

            encryptFile(inputFile, encryptedFile, secretKey, iv);
            decryptFile(encryptedFile, decryptedFile, secretKey, iv);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}