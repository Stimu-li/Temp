import java.util.Scanner;

/**
 * Rail Fence Cipher implementation
 * Encrypts text using zigzag pattern across two rails
 */
class RailFenceCipher {
    
    // Encrypt text using Rail Fence cipher
    public static String encrypt(String plaintext) {
        StringBuilder topRail = new StringBuilder();
        StringBuilder bottomRail = new StringBuilder();
        
        // Separate characters into two rails
        for (int i = 0; i < plaintext.length(); i++) {
            if (i % 2 == 0) {
                topRail.append(plaintext.charAt(i));
            } else {
                bottomRail.append(plaintext.charAt(i));
            }
        }
        
        // Concatenate top rail + bottom rail
        return topRail.toString() + bottomRail.toString();
    }
    
    // Decrypt text using Rail Fence cipher
    public static String decrypt(String ciphertext) {
        StringBuilder plaintext = new StringBuilder();
        int halfLength = (ciphertext.length() + 1) / 2; // Handle odd length
        
        String topRail = ciphertext.substring(0, halfLength);
        String bottomRail = ciphertext.substring(halfLength);
        
        // Interleave characters from both rails
        for (int i = 0; i < halfLength; i++) {
            plaintext.append(topRail.charAt(i));
            if (i < bottomRail.length()) {
                plaintext.append(bottomRail.charAt(i));
            }
        }
        
        return plaintext.toString();
    }
    
    // Display rail pattern visualization
    public static void displayRailPattern(String text) {
        System.out.println("\nRail Pattern:");
        StringBuilder topRail = new StringBuilder();
        StringBuilder bottomRail = new StringBuilder();
        
        for (int i = 0; i < text.length(); i++) {
            if (i % 2 == 0) {
                topRail.append(text.charAt(i)).append(" ");
                bottomRail.append("  ");
            } else {
                topRail.append("  ");
                bottomRail.append(text.charAt(i)).append(" ");
            }
        }
        
        System.out.println("Top Rail:    " + topRail.toString());
        System.out.println("Bottom Rail: " + bottomRail.toString());
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the message: ");
        String message = sc.nextLine().toLowerCase().replace(" ", "");
        
        displayRailPattern(message);
        
        String encrypted = encrypt(message);
        String decrypted = decrypt(encrypted);
        
        System.out.println("\nResults:");
        System.out.println("Original Text: " + message);
        System.out.println("Cipher Text:   " + encrypted);
        System.out.println("Decrypted:     " + decrypted);
        
        sc.close();
    }
}
