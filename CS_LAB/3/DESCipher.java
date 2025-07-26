import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * DES (Data Encryption Standard) implementation
 * Encrypts/decrypts data using 56-bit symmetric key
 */
class DESCipher {
    private static SecretKey secretKey;
    private static Cipher encryptCipher;
    private static Cipher decryptCipher;
    
    // Initialize DES cipher with generated key
    public static void initializeCipher() throws NoSuchAlgorithmException, 
            NoSuchPaddingException, InvalidKeyException {
        
        // Generate DES key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        secretKey = keyGenerator.generateKey();
        
        // Initialize encryption cipher
        encryptCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);
        
        // Initialize decryption cipher
        decryptCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKey);
        
        System.out.println("DES Key Generated: " + 
            Base64.getEncoder().encodeToString(secretKey.getEncoded()));
    }
    
    // Encrypt plaintext using DES
    public static byte[] encryptData(String plaintext) throws 
            IllegalBlockSizeException, BadPaddingException {
        
        System.out.println("Data Before Encryption: " + plaintext);
        byte[] dataToEncrypt = plaintext.getBytes();
        byte[] encryptedData = encryptCipher.doFinal(dataToEncrypt);
        
        System.out.println("Encrypted Data (Base64): " + 
            Base64.getEncoder().encodeToString(encryptedData));
        
        return encryptedData;
    }
    
    // Decrypt ciphertext using DES
    public static String decryptData(byte[] encryptedData) throws 
            IllegalBlockSizeException, BadPaddingException {
        
        byte[] decryptedData = decryptCipher.doFinal(encryptedData);
        String decryptedText = new String(decryptedData);
        
        System.out.println("Decrypted Data: " + decryptedText);
        return decryptedText;
    }
    
    public static void main(String[] args) {
        try {
            System.out.println("=== DES Encryption Demo ===\n");
            
            // Initialize DES cipher
            initializeCipher();
            
            // Test data
            String originalText = "Classified Information!";
            
            // Encrypt data
            System.out.println("\n--- Encryption Process ---");
            byte[] encryptedData = encryptData(originalText);
            
            // Decrypt data
            System.out.println("\n--- Decryption Process ---");
            String decryptedText = decryptData(encryptedData);
            
            // Verify integrity
            System.out.println("\n--- Verification ---");
            System.out.println("Original Text:  " + originalText);
            System.out.println("Decrypted Text: " + decryptedText);
            System.out.println("Match: " + originalText.equals(decryptedText));
            
        } catch (NoSuchAlgorithmException e) {
            System.err.println("DES algorithm not available: " + e.getMessage());
        } catch (NoSuchPaddingException e) {
            System.err.println("Padding not available: " + e.getMessage());
        } catch (InvalidKeyException e) {
            System.err.println("Invalid key: " + e.getMessage());
        } catch (IllegalBlockSizeException e) {
            System.err.println("Illegal block size: " + e.getMessage());
        } catch (BadPaddingException e) {
            System.err.println("Bad padding: " + e.getMessage());
        }
    }
}
