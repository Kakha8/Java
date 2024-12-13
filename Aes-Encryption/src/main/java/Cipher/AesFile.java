package Cipher;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;

public class AesFile {
    public static void encryptFile(File inputFile, File outputFile, SecretKey key, byte[] iv) throws Exception {
        // Read file content
        byte[] fileBytes = readFileToByteArray(inputFile);

        // Encrypt file content
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        byte[] encryptedBytes = cipher.doFinal(fileBytes);

        // Get file format (extension)
        String fileExtension = getFileExtension(inputFile);
        byte[] extensionBytes = fileExtension.getBytes("UTF-8");

        // Combine extension length, extension, and encrypted content
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            fos.write(extensionBytes.length); // Write length of extension
            fos.write(extensionBytes);        // Write extension
            fos.write(encryptedBytes);        // Write encrypted content
        }
    }

    public static void decryptFile(File inputFile, File outputFile, SecretKey key, byte[] iv) throws Exception {
        // Read the binary file
        try (FileInputStream fis = new FileInputStream(inputFile)) {
            // Read extension length
            int extensionLength = fis.read();

            // Read extension
            byte[] extensionBytes = new byte[extensionLength];
            fis.read(extensionBytes);
            String fileExtension = new String(extensionBytes, "UTF-8");

            // Read encrypted content
            byte[] encryptedBytes = readBytes(fis);

            // Decrypt file content
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            // Write decrypted content to output file
            File outputWithExtension = new File(outputFile.getAbsolutePath() + "." + fileExtension);
            try (FileOutputStream fos = new FileOutputStream(outputWithExtension)) {
                fos.write(decryptedBytes);
            }
        }
    }
    private static byte[] readFileToByteArray(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            return bos.toByteArray();
        }
    }

    private static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndex = name.lastIndexOf('.');
        return (lastIndex == -1) ? "" : name.substring(lastIndex + 1);
    }

    private static byte[] readBytes(FileInputStream fis) throws IOException {
        byte[] encryptedBytes;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            encryptedBytes = bos.toByteArray();
            return encryptedBytes;
        }
    }
}
