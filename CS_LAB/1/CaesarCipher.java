import java.util.Scanner;

/**
 * Caesar Cipher implementation for encryption and decryption
 * Shifts letters by a fixed key value in the alphabet
 */
class CaesarCipher {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    
    // Encrypt text using Caesar cipher
    public static String encrypt(String text, int key) {
        text = text.toLowerCase();
        StringBuilder result = new StringBuilder();
        
        for (char c : text.toCharArray()) {
            int pos = ALPHABET.indexOf(c);
            if (pos != -1) { // Only process alphabetic characters
                int newPos = (pos + key) % 26;
                result.append(ALPHABET.charAt(newPos));
            } else {
                result.append(c); // Keep non-alphabetic characters unchanged
            }
        }
        return result.toString();
    }
    
    // Decrypt text using Caesar cipher
    public static String decrypt(String text, int key) {
        text = text.toLowerCase();
        StringBuilder result = new StringBuilder();
        
        for (char c : text.toCharArray()) {
            int pos = ALPHABET.indexOf(c);
            if (pos != -1) { // Only process alphabetic characters
                int newPos = (pos - key + 26) % 26; // +26 to handle negative values
                result.append(ALPHABET.charAt(newPos));
            } else {
                result.append(c); // Keep non-alphabetic characters unchanged
            }
        }
        return result.toString();
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter text to encrypt: ");
        String text = sc.nextLine();
        
        System.out.print("Enter shift key (default 3): ");
        int key = sc.hasNextInt() ? sc.nextInt() : 3;
        
        String encrypted = encrypt(text, key);
        String decrypted = decrypt(encrypted, key);
        
        System.out.println("\nResults:");
        System.out.println("Original:  " + text);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
        
        sc.close();
    }
}
