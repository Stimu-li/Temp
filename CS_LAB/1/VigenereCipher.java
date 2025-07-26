import java.util.Scanner;

/**
 * Vigenère Cipher implementation
 * Encrypts/decrypts text using a repeating keyword
 */
class VigenereCipher {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    
    // Extend key to match text length
    public static String extendKey(String key, int textLength) {
        StringBuilder extendedKey = new StringBuilder(key);
        while (extendedKey.length() < textLength) {
            extendedKey.append(key);
        }
        return extendedKey.substring(0, textLength);
    }
    
    // Encrypt text using Vigenère cipher
    public static String encrypt(String plaintext, String key) {
        plaintext = plaintext.toLowerCase();
        key = key.toLowerCase();
        String extendedKey = extendKey(key, plaintext.length());
        
        StringBuilder ciphertext = new StringBuilder();
        
        for (int i = 0; i < plaintext.length(); i++) {
            if (Character.isLetter(plaintext.charAt(i))) {
                int plainIndex = plaintext.charAt(i) - 'a';
                int keyIndex = extendedKey.charAt(i) - 'a';
                int cipherIndex = (plainIndex + keyIndex) % 26;
                ciphertext.append((char) (cipherIndex + 'a'));
            } else {
                ciphertext.append(plaintext.charAt(i)); // Keep non-letters unchanged
            }
        }
        
        return ciphertext.toString();
    }
    
    // Decrypt text using Vigenère cipher
    public static String decrypt(String ciphertext, String key) {
        ciphertext = ciphertext.toLowerCase();
        key = key.toLowerCase();
        String extendedKey = extendKey(key, ciphertext.length());
        
        StringBuilder plaintext = new StringBuilder();
        
        for (int i = 0; i < ciphertext.length(); i++) {
            if (Character.isLetter(ciphertext.charAt(i))) {
                int cipherIndex = ciphertext.charAt(i) - 'a';
                int keyIndex = extendedKey.charAt(i) - 'a';
                int plainIndex = (cipherIndex - keyIndex + 26) % 26;
                plaintext.append((char) (plainIndex + 'a'));
            } else {
                plaintext.append(ciphertext.charAt(i)); // Keep non-letters unchanged
            }
        }
        
        return plaintext.toString();
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the key: ");
        String key = sc.nextLine();
        
        System.out.print("Enter the plaintext: ");
        String plaintext = sc.nextLine();
        
        String extendedKey = extendKey(key, plaintext.length());
        String encrypted = encrypt(plaintext, key);
        String decrypted = decrypt(encrypted, key);
        
        System.out.println("\nResults:");
        System.out.println("Message:     " + plaintext);
        System.out.println("Key Text:    " + extendedKey);
        System.out.println("Cipher Text: " + encrypted);
        System.out.println("Plain Text:  " + decrypted);
        
        sc.close();
    }
}
