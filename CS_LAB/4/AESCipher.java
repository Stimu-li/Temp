import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES (Advanced Encryption Standard) implementation
 * Encrypts/decrypts data using 128/192/256-bit symmetric key
 */
class AESCipher {
    private static Cipher cipher;
    
    // Initialize AES cipher
    public static void initializeCipher() throws Exception {
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    }
    
    // Generate AES key of specified size
    public static SecretKey generateKey(int keySize) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(keySize); // 128, 192, or 256 bits
        SecretKey secretKey = keyGenerator.generateKey();
        
        System.out.println("AES-" + keySize + " Key Generated: " + 
            Base64.getEncoder().encodeToString(secretKey.getEncoded()));
        
        return secretKey;
    }
    
    // Encrypt plaintext using AES
    public static String encrypt(String plaintext, SecretKey secretKey) throws Exception {
        System.out.println("Plain Text Before Encryption: " + plaintext);
        
        byte[] plaintextBytes = plaintext.getBytes();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plaintextBytes);
        
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("Encrypted Text After Encryption: " + encryptedText);
        
        return encryptedText;
    }
    
    // Decrypt ciphertext using AES
    public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        
        String decryptedText = new String(decryptedBytes);
        System.out.println("Decrypted Text After Decryption: " + decryptedText);
        
        return decryptedText;
    }
    
    // Display AES algorithm details
    public static void displayAlgorithmInfo() {
        System.out.println("=== AES Algorithm Information ===");
        System.out.println("Block Size: 128 bits (16 bytes)");
        System.out.println("Key Sizes: 128, 192, 256 bits");
        System.out.println("Rounds: AES-128 (10), AES-192 (12), AES-256 (14)");
        System.out.println("Mode: ECB (Electronic Codebook)");
        System.out.println("Padding: PKCS5Padding");
        System.out.println("=====================================\n");
    }
    
    public static void main(String[] args) {
        try {
            displayAlgorithmInfo();
            
            Scanner sc = new Scanner(System.in);
            
            // Initialize cipher
            initializeCipher();
            
            // Choose key size
            System.out.print("Choose AES key size (128/192/256): ");
            int keySize = sc.hasNextInt() ? sc.nextInt() : 128;
            sc.nextLine(); // consume newline
            
            // Generate key
            SecretKey secretKey = generateKey(keySize);
            
            // Get input text
            System.out.print("\nEnter text to encrypt: ");
            String plaintext = sc.nextLine();
            if (plaintext.isEmpty()) {
                plaintext = "AES Symmetric Encryption Decryption";
            }
            
            System.out.println("\n--- Encryption Process ---");
            String encryptedText = encrypt(plaintext, secretKey);
            
            System.out.println("\n--- Decryption Process ---");
            String decryptedText = decrypt(encryptedText, secretKey);
            
            // Verification
            System.out.println("\n--- Verification ---");
            System.out.println("Original:  " + plaintext);
            System.out.println("Decrypted: " + decryptedText);
            System.out.println("Match: " + plaintext.equals(decryptedText));
            
            sc.close();
            
        } catch (Exception e) {
            System.err.println("AES Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
